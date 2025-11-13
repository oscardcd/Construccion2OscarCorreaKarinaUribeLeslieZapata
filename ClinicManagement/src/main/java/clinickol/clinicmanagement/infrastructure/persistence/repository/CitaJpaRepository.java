package clinickol.clinicmanagement.infrastructure.persistence.repository;

import clinickol.clinicmanagement.infrastructure.persistence.entities.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaJpaRepository extends JpaRepository<CitaEntity, Long> {
    List<CitaEntity> findByPacienteId(Long pacienteId);
    
    List<CitaEntity> findByMedicoId(Long medicoId);
    
    List<CitaEntity> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    
    List<CitaEntity> findByEstado(String estado);
    
    List<CitaEntity> findByPacienteIdAndEstado(Long pacienteId, String estado);
    
    List<CitaEntity> findByMedicoIdAndEstado(Long medicoId, String estado);
    
    Optional<CitaEntity> findByPacienteIdAndFechaHora(Long pacienteId, LocalDateTime fechaHora);
    
    Optional<CitaEntity> findByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);
}

