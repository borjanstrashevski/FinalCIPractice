package com.example.demo.controller;

import com.example.demo.model.Pet;
import com.example.demo.model.Appointment;
import com.example.demo.service.PetService;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;
    private final AppointmentService appointmentService;

    public PetController(PetService petService, AppointmentService appointmentService) {
        this.petService = petService;
        this.appointmentService = appointmentService;
    }

    @GetMapping public List<Pet> all() { return petService.findAll(); }
    @GetMapping("/{id}") public Pet one(@PathVariable Long id) { return petService.findById(id); }
    @PostMapping public Pet create(@RequestBody Pet pet) { return petService.save(pet); }
    @PutMapping("/{id}") public Pet update(@PathVariable Long id, @RequestBody Pet pet) {
        pet.setId(id); return petService.save(pet);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { petService.delete(id); }

    @GetMapping("/{id}/appointments")
    public List<Appointment> petAppointments(@PathVariable Long id) {
        return appointmentService.listPetAppointments(id);
    }

    @GetMapping("/{id}/visits/count")
    public long countVisits(@PathVariable Long id) {
        return appointmentService.countPetVisits(id);
    }
}
