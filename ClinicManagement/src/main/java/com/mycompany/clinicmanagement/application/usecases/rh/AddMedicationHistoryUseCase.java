package com.mycompany.clinicmanagement.application.usecases.rh;
import app.domain.models.MedicalVisit;
import app.domain.models.Medication;
import java.time.LocalDate;

public class AddMedicationHistoryUseCase {
    private final Medication medication;

    public AddMedicationHistoryUseCase(Medication med)) {
        this.medication = medication;
    }

    //Asocia un medicamento en la historia clínica en una fecha especifica
    public void execute(String patientId, LocalDate date, Medication medication) {
        medicalVisit.Medication(patientId, date, medication);
    }
}
