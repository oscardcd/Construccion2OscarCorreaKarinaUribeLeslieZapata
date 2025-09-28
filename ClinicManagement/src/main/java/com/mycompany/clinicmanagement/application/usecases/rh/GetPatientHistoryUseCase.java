package com.mycompany.clinicmanagement.application.usecases.rh;
import java.util.List;

import app.domain.models.MedicalRecord;

//Consulta la historia clínica de un paciente.
public class GetPatientHistoryUseCase {
    
    private final MedicalRecord medicalRecord;

    public GetPatientHistoryUseCase(MedicalRecord medicalRecord;) {
        this.medicalRecord = medicalRecord;
    }

    //Arroja una lista de todas las visitas que ha tenido 
    public List<MedicalRecord> execute(String patientId) {
        return medicalRecord.getVisits(patientId);
    }

}






