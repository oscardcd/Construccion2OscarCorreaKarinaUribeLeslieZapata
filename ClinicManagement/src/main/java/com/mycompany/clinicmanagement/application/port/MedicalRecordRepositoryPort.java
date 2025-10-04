package com.mycompany.clinicmanagement.application.port;
import app.domain.models.MedicalRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) para el repositorio de historias clínicas.
 * Define las operaciones de persistencia para la entidad MedicalRecord.
 */
public interface MedicalRecordRepositoryPort {

    /**
     * Guarda una historia clínica en el repositorio.
     *
     * @param record Historia clínica a guardar
     * @return Historia clínica guardada
     */
    MedicalRecord save(MedicalRecord record);

    /**
     * Busca una historia clínica por paciente y fecha.
     *
     * @param patientDocumentNumber Número de documento del paciente
     * @param date Fecha de la historia clínica
     * @return Optional con la historia clínica si existe
     */
    Optional<MedicalRecord> findByPatientAndDate(String patientDocumentNumber, LocalDate date);

    /**
     * Lista todas las historias clínicas de un paciente.
     *
     * @param patientDocumentNumber Número de documento del paciente
     * @return Lista de historias clínicas del paciente
     */
    List<MedicalRecord> findAllByPatient(String patientDocumentNumber);

    /**
     * Elimina una historia clínica por paciente y fecha.
     *
     * @param patientDocumentNumber Número de documento del paciente
     * @param date Fecha de la historia clínica
     */
    void delete(String patientDocumentNumber, LocalDate date);

    /*
     * Verifica si existe una historia clínica para un paciente en una fecha específica.
     *
     * @param patientDocumentNumber Número de documento del paciente
     * @param date Fecha de la historia clínica
     * @return true si existe, false en caso contrario
     */
    boolean existsByPatientAndDate(String patientDocumentNumber, LocalDate date);
}
