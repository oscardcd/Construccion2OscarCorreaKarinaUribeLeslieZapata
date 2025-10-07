package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.Appointment;
import com.mycompany.clinicmanagement.domain.ports.AppointmentPort;
import java.time.LocalDateTime;

public class CreateAppointmentService {
    private final AppointmentPort appointmentPort;

    public CreateAppointmentService(AppointmentPort appointmentPort) {
        this.appointmentPort = appointmentPort;
    }

    public Appointment create(String appointmentId, String patientId, String doctorId,
            LocalDateTime dateTime, String type, String status, String reason) {
        
        Appointment appointment = new Appointment.Builder()
                .appointmentId(appointmentId)
                .patientId(patientId)
                .doctorId(doctorId)
                .dateTime(dateTime)
                .appointmentType(type)
                .status(status)
                .reason(reason)
                .build();
        return appointmentPort.save(appointment);
    }

}

