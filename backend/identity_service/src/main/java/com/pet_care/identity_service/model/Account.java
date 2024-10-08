package com.pet_care.identity_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String password;
    String email;
    @ManyToMany
    Set<Role> roles;
    @JsonIgnore
    LocalDate createdAt;
    @JsonIgnore
    LocalDate updatedAt;
}
