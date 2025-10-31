/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.domain.ports;

/**
 *
 * @author USUARIO
 */
import app.domain.models.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentPort {
    Appointment save(Appointment appointment) throws Exception;
    Appointment findById(String id) throws Exception;
    List<Appointment> findAll() throws Exception;
    void delete(String id) throws Exception;

    Appointment findByDoctorAndDate(String doctorId, LocalDateTime dateTime) throws Exception;
    Appointment findByPatientAndDate(String patientId, LocalDateTime dateTime) throws Exception;
}