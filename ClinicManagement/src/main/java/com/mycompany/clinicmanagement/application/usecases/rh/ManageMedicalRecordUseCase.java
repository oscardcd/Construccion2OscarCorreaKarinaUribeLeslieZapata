package com.mycompany.clinicmanagement.application.usecases.rh;

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

    // ================== Dependencias ==================
    private final MedicalRecordRepositoryPort recordRepository;
    private final PatientRepositoryPort patientRepository;

    // ================== Constructor ==================
    public ManageMedicalRecordUseCase(MedicalRecordRepositoryPort recordRepository,
                                    PatientRepositoryPort patientRepository) {
        this.recordRepository = recordRepository;
        this.patientRepository = patientRepository;
    }

    // ================== Casos de Uso ==================

    /**
     * Crea una nueva historia clínica para un paciente.
     */
    public void createRecord(User doctor, MedicalRecord record) {
        validateDoctor(doctor);

        if (!patientRepository.existsByDocumentNumber(record.getPatientDocumentNumber())) {
            throw new IllegalArgumentException("El paciente no existe en el sistema.");
        }
        if (record.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }
        if (recordRepository.findByPatientAndDate(record.getPatientDocumentNumber(), record.getDate()) != null) {
            throw new IllegalStateException("Ya existe una historia clínica para ese paciente en esa fecha.");
        }

        // Regla de negocio: ayuda diagnóstica no puede coexistir con medicamentos ni procedimientos
        if (record.getDiagnosticAids() != null && !record.getDiagnosticAids().isEmpty()) {
            if ((record.getMedications() != null && !record.getMedications().isEmpty()) ||
                (record.getProcedures() != null && !record.getProcedures().isEmpty())) {
                throw new IllegalArgumentException(
                        "Si se registra una ayuda diagnóstica, no se pueden agregar medicamentos ni procedimientos en el mismo registro."
                );
            }
        }

        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

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
     */
    public void updateRecord(User doctor, MedicalRecord updatedRecord) {
        validateDoctor(doctor);

        MedicalRecord existing = recordRepository.findByPatientAndDate(
                updatedRecord.getPatientDocumentNumber(), updatedRecord.getDate());

        if (existing == null) {
            throw new IllegalArgumentException("No existe una historia clínica en esa fecha para ese paciente.");
        }

        updatedRecord.setCreatedAt(existing.getCreatedAt());
        updatedRecord.setUpdatedAt(LocalDateTime.now());

        recordRepository.save(updatedRecord);
    }

    /**
     * Elimina una historia clínica (en sistemas reales suele marcarse como anulada).
     */
    public void deleteRecord(User doctor, String patientDocumentNumber, LocalDate date) {
        validateDoctor(doctor);

        MedicalRecord existing = recordRepository.findByPatientAndDate(patientDocumentNumber, date);
        if (existing == null) {
            throw new IllegalArgumentException("No existe la historia clínica que se quiere eliminar.");
        }

        recordRepository.delete(patientDocumentNumber, date);
    }

    // ================== Métodos Helper ==================

    private void validateDoctor(User doctor) {
        if (doctor == null || !doctor.canCreateMedicalRecords()) {
            throw new SecurityException("Solo un médico puede gestionar historias clínicas.");
        }
    }
}
