package com.pet_care.appointment_service.dto.request;

import com.pet_care.appointment_service.enums.HospitalServiceStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HospitalRequest {
    String name;

    String description;

    String price;

    HospitalServiceStatus status;
}
