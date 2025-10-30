package clinickol.clinicmanagement.infrastructure.persistence.mapper;

import clinickol.clinicmanagement.domain.model.ContactoEmergenciaDomain;
import clinickol.clinicmanagement.infrastructure.persistence.entities.ContactoEmergenciaEntity;

public class ContactoEmergenciaMapper {

    public static ContactoEmergenciaEntity toEntity(ContactoEmergenciaDomain domain) {
        if (domain == null) return null;
        
        ContactoEmergenciaEntity entity = new ContactoEmergenciaEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setApellido(domain.getApellido());
        entity.setRelacionConPaciente(domain.getRelacionConPaciente());
        entity.setTelefonoEmergencia(domain.getTelefonoEmergencia());
        
        return entity;
    }

    public static ContactoEmergenciaDomain toDomain(ContactoEmergenciaEntity entity) {
        if (entity == null) return null;
        
        ContactoEmergenciaDomain domain = new ContactoEmergenciaDomain();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setApellido(entity.getApellido());
        domain.setRelacionConPaciente(entity.getRelacionConPaciente());
        domain.setTelefonoEmergencia(entity.getTelefonoEmergencia());
        
        return domain;
    }
}

