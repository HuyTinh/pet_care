package com.pet_care.appointment_service.service;

import com.pet_care.appointment_service.dto.request.HospitalServiceRequest;
import com.pet_care.appointment_service.dto.response.HospitalServiceResponse;
import com.pet_care.appointment_service.exception.AppointmentException;
import com.pet_care.appointment_service.exception.ErrorCode;
import com.pet_care.appointment_service.mapper.HospitalServiceMapper;
import com.pet_care.appointment_service.model.HospitalServiceEntity;
import com.pet_care.appointment_service.repository.HospitalServiceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HospitalService {

    HospitalServiceRepository hospitalServiceRepository;

    HospitalServiceMapper hospitalServiceMapper;

    public List<HospitalServiceResponse> getAll() {
        List<HospitalServiceEntity> hospitalServices = hospitalServiceRepository.findAll();
        return hospitalServices.stream().map(hospitalServiceMapper::toDto).collect(Collectors.toList());
    }

    public HospitalServiceResponse getById(String name) {
        HospitalServiceEntity hospitalServiceEntity = hospitalServiceRepository
                .findById(name)
                .orElseThrow(() -> new AppointmentException(ErrorCode.HOSPITAL_SERVICE_NOT_FOUND));
        return hospitalServiceMapper.toDto(hospitalServiceEntity);
    }

    public HospitalServiceResponse create(HospitalServiceRequest hospitalServiceRequest) {
        HospitalServiceEntity hospitalService = hospitalServiceMapper.toEntity(hospitalServiceRequest);
        System.out.println(hospitalService);
        return hospitalServiceMapper.toDto(hospitalServiceRepository.save(hospitalService));
    }

    public HospitalServiceResponse update(String hospitalService, HospitalServiceRequest hospitalServiceRequest) {
        HospitalServiceEntity existHospitalServiceEntity = hospitalServiceRepository
                .findById(hospitalService)
                .orElseThrow(() -> new AppointmentException(ErrorCode.HOSPITAL_SERVICE_NOT_FOUND));

        HospitalServiceEntity updatedHospitalServiceEntity = hospitalServiceMapper.partialUpdate(hospitalServiceRequest, existHospitalServiceEntity);
        return hospitalServiceMapper.toDto(hospitalServiceRepository.save(updatedHospitalServiceEntity));
    }

    public void delete(String hospitalService) {
        hospitalServiceRepository.deleteById(hospitalService);
    }
}
