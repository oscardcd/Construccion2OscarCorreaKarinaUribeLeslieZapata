package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.AyudaDiagnostica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AyudaDiagnosticaRepository extends JpaRepository<AyudaDiagnostica, Long> {
    List<AyudaDiagnostica> findByActivoTrue();

    List<AyudaDiagnostica> findByNombreContainingIgnoreCase(String nombre);
}
