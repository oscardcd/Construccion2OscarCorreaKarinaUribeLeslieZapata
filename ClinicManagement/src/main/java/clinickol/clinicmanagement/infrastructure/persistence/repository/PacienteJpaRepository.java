package clinickol.clinicmanagement.infrastructure.persistence.repository;

import clinickol.clinicmanagement.infrastructure.persistence.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteJpaRepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByCedula(String cedula);
    
    List<PacienteEntity> findByActivoTrue();
    
    boolean existsByCedula(String cedula);
}

