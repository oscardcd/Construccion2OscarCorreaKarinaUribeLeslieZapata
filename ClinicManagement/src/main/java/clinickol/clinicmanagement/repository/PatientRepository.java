package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de base de datos de Pacientes
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Buscar paciente por email
     */
    Optional<Patient> findByEmail(String email);

    /**
     * Buscar paciente por documento
     */
    Optional<Patient> findByDocumento(String documento);

    /**
     * Buscar pacientes activos
     */
    List<Patient> findByActivoTrue();

    /**
     * Buscar pacientes inactivos
     */
    List<Patient> findByActivoFalse();

    /**
     * Buscar pacientes por nombre (ignorando mayúsculas/minúsculas)
     */
    List<Patient> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Buscar pacientes por apellido (ignorando mayúsculas/minúsculas)
     */
    List<Patient> findByApellidoContainingIgnoreCase(String apellido);

    /**
     * Buscar pacientes por nombre completo (ignorando mayúsculas/minúsculas)
     */
    @Query("SELECT p FROM Patient p WHERE LOWER(CONCAT(p.nombre, ' ', p.apellido)) LIKE LOWER(CONCAT('%', :nombreCompleto, '%'))")
    List<Patient> findByNombreCompletoContainingIgnoreCase(@Param("nombreCompleto") String nombreCompleto);

    /**
     * Buscar pacientes por género
     */
    List<Patient> findByGenero(clinickol.clinicmanagement.model.Genero genero);

    /**
     * Buscar pacientes por ciudad
     */
    List<Patient> findByCiudad(String ciudad);

    /**
     * Buscar pacientes nacidos en un rango de fechas
     */
    List<Patient> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Buscar pacientes por edad (aproximada)
     */
    @Query("SELECT p FROM Patient p WHERE YEAR(CURRENT_DATE) - YEAR(p.fechaNacimiento) = :edad")
    List<Patient> findByEdad(@Param("edad") int edad);

    /**
     * Buscar pacientes con alergias específicas
     */
    @Query("SELECT p FROM Patient p WHERE LOWER(p.alergias) LIKE LOWER(CONCAT('%', :alergia, '%'))")
    List<Patient> findByAlergiasContaining(@Param("alergia") String alergia);

    /**
     * Contar pacientes activos
     */
    long countByActivoTrue();

    /**
     * Contar pacientes por género
     */
    long countByGenero(clinickol.clinicmanagement.model.Genero genero);

    /**
     * Verificar si existe un paciente con el email dado
     */
    boolean existsByEmail(String email);

    /**
     * Verificar si existe un paciente con el documento dado
     */
    boolean existsByDocumento(String documento);
}
