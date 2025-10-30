package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Procedimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Long> {
    List<Procedimiento> findByActivoTrue();

    List<Procedimiento> findByNombreContainingIgnoreCase(String nombre);
}
