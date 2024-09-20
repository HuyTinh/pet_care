package com.pet_care.customer_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet_care.customer_service.dto.request.CustomerRequest;
import com.pet_care.customer_service.dto.request.sub.AppointmentRequest;
import com.pet_care.customer_service.dto.response.CustomerResponse;
import com.pet_care.customer_service.exception.CustomerException;
import com.pet_care.customer_service.exception.ErrorCode;
import com.pet_care.customer_service.mapper.CustomerMapper;
import com.pet_care.customer_service.model.Customer;
import com.pet_care.customer_service.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    MessageService messageService;

    ObjectMapper objectMapper;

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toDto).orElseThrow(() -> new RuntimeException(("")));
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws JsonProcessingException {
        Customer customerSave = customerRepository.save(customerMapper.toEntity(customerRequest));

        AppointmentRequest appointmentRequest = customerRequest.getAppointment();

        appointmentRequest.setCustomerId(customerSave.getId());

        messageService.sendMessageQueue("customer-create-appointment", objectMapper.writeValueAsString(customerRequest.getAppointment()));

        return customerMapper.toDto(customerRepository.save(customerSave));
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerException(ErrorCode.CUSTOMER_NOT_FOUND));
        customerMapper.partialUpdate(customerRequest, existingCustomer);
        return customerMapper.toDto(customerRepository.save(existingCustomer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
