package com.pet_care.appointment_service.repository;

import com.pet_care.appointment_service.enums.AppointmentStatus;
import com.pet_care.appointment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE appointments set status = 'CHECKED_IN' Where id = ?1")
    int checkInAppointment(Long id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE appointments set status = 'CANCELLED' Where id = ?1")
    int cancelledAppointment(Long id);

    List<Appointment> findAppointmentByStatus(AppointmentStatus status);

    @Query(value = "SELECT EXISTS (SELECT TRUE FROM appointments WHERE status = 'CHECKED_IN' And id = ?1)")
    int checkInAppointmentIsExist(Long id);

    List<Appointment> findAllByCustomerId(Long customerId);
}