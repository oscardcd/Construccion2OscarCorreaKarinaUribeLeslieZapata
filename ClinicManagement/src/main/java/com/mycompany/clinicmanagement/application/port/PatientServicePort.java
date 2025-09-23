package com.mycompany.clinicmanagement.application.port;

import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) para el servicio de pacientes
 * Define las operaciones de negocio para la gestión de pacientes
 */
public interface PatientServicePort {

    /**
     * Registra un nuevo paciente (solo Personal Administrativo)
     * 
     * @param patient      Paciente a registrar
     * @param registeredBy Usuario que registra (debe ser Personal Administrativo)
     * @return Paciente registrado
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     * @throws IllegalStateException    Si ya existe un paciente con la misma cédula
     */
    Patient registerPatient(Patient patient, User registeredBy);

    /**
     * Actualiza los datos de un paciente (solo Personal Administrativo)
     * 
     * @param patient   Paciente con los datos actualizados
     * @param updatedBy Usuario que actualiza (debe ser Personal Administrativo)
     * @return Paciente actualizado
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     */
    Patient updatePatient(Patient patient, User updatedBy);

    /**
     * Busca un paciente por ID
     * 
     * @param id ID del paciente
     * @return Optional con el paciente si existe
     */
    Optional<Patient> findPatientById(Long id);

    /**
     * Busca un paciente por número de cédula
     * 
     * @param documentNumber Número de cédula
     * @return Optional con el paciente si existe
     */
    Optional<Patient> findPatientByDocumentNumber(String documentNumber);

    /**
     * Obtiene todos los pacientes
     * 
     * @return Lista de todos los pacientes
     */
    List<Patient> getAllPatients();

    /**
     * Busca pacientes por nombre (búsqueda parcial)
     * 
     * @param name Nombre o parte del nombre a buscar
     * @return Lista de pacientes que coinciden con el nombre
     */
    List<Patient> searchPatientsByName(String name);

    /**
     * Obtiene pacientes por género
     * 
     * @param gender Género de los pacientes a buscar
     * @return Lista de pacientes con el género especificado
     */
    List<Patient> getPatientsByGender(Patient.Gender gender);

    /**
     * Obtiene pacientes con póliza de seguro activa
     * 
     * @return Lista de pacientes con seguro activo
     */
    List<Patient> getPatientsWithActiveInsurance();

    /**
     * Obtiene pacientes sin seguro médico
     * 
     * @return Lista de pacientes sin seguro
     */
    List<Patient> getPatientsWithoutInsurance();

    /**
     * Busca pacientes por rango de edad
     * 
     * @param minAge Edad mínima
     * @param maxAge Edad máxima
     * @return Lista de pacientes en el rango de edad
     */
    List<Patient> getPatientsByAgeRange(int minAge, int maxAge);

    /**
     * Actualiza la información de seguro de un paciente
     * 
     * @param patientDocumentNumber Cédula del paciente
     * @param insuranceCompany      Compañía de seguros
     * @param policyNumber          Número de póliza
     * @param isActive              Si la póliza está activa
     * @param expirationDate        Fecha de vencimiento
     * @param updatedBy             Usuario que actualiza
     * @return Paciente actualizado
     */
    Patient updateInsuranceInfo(String patientDocumentNumber, String insuranceCompany,
            String policyNumber, boolean isActive,
            java.time.LocalDate expirationDate, User updatedBy);

    /**
     * Actualiza la información de contacto de emergencia de un paciente
     * 
     * @param patientDocumentNumber Cédula del paciente
     * @param contactName           Nombre del contacto
     * @param relation              Relación con el paciente
     * @param phone                 Teléfono del contacto
     * @param updatedBy             Usuario que actualiza
     * @return Paciente actualizado
     */
    Patient updateEmergencyContact(String patientDocumentNumber, String contactName,
            String relation, String phone, User updatedBy);

    /**
     * Valida los datos de un paciente antes de guardarlo
     * 
     * @param patient Paciente a validar
     * @throws IllegalArgumentException Si los datos no son válidos
     */
    void validatePatientData(Patient patient);

    /**
     * Verifica si un paciente tiene seguro activo
     * 
     * @param patientDocumentNumber Cédula del paciente
     * @return true si tiene seguro activo
     */
    boolean hasActiveInsurance(String patientDocumentNumber);

    /**
     * Obtiene estadísticas de pacientes
     * 
     * @return Mapa con estadísticas de pacientes
     */
    java.util.Map<String, Object> getPatientStatistics();

    /**
     * Obtiene estadísticas de seguros
     * 
     * @return Mapa con estadísticas de seguros
     */
    java.util.Map<String, Object> getInsuranceStatistics();

    /**
     * Verifica si un usuario puede acceder a información de pacientes
     * 
     * @param user Usuario a verificar
     * @return true si puede acceder
     */
    boolean canAccessPatientInfo(User user);

    /**
     * Obtiene el historial de cambios de un paciente
     * 
     * @param patientDocumentNumber Cédula del paciente
     * @return Lista de cambios realizados al paciente
     */
    List<java.util.Map<String, Object>> getPatientChangeHistory(String patientDocumentNumber);

    /**
     * Exporta los datos de un paciente
     * 
     * @param patientDocumentNumber Cédula del paciente
     * @return Datos del paciente en formato exportable
     */
    java.util.Map<String, Object> exportPatientData(String patientDocumentNumber);
}
