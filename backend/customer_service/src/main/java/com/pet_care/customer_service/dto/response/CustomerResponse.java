package com.pet_care.customer_service.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet_care.customer_service.model.Pet;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    Long id;

    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("phone_number")
    String phoneNumber;

    String address;

    String email;

    @JsonProperty("account_id")
    Long accountId;

    Set<PetResponse> pets;
}
