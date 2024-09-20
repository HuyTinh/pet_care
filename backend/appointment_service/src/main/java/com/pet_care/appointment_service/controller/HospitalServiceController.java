package com.pet_care.appointment_service.controller;

import com.pet_care.appointment_service.dto.request.HospitalServiceRequest;
import com.pet_care.appointment_service.dto.response.ApiResponse;
import com.pet_care.appointment_service.dto.response.HospitalServiceResponse;
import com.pet_care.appointment_service.service.HospitalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment/hospital-service")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HospitalServiceController {
    HospitalService hospitalService;

    @PostMapping
    ApiResponse<HospitalServiceResponse> create(@RequestBody HospitalServiceRequest hospitalServiceRequest) {
        return ApiResponse.<HospitalServiceResponse>builder()
                .result(hospitalService.create(hospitalServiceRequest))
                .build();
    };

    @GetMapping
    ApiResponse<List<HospitalServiceResponse>> getAll() {
        return ApiResponse.<List<HospitalServiceResponse>>builder()
                .result(hospitalService.getAll()).build();
    }

    @GetMapping("{service}")
    ApiResponse<HospitalServiceResponse> getById(@PathVariable("service") String service) {
        return ApiResponse.<HospitalServiceResponse>builder().result(hospitalService.getById(service)).build();
    }
}
