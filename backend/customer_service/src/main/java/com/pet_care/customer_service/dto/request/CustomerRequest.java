package com.pet_care.customer_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet_care.customer_service.dto.request.sub.AppointmentRequest;
import com.pet_care.customer_service.model.Pet;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("phone_number")
    String phoneNumber;
    String email;
    String address;
    @JsonProperty("account_id")
    Long accountId;

    AppointmentRequest appointment;

    Set<PetRequest> pets;
}
