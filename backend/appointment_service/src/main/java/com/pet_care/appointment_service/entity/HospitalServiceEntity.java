package com.pet_care.appointment_service.entity;

import com.pet_care.appointment_service.enums.HospitalServiceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Hospital_Services")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HospitalServiceEntity {
    @Id
    String name;

    String description;

    String price;

    @Enumerated(EnumType.STRING)
    HospitalServiceStatus status;
}
