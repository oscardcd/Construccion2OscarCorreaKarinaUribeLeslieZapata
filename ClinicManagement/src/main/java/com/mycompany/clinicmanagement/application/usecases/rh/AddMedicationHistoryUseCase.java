package com.mycompany.clinicmanagement.application.usecases.rh;

import app.domain.models.MedicalVisit;
import app.domain.models.MedicationOrder;
import java.time.LocalDate;

public class AddMedicationHistoryUseCase {

    private final MedicalVisit medicalVisit;

    public AddMedicationHistoryUseCase(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    //asocia un medicamento en la historia clínica en una fecha específica
    public void execute(String patientId, LocalDate date, MedicationOrder medicationOrder) {
        if (medicationOrder == null) {
            throw new IllegalArgumentException("El medicamento no puede ser nulo");
        }
        if (medicationOrder.getItemNumber() == 0) {
            throw new IllegalArgumentException("El medicamento debe tener número de orden");
        }

        medicalVisit.addMedicationOrder(medicationOrder);
    }
}
