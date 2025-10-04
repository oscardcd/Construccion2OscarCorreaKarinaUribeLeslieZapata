package com.mycompany.clinicmanagement.application.services;

import app.domain.models.MedicalRecord;
import app.domain.models.MedicalVisit;
import com.mycompany.clinicmanagement.application.usecases.rh.*;

import java.util.Collection;

public class MedicalRecordService {

    private final MedicalRecord medicalRecord;

    public MedicalRecordService(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public void createHistory(String doctorId, String date, String reason, String symptoms, String diagnosis) {
        new CreateMedicalHistoryUseCase(medicalRecord)
                .execute(doctorId, date, reason, symptoms, diagnosis);
    }

    public void addVisit(String date, MedicalVisit visit) {
        new Add(medicalRecord).execute(date, visit);
    }

    public void updateHistory(String date, String diagnosis, String symptoms) {
        new UpdateMedicalHistoryUseCase(medicalRecord).execute(date, diagnosis, symptoms);
    }

    public Collection<MedicalVisit> getPatientHistory() {
        return new GetPatientHistoryUseCase(medicalRecord).execute();
    }
}
