
package com.mycompany.clinicmanagement.domain.ports;

import com.mycompany.clinicmanagement.domain.models.Appointment;
import java.util.List;

public interface AppointmentPort {
    Appointment save(Appointment appointment);
    Appointment findById(String appointmentId);
    List<Appointment> findAll();
    List<Appointment> findByPatient(String patientId);
    void delete(String appointmentId);
}
