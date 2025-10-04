package com.mycompany.clinicmanagement.application.usecases.rh;

import java.time.LocalDate;
import app.domain.models.DiagnosticOrder;
import app.domain.models.MedicalVisit;

public class AddDiagnosticHistoryUseCase {

    private final MedicalVisit medicalVisit;

    public AddDiagnosticHistoryUseCase(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    public void execute(String patientId, LocalDate date, DiagnosticOrder aid) {
        if (aid == null) {
            throw new IllegalArgumentException("La ayuda diagnóstica no puede ser nula");
        }
        if (aid.getItemNumber() == 0) {
            throw new IllegalArgumentException("La ayuda diagnóstica debe tener número de orden");
        }

        medicalVisit.addDiagnosticOrder(aid);
    }
}
