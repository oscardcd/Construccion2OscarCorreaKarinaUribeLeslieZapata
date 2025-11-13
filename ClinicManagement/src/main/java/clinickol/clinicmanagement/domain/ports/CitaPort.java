package clinickol.clinicmanagement.domain.ports;

import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.model.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaPort {
    CitaDomain findById(Long id) throws Exception;
    
    List<CitaDomain> findAll() throws Exception;
    
    List<CitaDomain> findByPacienteId(Long pacienteId) throws Exception;
    
    List<CitaDomain> findByMedicoId(Long medicoId) throws Exception;
    
    List<CitaDomain> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin) throws Exception;
    
    List<CitaDomain> findByEstado(EstadoCita estado) throws Exception;
    
    List<CitaDomain> findByPacienteIdAndEstado(Long pacienteId, EstadoCita estado) throws Exception;
    
    List<CitaDomain> findByMedicoIdAndEstado(Long medicoId, EstadoCita estado) throws Exception;
    
    void save(CitaDomain cita) throws Exception;
    
    void delete(Long id) throws Exception;
    
    boolean existsByPacienteIdAndFechaHora(Long pacienteId, LocalDateTime fechaHora) throws Exception;
    
    boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora) throws Exception;
}

