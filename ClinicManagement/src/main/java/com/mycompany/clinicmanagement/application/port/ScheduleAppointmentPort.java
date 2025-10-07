
package com.mycompany.clinicmanagement.application.port;

import com.mycompany.clinicmanagement.domain.models.Appointment;

public interface ScheduleAppointmentPort {
    Appointment schedule(String patientId, String doctorId, 
                        String appointmentDateTime, String reason, String appointmentType);
}

