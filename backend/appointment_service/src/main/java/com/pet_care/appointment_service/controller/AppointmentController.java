package com.pet_care.appointment_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet_care.appointment_service.dto.request.AppointmentRequest;
import com.pet_care.appointment_service.dto.response.ApiResponse;
import com.pet_care.appointment_service.dto.response.AppointmentResponse;
import com.pet_care.appointment_service.service.AppointmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("appointment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentController {
    AppointmentService appointmentService;

    @GetMapping
    public ApiResponse<List<AppointmentResponse>> getAll() throws JsonProcessingException {
        return ApiResponse.<List<AppointmentResponse>>builder()
                .result(appointmentService.getAll())
                .build();
    }

    @GetMapping("/account/{accountId}")
    public ApiResponse<List<AppointmentResponse>> getAllByAccountId(@PathVariable("accountId") Long accountId) throws JsonProcessingException {
        return ApiResponse.<List<AppointmentResponse>>builder()
                .result(appointmentService.getByAccountId(accountId))
                .build();
    }

    @PostMapping
    public ApiResponse<AppointmentResponse> create(@RequestBody AppointmentRequest appointmentRequest) throws JsonProcessingException {
        return ApiResponse.<AppointmentResponse>builder()
                .result(appointmentService.create(appointmentRequest))
                .build();
    }

    @PostMapping("/approved/{appointmentId}")
    public ApiResponse<Integer> checkInAppointment(@PathVariable Long appointmentId) {
        return ApiResponse.<Integer>builder()
                .result(appointmentService.checkInAppointment(appointmentId))
                .build();
    }

    @PostMapping("/cancel/{appointmentId}")
    public ApiResponse<Integer> cancelAppointment(@PathVariable Long appointmentId) {
        return ApiResponse.<Integer>builder()
                .result(appointmentService.cancelAppointment(appointmentId))
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<AppointmentResponse>> getAppointmentsByStatus(@PathVariable("status") String status) {
        return ApiResponse.<List<AppointmentResponse>>builder()
                .result(appointmentService.getByStatus(status))
                .build();
    }

    @GetMapping("/isCheckin/{appointmentId}")
    public ApiResponse<?> getAppointment(@PathVariable Long appointmentId) {
        return ApiResponse.builder()
                .result(Map.of("isCheckIn:", appointmentService.checkInAppointment(appointmentId) == 1))
                .build();

    }
}
