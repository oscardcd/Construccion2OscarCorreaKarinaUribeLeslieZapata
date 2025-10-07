/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicmanagement.adapter.in.rest.controllers;

import com.mycompany.clinicmanagement.application.usecases.ScheduleAppointmentUseCase;
import com.mycompany.clinicmanagement.domain.models.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycompany.clinicmanagement.application.usecases.ScheduleAppointmentUseCase;
import com.mycompany.clinicmanagement.domain.models.Appointment;

@RestController
@RequestMapping("/appointments")    //ruta base del controlador
public class AppointmentController {
    private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;
    public AppointmentController(ScheduleAppointmentUseCase scheduleAppointmentUseCase) {
        this.scheduleAppointmentUseCase = scheduleAppointmentUseCase;
    }
    public static class AppointmentRequest {
        public String appointmentId;
        public String patientId;
        public String doctorId;
        public String dateTime;
        public String reason;
        public String type;
    }

@PostMapping("/schedule") 
    public ResponseEntity<Appointment> scheduleAppointment(@RequestBody AppointmentRequest request) { //convierte el cuerpo JSON en un objeto java
        Appointment appointment = scheduleAppointmentUseCase.schedule(
                request.appointmentId,
                request.patientId,
                request.doctorId,
                request.dateTime,
                request.reason,
                request.type
        );
        return ResponseEntity.ok(appointment);
    }

@GetMapping("/all")
    public ResponseEntity<?> listAppointments() {
        return ResponseEntity.ok(scheduleAppointmentUseCase
                .getAppointmentPort()
                .findAll());
    }
}

