package com.pet_care.appointment_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet_care.appointment_service.dto.request.AppointmentRequest;
import com.pet_care.appointment_service.dto.response.AppointmentResponse;
import com.pet_care.appointment_service.enums.AppointmentStatus;
import com.pet_care.appointment_service.exception.AppointmentException;
import com.pet_care.appointment_service.exception.ErrorCode;
import com.pet_care.appointment_service.mapper.AppointmentMapper;
import com.pet_care.appointment_service.mapper.PetMapper;
import com.pet_care.appointment_service.model.Appointment;
import com.pet_care.appointment_service.model.Pet;
import com.pet_care.appointment_service.repository.AppointmentRepository;
import com.pet_care.appointment_service.repository.HospitalServiceRepository;
import com.pet_care.appointment_service.repository.PetRepository;
import com.pet_care.appointment_service.repository.httpClient.CustomerClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentService {

    AppointmentRepository appointmentRepository;

    HospitalServiceRepository hospitalServiceRepository;

    AppointmentMapper appointmentMapper;

    MessageService messageService;

    Queue<String> queue;

    ObjectMapper objectMapper;

    CustomerClient customerClient;
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public AppointmentResponse create(AppointmentRequest appointmentRequest) throws JsonProcessingException {
        Appointment appointment = appointmentMapper.toEntity(appointmentRequest);

        appointment.setServices(new HashSet<>(hospitalServiceRepository.findAllById(appointmentRequest.getServices())));


        if(appointmentRequest.getStatus() == null){
            appointment.setStatus(AppointmentStatus.PENDING);
        }


        Appointment createSuccess = appointmentRepository.save(appointment);

        Set<Pet> pets = appointmentRequest.getPets().stream().map(petMapper::toEntity).collect(Collectors.toSet()).stream().peek(pet -> pet.setAppointment(createSuccess)).collect(Collectors.toSet());

        petRepository.saveAll(pets);

        String createAppointmentStatus = createSuccess.getStatus().name();
        AppointmentResponse appointmentResponse = appointmentMapper.toDto(appointment);
        appointmentResponse.setCustomer(customerClient.getCustomer(String.valueOf(appointment.getCustomerId())).getResult());

        if(createAppointmentStatus.equals("CHECKED_IN")){
            messageService.sendMessage("doctor-appointment-queue", objectMapper.writeValueAsString(appointmentResponse));
        } else {
           try {
               queue.add(objectMapper.writeValueAsString(appointmentResponse));
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
        }

        return appointmentMapper.toDto(createSuccess);
    }

    public List<AppointmentResponse> getAll() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream().map(appointment -> {
            AppointmentResponse appointmentResponse = appointmentMapper.toDto(appointment);
            appointmentResponse.setPets(petRepository.findByAppointment_Id(appointment.getId()).stream().map(petMapper::toDto).collect(Collectors.toSet()));
//            appointmentResponse.setCustomer(customerClient.getCustomer(String.valueOf(appointment.getCustomerId())).getResult());
            return appointmentResponse;
        }).toList();
    }

    @Transactional
    public AppointmentResponse getById(String appointment) {
        return appointmentMapper
                .toDto(appointmentRepository
                .findById(appointment)
                .orElseThrow(() -> new AppointmentException(ErrorCode.APPOINTMENT_NOT_FOUND)));
    }

    @Transactional
    public List<AppointmentResponse> getByAccountId(Long accountId) {
        Long customerId = customerClient.getCustomerByAccountId(String.valueOf(accountId)).getResult().getId();
        return appointmentRepository
                        .findAllByCustomerId(customerId).stream().map(appointment -> {
                            AppointmentResponse appointmentResponse = appointmentMapper.toDto(appointment);
                            appointmentResponse.setPets(new HashSet<>(petRepository.findByAppointment_Id(appointment.getId())).stream().map(petMapper::toDto).collect(Collectors.toSet()));
                            return appointmentResponse;
                }).collect(Collectors.toList());
    }

    public int checkInAppointment(Long appointmentId) {
        return appointmentRepository.checkInAppointment(appointmentId);
    }

    public int cancelAppointment(Long appointmentId) {
        return appointmentRepository.cancelledAppointment(appointmentId);
    }

    public List<AppointmentResponse> getByStatus(String status) {
        return appointmentRepository.findAppointmentByStatus(AppointmentStatus.valueOf(status)).stream().map(appointmentMapper::toDto).collect(Collectors.toList());
    }

    @JmsListener(destination = "customer-create-appointment", containerFactory = "queueFactory")
    public void receiveCustomerCreateAppointment(String message) {
        try {
            this.create(objectMapper.readValue(message, AppointmentRequest.class));
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
