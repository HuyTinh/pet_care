package com.pet_care.appointment_service.exception;

import com.pet_care.appointment_service.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> HandlingRuntimeException(RuntimeException e) {
        ErrorCode errorCode = ErrorCode.valueOf(e.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }
}
