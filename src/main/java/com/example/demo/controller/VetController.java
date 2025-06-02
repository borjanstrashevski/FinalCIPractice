package com.example.demo.controller;

import com.example.demo.model.Veterinarian;
import com.example.demo.model.Appointment;
import com.example.demo.service.VetService;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetController {
    private final VetService vetService;
    private final AppointmentService appointmentService;

    public VetController(VetService vetService, AppointmentService appointmentService) {
        this.vetService = vetService;
        this.appointmentService = appointmentService;
    }

    @GetMapping public List<Veterinarian> all() { return vetService.findAll(); }
    @GetMapping("/{id}") public Veterinarian one(@PathVariable Long id) { return vetService.findById(id); }
    @PostMapping public Veterinarian create(@RequestBody Veterinarian vet) { return vetService.save(vet); }
    @PutMapping("/{id}") public Veterinarian update(@PathVariable Long id, @RequestBody Veterinarian vet) {
        vet.setId(id); return vetService.save(vet);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { vetService.delete(id); }

    @GetMapping("/{id}/appointments")
    public List<Appointment> vetAppointments(@PathVariable Long id, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDateTime from = localDate.atStartOfDay();
        LocalDateTime to = from.plusDays(1);
        return appointmentService.listVetAppointments(id, from, to);
    }

    @GetMapping("/{id}/appointments/count")
    public long countAppointments(@PathVariable Long id, @RequestParam String from, @RequestParam String to) {
        LocalDateTime fromDate = LocalDateTime.parse(from);
        LocalDateTime toDate = LocalDateTime.parse(to);
        return appointmentService.countVetAppointments(id, fromDate, toDate);
    }
}
