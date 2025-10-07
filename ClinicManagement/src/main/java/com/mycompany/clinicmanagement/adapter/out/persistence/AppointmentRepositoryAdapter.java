package com.mycompany.clinicmanagement.adapter.out.persistence;

import com.mycompany.clinicmanagement.application.port.out.AppointmentRepositoryPort;
import com.mycompany.clinicmanagement.domain.models.Appointment;
import com.mycompany.clinicmanagement.adapter.out.persistence.entities.AppointmentEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AppointmentRepositoryAdapter implements AppointmentRepositoryPort {

    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentRepositoryAdapter(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity entity = AppointmentEntity.fromDomain(appointment);
        AppointmentEntity savedEntity = appointmentJpaRepository.save(entity);

        return savedEntity.toDomain();
    }

    @Override
    public Optional<Appointment> findById(String id) {
        return appointmentJpaRepository.findById(id)
            .map(AppointmentEntity::toDomain);
    }

    @Override
    public List<Appointment> findByPatientId(String patientId) {
        return appointmentJpaRepository.findByPatientId(patientId).stream()
            .map(AppointmentEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public boolean existsByDoctorAndTimeRange(String doctorId, LocalDateTime startTime, LocalDateTime endTime) {
        List<AppointmentEntity> appointments = appointmentJpaRepository
            .findByDoctorIdAndAppointmentDateTimeBetween(doctorId, startTime, endTime);
        return !appointments.isEmpty();
    }

    @Override
    public Optional<Appointment> findByDoctorAndDateTime(String doctorId, LocalDateTime dateTime) {
        return appointmentJpaRepository.findByDoctorIdAndAppointmentDateTime(doctorId, dateTime)
            .map(AppointmentEntity::toDomain);
    }
}