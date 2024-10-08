package com.pet_care.identity_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet_care.identity_service.model.Account;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Account}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationRequest implements Serializable {
    @Size(min = 5, message = "PASSWORD_INVALID")
    String password;

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
    message = "EMAIL_INVALID")
    String email;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("phone_number")
    @Size(min = 5, message = "PHONE_NUMBER_INVALID")
    String phoneNumber;

    Set<String> roles;
}