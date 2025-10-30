package clinickol.clinicmanagement.infrastructure.persistence.repository;

import clinickol.clinicmanagement.infrastructure.persistence.entities.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoJpaRepository extends JpaRepository<MedicamentoEntity, Long> {
    List<MedicamentoEntity> findByActivoTrue();
}

