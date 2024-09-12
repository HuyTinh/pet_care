package com.pet_care.identity_service.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "Account exist"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at less 5 characters"),
    EMAIL_INVALID(1005, "Email is not valid"),
    USER_NOT_EXISTED(1006, "Account exist"),
    UNAUTHENTICATED(1007, "Unauthenticated"),;

    int code;
    String message;
}
