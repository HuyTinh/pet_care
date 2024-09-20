package com.pet_care.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity(name = "pets")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String age;

    Double weight;

    String species;

    @ManyToOne
    @JoinColumn(name="customer_id")
    Customer customer;
}
