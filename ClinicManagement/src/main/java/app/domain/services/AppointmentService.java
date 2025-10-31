/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.domain.services;

/**
 *
 * @author USUARIO
 */

import app.domain.models.Appointment;
import app.domain.ports.AppointmentPort;

public class AppointmentService {

    private final AppointmentPort appointmentPort;

    public AppointmentService(AppointmentPort appointmentPort) {
        this.appointmentPort = appointmentPort;
    }

    public void schedule(Appointment appointment) throws Exception {
        if (appointmentPort.findByDoctorAndDate(appointment.getDoctorId(), appointment.getDateTime()) != null) {
            throw new Exception("The doctor already has an appointment at that time.");
        }

        if (appointmentPort.findByPatientAndDate(appointment.getPatientId(), appointment.getDateTime()) != null) {
            throw new Exception("The patient already has an appointment at that time");
        }

        appointmentPort.save(appointment);
    }
}
