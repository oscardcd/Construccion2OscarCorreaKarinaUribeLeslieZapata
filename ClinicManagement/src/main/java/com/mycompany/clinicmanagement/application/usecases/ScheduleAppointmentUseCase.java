
package com.mycompany.clinicmanagement.application.usecases;

import com.mycompany.clinicmanagement.domain.models.Appointment;
import com.mycompany.clinicmanagement.domain.ports.AppointmentPort;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ScheduleAppointmentUseCase {
    private final AppointmentPort appointmentPort;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public ScheduleAppointmentUseCase(AppointmentPort appointmentPort) {
        this.appointmentPort = appointmentPort;
    }
    
    public Appointment schedule(String appointmentId, String patientId, String doctorId,
            String dateTimeStr, String reason, String type) {
        LocalDateTime dateTime = parseDateTime(dateTimeStr);
        validateDoctorAvailability(doctorId, dateTime);
        
        Appointment appointment = new Appointment.Builder()
                .appointmentId(appointmentId)
                .patientId(patientId)
                .doctorId(doctorId)
                .dateTime(dateTime)
                .appointmentType(type)
                .status("SCHEDULED")
                .reason(reason)
                .build();
        return appointmentPort.save(appointment);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format. Expected: yyyy-MM-dd HH:mm");
    }
}
    private void validateDoctorAvailability(String doctorId, LocalDateTime dateTime) {
        Optional<Appointment> conflict = appointmentPort.findAll().stream()
                .filter(a -> a.getDoctorId().equals(doctorId) && a.getDateTime().equals(dateTime))
                .findFirst();
        if (conflict.isPresent())
            throw new IllegalArgumentException("Doctor already has an appointment at that time");
    } 

}
