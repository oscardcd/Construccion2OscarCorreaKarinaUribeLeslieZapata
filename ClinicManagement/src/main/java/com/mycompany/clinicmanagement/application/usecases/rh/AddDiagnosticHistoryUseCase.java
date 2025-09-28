package com.mycompany.clinicmanagement.application.usecases.rh;
import java.time.LocalDate;
import app.domain.models.DiagnosticOrder;
import app.domain.models.MedicalVisit;

public class AddDiagnosticHistoryUseCase {

    public AddDiagnosticHistoryUseCase(MedicalVisit medicalVisit) {
    }

    public void execute(String patientId, LocalDate date, DiagnosticOrder aid) {
        if (aid == null) {
            throw new IllegalArgumentException("La ayuda diagnóstica no puede ser nula");
        }
        if (aid == null || aid.getItemNumber() == 0) {
        throw new IllegalArgumentException("La ayuda diagnóstica debe tener número de orden");
        }



        // Regla: si se receta ayuda diagnóstica, no puede haber medicamentos o procedimientos aún
        // (esta validación puedes implementarla en MedicalHistoryServiceImpl)
        
        MedicalVisit.addProcedure(patientId, date, aid);
    }
}
