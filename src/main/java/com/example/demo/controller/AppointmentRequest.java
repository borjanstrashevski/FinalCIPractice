package com.example.demo.controller;

import java.time.LocalDateTime;

public class AppointmentRequest {
    private LocalDateTime appointmentDateTime;
    private String reason;

    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) { this.appointmentDateTime = appointmentDateTime; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
