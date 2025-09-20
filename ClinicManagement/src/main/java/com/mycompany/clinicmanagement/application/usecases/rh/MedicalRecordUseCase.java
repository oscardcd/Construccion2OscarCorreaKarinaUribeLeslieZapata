package com.mycompany.clinicmanagement.application.usecases.rh;

public class MedicalRecordUseCase {

}

import com.mycompany.clinicmanagement.domain.models.MedicalRecord;
import com.mycompany.clinicmanagement.domain.models.User;
import com.mycompany.clinicmanagement.domain.ports.MedicalRecordRepositoryPort;
import com.mycompany.clinicmanagement.domain.ports.PatientRepositoryPort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Caso de uso para manejar la historia clínica de pacientes.
 * Según el documento: solo los médicos pueden crear y actualizar historia clínica.
 */
public class ManageMedicalRecordUseCase {
    private final MedicalRecordRepositoryPort recordRepository;
    private final PatientRepositoryPort patientRepository; // Para validar que el paciente existe

    public ManageMedicalRecordUseCase(MedicalRecordRepositoryPort recordRepository,
        PatientRepositoryPort patientRepository) {
        this.recordRepository = recordRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Crea una nueva historia clínica para un paciente.
     */
    public void createRecord(User doctor, MedicalRecord record) {
        // 📌 Validación: Solo médicos pueden crear historia clínica (documento)
        if (doctor == null || !doctor.canCreateMedicalRecords()) {
            throw new SecurityException("Solo un médico puede crear historia clínica.");
        }

        // 📌 Validación: Paciente debe existir (buena práctica)
        if (!patientRepository.existsByDocumentNumber(record.getPatientDocumentNumber())) {
            throw new IllegalArgumentException("El paciente no existe en el sistema.");
        }

        // 📌 Validación: Fecha no puede ser futura (buena práctica)
        if (record.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }

        // 📌 Validación: No permitir duplicados (buena práctica)
        if (recordRepository.findByPatientAndDate(record.getPatientDocumentNumber(), record.getDate()) != null) {
            throw new IllegalStateException("Ya existe una historia clínica para ese paciente en esa fecha.");
        }

        // 📌 Regla de negocio: ayuda diagnóstica no puede coexistir con medicamentos ni procedimientos (documento)
        if (record.getDiagnosticAids() != null && !record.getDiagnosticAids().isEmpty()) {
            if ((record.getMedications() != null && !record.getMedications().isEmpty()) ||
                (record.getProcedures() != null && !record.getProcedures().isEmpty())) {
                throw new IllegalArgumentException("Si se registra una ayuda diagnóstica, no se pueden agregar medicamentos ni procedimientos en el mismo registro.");
            }
        }

        // 📌 Auditoría (buena práctica)
        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        // Guardar registro
        recordRepository.save(record);
    }

    /**
     * Obtiene una historia clínica por paciente y fecha.
     */
    public MedicalRecord getRecord(String patientDocumentNumber, LocalDate date) {
        return recordRepository.findByPatientAndDate(patientDocumentNumber, date);
    }

    /**
     * Lista todas las historias clínicas de un paciente.
     */
    public List<MedicalRecord> getRecordsForPatient(String patientDocumentNumber) {
        return recordRepository.findAllByPatient(patientDocumentNumber);
    }

    /**
     * Actualiza una historia clínica existente.
     * Según el documento: los médicos pueden actualizar registros (por ejemplo, agregar resultados).
     */
    public void updateRecord(User doctor, MedicalRecord updatedRecord) {
        // Validación de permisos
        if (doctor == null || !doctor.canCreateMedicalRecords()) {
            throw new SecurityException("Solo un médico puede actualizar historia clínica.");
        }

        MedicalRecord existing = recordRepository.findByPatientAndDate(
                updatedRecord.getPatientDocumentNumber(), updatedRecord.getDate());

        if (existing == null) {
            throw new IllegalArgumentException("No existe una historia clínica en esa fecha para ese paciente.");
        }

        // 📌 Auditoría
        updatedRecord.setCreatedAt(existing.getCreatedAt());
        updatedRecord.setUpdatedAt(LocalDateTime.now());

        recordRepository.save(updatedRecord);
    }

    /**
     * Elimina una historia clínica (opcional, depende de requerimientos).
     * Normalmente en sistemas reales no se elimina, solo se marca como anulada.
     */
    public void deleteRecord(User doctor, String patientDocumentNumber, LocalDate date) {
        if (doctor == null || !doctor.canCreateMedicalRecords()) {
            throw new SecurityException("Solo un médico puede eliminar historia clínica.");
        }
        MedicalRecord existing = recordRepository.findByPatientAndDate(patientDocumentNumber, date);
        if (existing == null) {
            throw new IllegalArgumentException("No existe la historia clínica que se quiere eliminar.");
        }
        recordRepository.delete(patientDocumentNumber, date);
    }
}
