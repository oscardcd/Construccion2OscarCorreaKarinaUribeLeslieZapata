package com.mycompany.clinicmanagement.infrastructure.repositoty;

import com.mycompany.clinicmanagement.infrastructure.adapters.*;
import com.mycompany.clinicmanagement.domain.models.Appointment;
import com.mycompany.clinicmanagement.domain.ports.AppointmentPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRepository implements AppointmentPort {
    private final List<Appointment> appointments = new ArrayList<>();

@Override
    public Appointment save(Appointment appointment) {
        appointments.add(appointment);
        return appointment;
    }

@Override
    public Appointment findById(String appointmentId) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }

@Override
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

@Override
    public List<Appointment> findByPatient(String patientId) {
        return appointments.stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .toList();
    }

@Override
    public void delete(String appointmentId) {
        appointments.removeIf(a -> a.getAppointmentId().equals(appointmentId));
    }
}
