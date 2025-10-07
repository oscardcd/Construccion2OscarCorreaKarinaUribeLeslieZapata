package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.MedicalVisit;
import com.mycompany.clinicmanagement.domain.ports.MedicalVisitPort;
import java.time.LocalDateTime;

public class CreateMedicalVisitService {
    private final MedicalVisitPort visitPort;
    public CreateMedicalVisitService(MedicalVisitPort visitPort) {
        this.visitPort = visitPort;
    }

    public MedicalVisit create(String visitId, String patientId, String doctorId,
            LocalDateTime visitDate, String notes,
            String orderId, String vitalId) {
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
