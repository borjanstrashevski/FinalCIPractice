package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByVeterinarianIdAndAppointmentDateTimeBetween(Long vetId, LocalDateTime from, LocalDateTime to);
    List<Appointment> findByPetId(Long petId);
    long countByVeterinarianIdAndAppointmentDateTimeBetween(Long vetId, LocalDateTime from, LocalDateTime to);
    long countByPetId(Long petId);
    boolean existsByVeterinarianIdAndAppointmentDateTimeBetween(Long vetId, LocalDateTime start, LocalDateTime end);
    boolean existsByPetIdAndAppointmentDateTime(Long petId, LocalDateTime dateTime);
}
