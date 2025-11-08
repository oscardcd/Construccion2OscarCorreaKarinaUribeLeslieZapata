package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByActivoTrue();

    List<Medication> findByNombreContainingIgnoreCase(String nombre);

    List<Medication> findByDescriptionContainingIgnoreCase(String description);

    List<Medication> findByCostGreaterThanEqual(BigDecimal minCost);

    List<Medication> findByStockLessThan(int stockThreshold);
}
