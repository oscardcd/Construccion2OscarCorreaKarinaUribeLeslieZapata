package clinickol.clinicmanagement.infrastructure.persistence.mapper;

import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.model.enums.EstadoCita;
import clinickol.clinicmanagement.infrastructure.persistence.entities.CitaEntity;

public class CitaMapper {

    public static CitaEntity toEntity(CitaDomain domain) {
        if (domain == null) return null;
        
        CitaEntity entity = new CitaEntity();
        entity.setId(domain.getId());
        entity.setPaciente(PacienteMapper.toEntity(domain.getPaciente()));
        entity.setMedico(EmpleadoMapper.toEntity(domain.getMedico()));
        entity.setFechaHora(domain.getFechaHora());
        entity.setEstado(domain.getEstado() != null ? domain.getEstado().name() : null);
        entity.setMotivo(domain.getMotivo());
        entity.setNotas(domain.getNotas());
        
        return entity;
    }

    public static CitaDomain toDomain(CitaEntity entity) {
        if (entity == null) return null;
        
        CitaDomain domain = new CitaDomain();
        domain.setId(entity.getId());
        domain.setPaciente(PacienteMapper.toDomain(entity.getPaciente()));
        domain.setMedico(EmpleadoMapper.toDomain(entity.getMedico()));
        domain.setFechaHora(entity.getFechaHora());
        domain.setEstado(parseEstadoCita(entity.getEstado()));
        domain.setMotivo(entity.getMotivo());
        domain.setNotas(entity.getNotas());
        
        return domain;
    }

    private static EstadoCita parseEstadoCita(String estado) {
        if (estado == null) return null;
        try {
            return EstadoCita.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}

