package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCedula(String cedula);

    List<Paciente> findByActivoTrue();

    boolean existsByCedula(String cedula);

    List<Paciente> findByNombreCompletoContainingIgnoreCase(String nombre);
}
