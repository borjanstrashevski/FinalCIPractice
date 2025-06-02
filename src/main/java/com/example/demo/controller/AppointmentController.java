package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) { this.appointmentService = appointmentService; }

    @PostMapping("/vets/{vetId}/pets/{petId}/appointments")
    public Appointment book(@PathVariable Long vetId, @PathVariable Long petId,
                            @RequestBody AppointmentRequest req) {
        return appointmentService.bookAppointment(vetId, petId, req.getAppointmentDateTime(), req.getReason());
    }

    @DeleteMapping("/appointments/{id}")
    public void cancel(@PathVariable Long id) { appointmentService.cancelAppointment(id); }
}
