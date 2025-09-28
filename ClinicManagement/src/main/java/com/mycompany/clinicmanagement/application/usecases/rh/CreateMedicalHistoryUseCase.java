package com.mycompany.clinicmanagement.application.usecases.rh;
import java.time.LocalDate;
import app.domain.models.MedicalVisit;

//Crea una nueva entrada de historia clínica para un paciente en una fecha específica.
public class CreateMedicalHistoryUseCase {
        private final MedicalVisit medicalVisit;

    public CreateMedicalHistoryUseCase(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    //Guarda la fecha, médico, motivo de consulta, síntomas y diagnóstico.
    public void execute(String patientId, String doctorId, LocalDate date, String symptoms, String diagnosis, String reasonForVisit) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }

        medicalVisit.createHistory(patientId, doctorId,reasonForVisit, date, symptoms, diagnosis);
    }

}






