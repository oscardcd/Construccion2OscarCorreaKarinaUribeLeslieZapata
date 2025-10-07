package com.mycompany.clinicmanagement.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.clinicmanagement.domain.models.Appointment;
import com.mycompany.clinicmanagement.adapter.in.validator.AppointmentValidator;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AppointmentBuilder {

    @Autowired
    private AppointmentValidator appointmentValidator;

    public Appointment build(String patientId, String doctorId, LocalDateTime appointmentDateTime, 
                           String reason, String appointmentType, String appointmentStatus) {
        
        String validatedPatientId = appointmentValidator.patientIdValidator(patientId);
        String validatedDoctorId = appointmentValidator.doctorIdValidator(doctorId);
        LocalDateTime validatedDateTime = appointmentValidator.dateTimeValidator(appointmentDateTime);
        String validatedReason = appointmentValidator.reasonValidator(reason);
        String validatedType = appointmentValidator.typeValidator(appointmentType);
        String validatedStatus = appointmentValidator.statusValidator(appointmentStatus);

        Appointment appointment = new Appointment();
        appointment.setId(generateAppointmentId());
        appointment.setPatientId(validatedPatientId);
        appointment.setDoctorId(validatedDoctorId);
        appointment.setAppointmentDateTime(validatedDateTime);
        appointment.setReason(validatedReason);
        appointment.setAppointmentType(validatedType);
        appointment.setAppointmentStatus(validatedStatus);
        
        return appointment;
    }

    public Appointment buildForUpdate(Appointment existingAppointment, LocalDateTime newDateTime, 
                                     String newReason, String newType, String newStatus) {
        
        existingAppointment.setAppointmentDateTime(appointmentValidator.dateTimeValidator(newDateTime));
        existingAppointment.setReason(appointmentValidator.reasonValidator(newReason));
        existingAppointment.setAppointmentType(appointmentValidator.typeValidator(newType));
        existingAppointment.setAppointmentStatus(appointmentValidator.statusValidator(newStatus));
        
        return existingAppointment;
    }

    private String generateAppointmentId() {
        return "APT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
