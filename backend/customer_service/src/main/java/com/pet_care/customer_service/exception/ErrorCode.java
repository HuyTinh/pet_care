package com.pet_care.customer_service.exception;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    CUSTOMER_NOT_FOUND(1017, "Customer not found", HttpStatus.NOT_FOUND),
    PET_NOT_FOUND(1018, "Pet not found", HttpStatus.NOT_FOUND),
    EMAIL_NOT_FOUND(1019, "Email not found", HttpStatus.NOT_FOUND),;

    int code;
    String message;
    HttpStatusCode status;
}
