package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Pet;
import com.example.demo.model.Veterinarian;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.VetRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository repo;
    private final PetRepository petRepo;
    private final VetRepository vetRepo;

    public AppointmentService(AppointmentRepository repo, PetRepository petRepo, VetRepository vetRepo) {
        this.repo = repo; this.petRepo = petRepo; this.vetRepo = vetRepo;
    }

    public Appointment bookAppointment(Long vetId, Long petId, LocalDateTime dateTime, String reason) {
        LocalDateTime start = dateTime.minusMinutes(30);
        LocalDateTime end = dateTime.plusMinutes(30);
        if (repo.existsByVeterinarianIdAndAppointmentDateTimeBetween(vetId, start, end)) {
            throw new RuntimeException("Vet is busy at this time.");
        }
        if (repo.existsByPetIdAndAppointmentDateTime(petId, dateTime)) {
            throw new RuntimeException("Pet already has an appointment at this time.");
        }
        Pet pet = petRepo.findById(petId).orElseThrow();
        Veterinarian vet = vetRepo.findById(vetId).orElseThrow();
        Appointment appt = new Appointment();
        appt.setAppointmentDateTime(dateTime);
        appt.setReason(reason);
        appt.setPet(pet);
        appt.setVeterinarian(vet);
        return repo.save(appt);
    }

    public void cancelAppointment(Long id) { repo.deleteById(id); }
    public List<Appointment> listVetAppointments(Long vetId, LocalDateTime from, LocalDateTime to) {
        return repo.findByVeterinarianIdAndAppointmentDateTimeBetween(vetId, from, to);
    }
    public List<Appointment> listPetAppointments(Long petId) {
        return repo.findByPetId(petId);
    }
    public long countVetAppointments(Long vetId, LocalDateTime from, LocalDateTime to) {
        return repo.countByVeterinarianIdAndAppointmentDateTimeBetween(vetId, from, to);
    }
    public long countPetVisits(Long petId) {
        return repo.countByPetId(petId);
    }
}
