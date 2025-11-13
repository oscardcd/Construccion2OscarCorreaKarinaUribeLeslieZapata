package clinickol.clinicmanagement.adapter.out.persistence;

import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.model.enums.EstadoCita;
import clinickol.clinicmanagement.domain.ports.CitaPort;
import clinickol.clinicmanagement.infrastructure.persistence.entities.CitaEntity;
import clinickol.clinicmanagement.infrastructure.persistence.mapper.CitaMapper;
import clinickol.clinicmanagement.infrastructure.persistence.repository.CitaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaAdapter implements CitaPort {

    @Autowired
    private CitaJpaRepository citaJpaRepository;

    @Override
    public CitaDomain findById(Long id) throws Exception {
        Optional<CitaEntity> entity = citaJpaRepository.findById(id);
        return entity.map(CitaMapper::toDomain).orElse(null);
    }

    @Override
    public List<CitaDomain> findAll() throws Exception {
        return citaJpaRepository.findAll()
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDomain> findByPacienteId(Long pacienteId) throws Exception {
        return citaJpaRepository.findByPacienteId(pacienteId)
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDomain> findByMedicoId(Long medicoId) throws Exception {
        return citaJpaRepository.findByMedicoId(medicoId)
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDomain> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin) throws Exception {
        return citaJpaRepository.findByFechaHoraBetween(inicio, fin)
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDomain> findByEstado(EstadoCita estado) throws Exception {
        return citaJpaRepository.findByEstado(estado.name())
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDomain> findByPacienteIdAndEstado(Long pacienteId, EstadoCita estado) throws Exception {
        return citaJpaRepository.findByPacienteIdAndEstado(pacienteId, estado.name())
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDomain> findByMedicoIdAndEstado(Long medicoId, EstadoCita estado) throws Exception {
        return citaJpaRepository.findByMedicoIdAndEstado(medicoId, estado.name())
                .stream()
                .map(CitaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(CitaDomain cita) throws Exception {
        CitaEntity entity = CitaMapper.toEntity(cita);
        citaJpaRepository.save(entity);
        // Actualizar el ID del dominio después de guardar
        if (cita.getId() == null) {
            cita.setId(entity.getId());
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        citaJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByPacienteIdAndFechaHora(Long pacienteId, LocalDateTime fechaHora) throws Exception {
        return citaJpaRepository.findByPacienteIdAndFechaHora(pacienteId, fechaHora).isPresent();
    }

    @Override
    public boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora) throws Exception {
        return citaJpaRepository.findByMedicoIdAndFechaHora(medicoId, fechaHora).isPresent();
    }
}

