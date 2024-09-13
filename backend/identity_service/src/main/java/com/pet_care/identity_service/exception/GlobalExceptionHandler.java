package com.pet_care.identity_service.exception;

import com.pet_care.identity_service.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@ControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException ex) {
        ErrorCode errorCode = ErrorCode.valueOf(ex.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.builder().code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode()).message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage()).build());
    }


    @ExceptionHandler(IdentityException.class)
    ResponseEntity<ApiResponse> handlingIdentityException(IdentityException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getCode()).body(ApiResponse.builder().code(errorCode.getCode()).message(errorCode.getMessage()).build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(ErrorCode.UNAUTHORIZED.getCode()).body(ApiResponse.builder().code(errorCode.getCode()).message(errorCode.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String enumKey = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored) {}

        return ResponseEntity.badRequest().body(ApiResponse.builder().code(errorCode.getCode()).message(errorCode.getMessage()).build());
    }
}
