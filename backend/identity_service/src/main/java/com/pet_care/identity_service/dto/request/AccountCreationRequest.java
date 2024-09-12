package com.pet_care.identity_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet_care.identity_service.entity.Account;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Account}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest implements Serializable {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @Size(min = 5, message = "PASSWORD_INVALID")
    String password;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
    message = "EMAIL_INVALID")
    String email;
    LocalDate dob;
}