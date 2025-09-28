package com.mycompany.clinicmanagement.application.usecases.rh;
import app.domain.models.MedicalVisit;
import app.domain.models.ProcedureOrder;

import java.time.LocalDate;

public class AddProcedureHistoryUseCase {

    private final MedicalVisit medicalVisit;

    public AddProcedureHistoryUseCase(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    public void execute(String patientId, LocalDate date, ProcedureOrder procedureOrder) {
        if (procedureOrder == null) {
            throw new IllegalArgumentException("El procedimiento no puede ser nulo");
        }
        if (procedureOrder.getItemNumber() == 0) {
            throw new IllegalArgumentException("El procedimiento debe tener número de orden");
        }

        medicalVisit.Procedure(patientId, date, procedureOrder);
    }
}

