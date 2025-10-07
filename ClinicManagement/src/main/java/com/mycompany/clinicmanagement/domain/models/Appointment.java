package com.mycompany.clinicmanagement.domain.models;
import java.time.LocalDateTime;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDateTime dateTime;
    private String appointmentType;
    private String status;
    private String reason;

    private Appointment() {}

    public static class Builder {
        private Appointment appointment;

        public Builder() {
            this.appointment = new Appointment();
        }

        public Builder appointmentId(String appointmentId) {
            appointment.appointmentId = appointmentId;
            return this;
        }

        public Builder patientId(String patientId) {
            appointment.patientId = patientId;
            return this;
        }

        public Builder doctorId(String doctorId) {
            appointment.doctorId = doctorId;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            appointment.dateTime = dateTime;
            return this;
        }

        public Builder appointmentType(String appointmentType) {
            appointment.appointmentType = appointmentType;
            return this;
        }

        public Builder status(String status) {
            appointment.status = status;
            return this;
        }

        public Builder reason(String reason) {
            appointment.reason = reason;
            return this;
        }

        public Appointment build() {
            if (appointment.appointmentId == null || appointment.appointmentId.trim().isEmpty()) {
                throw new IllegalStateException("Appointment ID is required");
            }
            if (appointment.patientId == null || appointment.patientId.trim().isEmpty()) {
                throw new IllegalStateException("Patient ID is required");
            }
            return appointment;
        }
    }

    // Getters
    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getAppointmentType() { return appointmentType; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }
}