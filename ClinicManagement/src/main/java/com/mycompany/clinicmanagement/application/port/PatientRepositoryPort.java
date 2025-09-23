package com.mycompany.clinicmanagement.application.port;

import com.mycompany.clinicmanagement.domain.models.Patient;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) para el repositorio de pacientes
 * Define las operaciones de persistencia para la entidad Patient
 */
public interface PatientRepositoryPort {

    /**
     * Guarda un paciente en el repositorio
     * 
     * @param patient Paciente a guardar
     * @return Paciente guardado con ID asignado
     */
    Patient save(Patient patient);

    /**
     * Busca un paciente por su ID
     * 
     * @param id ID del paciente
     * @return Optional con el paciente si existe
     */
    Optional<Patient> findById(Long id);

    /**
     * Busca un paciente por su número de cédula
     * 
     * @param documentNumber Número de cédula
     * @return Optional con el paciente si existe
     */
    Optional<Patient> findByDocumentNumber(String documentNumber);

    /**
     * Obtiene todos los pacientes
     * 
     * @return Lista de todos los pacientes
     */
    List<Patient> findAll();

    /**
     * Obtiene pacientes por género
     * 
     * @param gender Género de los pacientes a buscar
     * @return Lista de pacientes con el género especificado
     */
    List<Patient> findByGender(Patient.Gender gender);

    /**
     * Obtiene pacientes con póliza de seguro activa
     * 
     * @return Lista de pacientes con seguro activo
     */
    List<Patient> findWithActiveInsurance();

    /**
     * Obtiene pacientes sin seguro médico
     * 
     * @return Lista de pacientes sin seguro
     */
    List<Patient> findWithoutInsurance();

    /**
     * Busca pacientes por nombre (búsqueda parcial)
     * 
     * @param name Nombre o parte del nombre a buscar
     * @return Lista de pacientes que coinciden con el nombre
     */
    List<Patient> findByNameContaining(String name);

    /**
     * Busca pacientes por rango de edad
     * 
     * @param minAge Edad mínima
     * @param maxAge Edad máxima
     * @return Lista de pacientes en el rango de edad
     */
    List<Patient> findByAgeRange(int minAge, int maxAge);

    /**
     * Verifica si existe un paciente con el número de cédula dado
     * 
     * @param documentNumber Número de cédula
     * @return true si existe, false en caso contrario
     */
    boolean existsByDocumentNumber(String documentNumber);

    /**
     * Verifica si existe un paciente con el email dado
     * 
     * @param email Email del paciente
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);

    /**
     * Actualiza un paciente existente
     * 
     * @param patient Paciente con los datos actualizados
     * @return Paciente actualizado
     */
    Patient update(Patient patient);

    /**
     * Elimina un paciente por su ID
     * 
     * @param id ID del paciente a eliminar
     */
    void deleteById(Long id);

    /**
     * Elimina un paciente por su número de cédula
     * 
     * @param documentNumber Número de cédula del paciente a eliminar
     */
    void deleteByDocumentNumber(String documentNumber);

    /**
     * Cuenta el total de pacientes
     * 
     * @return Número total de pacientes
     */
    long count();

    /**
     * Cuenta pacientes por género
     * 
     * @param gender Género de los pacientes a contar
     * @return Número de pacientes con el género especificado
     */
    long countByGender(Patient.Gender gender);

    /**
     * Cuenta pacientes con seguro activo
     * 
     * @return Número de pacientes con seguro activo
     */
    long countWithActiveInsurance();
}
