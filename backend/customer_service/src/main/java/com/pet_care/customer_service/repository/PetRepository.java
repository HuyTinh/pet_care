package com.pet_care.customer_service.repository;

import com.pet_care.customer_service.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}