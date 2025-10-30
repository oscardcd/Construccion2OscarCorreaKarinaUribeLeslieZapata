package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByActivoTrue();

    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);
}
