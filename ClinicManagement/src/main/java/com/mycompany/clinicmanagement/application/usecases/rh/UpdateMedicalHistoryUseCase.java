package com.mycompany.clinicmanagement.application.usecases.rh;
import java.time.LocalDate;
import app.domain.models.MedicalVisit;

public class UpdateMedicalHistoryUseCase {

    private final MedicalVisit medicalVisit;

    public UpdateMedicalHistoryUseCase(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    public void execute(String patientId, LocalDate date, String newDiagnosis, String newSymptoms) {
        if (patientId == null || patientId.isEmpty()) {
            throw new IllegalArgumentException("El Id del paciente es obligatorio");
        }
        if (date == null) {
            throw new IllegalArgumentException("La fecha de atención es obligatoria");
        }
        // Valida que no se actualice a futuro
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }

        medicalVisit.updateHistory(patientId, date, newDiagnosis, newSymptoms);
    }
}
