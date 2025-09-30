package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByDocumento(String documento);

    List<Patient> findByActivoTrue();

    List<Patient> findByActivoFalse();

    List<Patient> findByNombreContainingIgnoreCase(String nombre);

    List<Patient> findByApellidoContainingIgnoreCase(String apellido);

    @Query("SELECT p FROM Patient p WHERE LOWER(CONCAT(p.nombre, ' ', p.apellido)) LIKE LOWER(CONCAT('%', :nombreCompleto, '%'))")
    List<Patient> findByNombreCompletoContainingIgnoreCase(@Param("nombreCompleto") String nombreCompleto);

    List<Patient> findByGenero(clinickol.clinicmanagement.domain.model.Gender genero);

    List<Patient> findByCiudad(String ciudad);

    List<Patient> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT p FROM Patient p WHERE YEAR(CURRENT_DATE) - YEAR(p.fechaNacimiento) = :edad")
    List<Patient> findByEdad(@Param("edad") int edad);

    @Query("SELECT p FROM Patient p WHERE LOWER(p.alergias) LIKE LOWER(CONCAT('%', :alergia, '%'))")
    List<Patient> findByAlergiasContaining(@Param("alergia") String alergia);

    long countByActivoTrue();

    long countByGenero(clinickol.clinicmanagement.domain.model.Gender genero);

    boolean existsByEmail(String email);

    boolean existsByDocumento(String documento);
}
