package com.mycompany.clinicmanagement.application.usecases.rh;

import java.util.ArrayList;
import java.util.List;

import app.domain.models.MedicalRecord;
import app.domain.models.MedicalVisit;

//Consulta la historia clínica de un paciente.
public class GetPatientHistoryUseCase {

    private final MedicalRecord medicalRecord;

    public GetPatientHistoryUseCase(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    //Retorna una lista con todas las visitas que ha tenido el paciente
    public List<MedicalVisit> execute(String patientId) {
        if (medicalRecord == null || !medicalRecord.getPatientId().equals(patientId)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(medicalRecord.getVisits().values());
    }
}
