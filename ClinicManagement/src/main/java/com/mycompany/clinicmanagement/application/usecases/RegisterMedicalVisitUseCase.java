package com.mycompany.clinicmanagement.application.usecases;


import com.mycompany.clinicmanagement.domain.models.MedicalVisit;
import com.mycompany.clinicmanagement.domain.ports.MedicalVisitPort;
import java.time.LocalDateTime;
import java.util.Optional;

public class RegisterMedicalVisitUseCase {
    private final MedicalVisitPort visitPort;


    public RegisterMedicalVisitUseCase(MedicalVisitPort visitPort) {
        this.visitPort = visitPort;
    }

    public MedicalVisit register(String visitId, String patientId, String doctorId,
            LocalDateTime visitDate, String notes,
            String orderId, String vitalId) {
        
        Optional<MedicalVisit> existing = visitPort.findAll().stream()
                .filter(v -> v.getVisitId().equals(visitId))
                .findFirst();
        
        if (existing.isPresent())
            throw new IllegalArgumentException("Visit already exists with ID: " + visitId);
        
        MedicalVisit visit = new MedicalVisit.Builder()
                .visitId(visitId)
                .patientId(patientId)
                .doctorId(doctorId)
                .visitDate(visitDate)
                .notes(notes)
                .orderId(orderId)
                .vitalId(vitalId)
                .build();
        return visitPort.save(visit);
    }
}
