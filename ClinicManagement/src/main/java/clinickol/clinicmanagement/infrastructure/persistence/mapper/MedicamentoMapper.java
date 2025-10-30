package clinickol.clinicmanagement.infrastructure.persistence.mapper;

import clinickol.clinicmanagement.domain.model.MedicamentoDomain;
import clinickol.clinicmanagement.infrastructure.persistence.entities.MedicamentoEntity;

public class MedicamentoMapper {

    public static MedicamentoEntity toEntity(MedicamentoDomain domain) {
        if (domain == null) return null;
        
        MedicamentoEntity entity = new MedicamentoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setCosto(domain.getCosto());
        entity.setStock(domain.getStock());
        entity.setActivo(domain.isActivo());
        
        return entity;
    }

    public static MedicamentoDomain toDomain(MedicamentoEntity entity) {
        if (entity == null) return null;
        
        MedicamentoDomain domain = new MedicamentoDomain();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setDescripcion(entity.getDescripcion());
        domain.setCosto(entity.getCosto());
        domain.setStock(entity.getStock());
        domain.setActivo(entity.isActivo());
        
        return domain;
    }
}

