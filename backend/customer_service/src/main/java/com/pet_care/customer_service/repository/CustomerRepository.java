package com.pet_care.customer_service.repository;

import com.pet_care.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}