package com.mycompany.clinicmanagement.domain.models;
import app.domain.models.MedicalRecord;
import app.domain.models.MedicalVisit;
import java.util.List;
import com.mycompany.clinicmanagement.application.usecases.rh.MedicalRecordUseCase;
import com.mycompany.clinicmanagement.domain.models.User;

import java.time.LocalDate;

public class MedicalRecordService {
    private final MedicalRecordUseCase useCase;

    public MedicalRecordService(MedicalRecordUseCase useCase) {
        this.useCase = useCase;
    }

    public void registerMedicalRecord(User doctor, MedicalVisit record) {
        if (doctor == null || !doctor.canCreateMedicalRecords()) {
            throw new SecurityException("El usuario no tiene permisos para crear historia clínica.");
        }
        if (record.getDate().isEmpty(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la historia clínica no puede ser futura.");
        }
        if (record.getDiagnosticOrders() != null && !record.getDiagnosticOrders().isEmpty()) {
            if ((record.getMedicationOrders() != null && !record.getMedicationOrders().isEmpty()) ||
                (record.getProcedureOrders() != null && !record.getProcedureOrders().isEmpty())) {
                throw new IllegalArgumentException("Si se registra una ayuda diagnóstica, no se pueden incluir medicamentos ni procedimientos en el mismo registro.");
            }
        }
        useCase.createRecord(record);
    }

    public MedicalRecord findMedicalRecord(String patientDocumentNumber, LocalDate date) {
        return useCase.getRecord(patientDocumentNumber, date);
    }

    public List<MedicalRecord> findAllMedicalRecords(String patientDocumentNumber) {
        return useCase.getRecordsForPatient(patientDocumentNumber);
    }

    public void updateMedicalRecord(User doctor, MedicalRecord updatedRecord) {
        if (doctor == null || !doctor.canCreateMedicalRecords()) {
            throw new SecurityException("El usuario no tiene permisos para actualizar historia clínica.");
        }
        // Reglas de negocio antes de actualizar (por ejemplo validar que exista)
        MedicalRecord existing = useCase.getRecord(updatedRecord.getPatientId(), updatedRecord.getDate());
        if (existing == null) {
            throw new IllegalArgumentException("No existe una historia clínica en esa fecha para ese paciente.");
        }
        useCase.createRecord(updatedRecord); // en este diseño reusa save para actualizar
    }
}
