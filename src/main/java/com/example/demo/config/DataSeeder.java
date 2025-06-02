package com.example.demo.config;

import com.example.demo.model.Pet;
import com.example.demo.model.Veterinarian;
import com.example.demo.model.Appointment;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.VetRepository;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PetRepository petRepo;
    private final VetRepository vetRepo;
    private final AppointmentRepository apptRepo;

    public DataSeeder(PetRepository petRepo, VetRepository vetRepo, AppointmentRepository apptRepo) {
        this.petRepo = petRepo; this.vetRepo = vetRepo; this.apptRepo = apptRepo;
    }

    @Override
    public void run(String... args) {
        Pet p1 = petRepo.save(new Pet(null, "Buddy", "Dog", "Labrador", LocalDate.of(2020,1,1), "Alice", "1234567890"));
        Pet p2 = petRepo.save(new Pet(null, "Mittens", "Cat", "Siamese", LocalDate.of(2019,5,10), "Bob", "0987654321"));
        Pet p3 = petRepo.save(new Pet(null, "Rex", "Dog", "Beagle", LocalDate.of(2021,3,15), "Carol", "1112223333"));

        Veterinarian v1 = vetRepo.save(new Veterinarian(null, "John", "Doe", "john@vet.com", "Surgery"));
        Veterinarian v2 = vetRepo.save(new Veterinarian(null, "Jane", "Smith", "jane@vet.com", "Dermatology"));
        Veterinarian v3 = vetRepo.save(new Veterinarian(null, "Sam", "Lee", "sam@vet.com", "Dentistry"));

        // Each vet and pet has at least one past and one future appointment
        apptRepo.save(new Appointment(null, LocalDateTime.now().minusDays(1), "Checkup", p1, v1));
        apptRepo.save(new Appointment(null, LocalDateTime.now().plusDays(2), "Vaccination", p2, v2));
        apptRepo.save(new Appointment(null, LocalDateTime.now().minusDays(3), "Dental", p3, v3));
        apptRepo.save(new Appointment(null, LocalDateTime.now().plusDays(1), "Surgery", p1, v2));
    }
}
