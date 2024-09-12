package com.pet_care.identity_service.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class BaseEntity {

    LocalDate createdAt;
    LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }
}
