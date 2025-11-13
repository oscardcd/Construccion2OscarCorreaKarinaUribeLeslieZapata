package clinickol.clinicmanagement.infrastructure.persistence.mapper;

import clinickol.clinicmanagement.domain.model.SeguroMedicoDomain;
import clinickol.clinicmanagement.infrastructure.persistence.entities.SeguroMedicoEntity;

public class SeguroMedicoMapper {

    public static SeguroMedicoEntity toEntity(SeguroMedicoDomain domain) {
        if (domain == null) return null;
        
        SeguroMedicoEntity entity = new SeguroMedicoEntity();
        entity.setId(domain.getId());
        entity.setNombreCompania(domain.getNombreCompania());
        entity.setNumeroPoliza(domain.getNumeroPoliza());
        entity.setEstadoPoliza(domain.isEstadoPoliza());
        entity.setVigenciaPoliza(domain.getVigenciaPoliza());
        
        return entity;
    }

    public static SeguroMedicoDomain toDomain(SeguroMedicoEntity entity) {
        if (entity == null) return null;
        
        SeguroMedicoDomain domain = new SeguroMedicoDomain();
        domain.setId(entity.getId());
        domain.setNombreCompania(entity.getNombreCompania());
        domain.setNumeroPoliza(entity.getNumeroPoliza());
        domain.setEstadoPoliza(entity.isEstadoPoliza());
        domain.setVigenciaPoliza(entity.getVigenciaPoliza());
        
        return domain;
    }
}

