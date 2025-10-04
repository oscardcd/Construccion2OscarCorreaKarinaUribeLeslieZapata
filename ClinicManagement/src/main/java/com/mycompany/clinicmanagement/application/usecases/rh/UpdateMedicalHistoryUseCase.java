package com.mycompany.clinicmanagement.application.usecases.rh;

import app.domain.models.MedicalRecord;
import app.domain.models.MedicalVisit;

public class UpdateMedicalHistoryUseCase {

    private final MedicalRecord medicalRecord;

    public UpdateMedicalHistoryUseCase(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void execute(String patientId, String date, String newDiagnosis, String newSymptoms) {
        if (patientId == null || patientId.isEmpty()) {
            throw new IllegalArgumentException("El Id del paciente es obligatorio");
        }
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("La fecha de atención es obligatoria");
        }

        //Buscar la visita en el historial del paciente
        MedicalVisit visit = medicalRecord.getVisitByDate(date);
        if (visit == null) {
            throw new IllegalArgumentException("No existe una visita registrada en esa fecha");
        }

        //Actualizar síntomas y diagnóstico
        visit.setDiagnosis(newDiagnosis);
        visit.setSymptoms(newSymptoms);
    }
}
