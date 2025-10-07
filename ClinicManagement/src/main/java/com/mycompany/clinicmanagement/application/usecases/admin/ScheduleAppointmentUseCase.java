
package com.mycompany.clinicmanagement.application.usecases.admin;


import com.mycompany.clinicmanagement.application.port.ScheduleAppointmentPort;
import com.mycompany.clinicmanagement.application.port.AppointmentRepositoryPort;
import com.mycompany.clinicmanagement.application.port.PatientRepositoryPort;
import com.mycompany.clinicmanagement.application.port.out.DoctorRepositoryPort;
import com.mycompany.clinicmanagement.domain.models.Appointment;
import com.mycompany.clinicmanagement.adapter.in.builder.AppointmentBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class ScheduleAppointmentUseCase implements ScheduleAppointmentPort {

    private final AppointmentRepositoryPort appointmentRepository;
    private final PatientRepositoryPort patientRepository;
    private final DoctorRepositoryPort doctorRepository;
    private final AppointmentBuilder appointmentBuilder;
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ScheduleAppointmentUseCase(AppointmentRepositoryPort appointmentRepository,
                                     PatientRepositoryPort patientRepository,
                                     DoctorRepositoryPort doctorRepository,
                                     AppointmentBuilder appointmentBuilder) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentBuilder = appointmentBuilder;
    }

    @Override
    public Appointment schedule(String patientId, String doctorId, 
                               String appointmentDateTimeStr, String reason, String appointmentType) {
        
        LocalDateTime appointmentDateTime = parseDateTime(appointmentDateTimeStr, "appointment date time");
        
        validateEntitiesExistence(patientId, doctorId);
        
        validateDoctorAvailability(doctorId, appointmentDateTime);
       
        Appointment newAppointment = appointmentBuilder.build(
            patientId, doctorId, appointmentDateTime, reason, appointmentType, "SCHEDULED");
        
        return appointmentRepository.save(newAppointment);
    }

    private LocalDateTime parseDateTime(String dateTimeStr, String fieldName) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid format for " + fieldName + 
                                             ". Expected format: yyyy-MM-dd HH:mm", e);
        }
    }

    private void validateEntitiesExistence(String patientId, String doctorId) {
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalArgumentException("Patient not found with id: " + patientId);
        }
        
        if (!doctorRepository.existsById(doctorId)) {
            throw new IllegalArgumentException("Doctor not found with id: " + doctorId);
        }
    }

    private void validateDoctorAvailability(String doctorId, LocalDateTime appointmentDateTime) {
        boolean hasConflict = appointmentRepository.findByDoctorAndDateTime(doctorId, appointmentDateTime)
            .isPresent();
            
        if (hasConflict) {
            throw new IllegalArgumentException("Doctor already has an appointment at the requested time");
        }
    }
}
