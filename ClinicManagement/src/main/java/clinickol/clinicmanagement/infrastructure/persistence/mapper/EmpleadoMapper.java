package clinickol.clinicmanagement.infrastructure.persistence.mapper;

import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.enums.Role;
import clinickol.clinicmanagement.infrastructure.persistence.entities.EmpleadoEntity;

public class EmpleadoMapper {

    public static EmpleadoEntity toEntity(EmpleadoDomain domain) {
        if (domain == null) return null;
        
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setId(domain.getId());
        entity.setNombreCompleto(domain.getNombreCompleto());
        entity.setCedula(domain.getCedula());
        entity.setCorreoElectronico(domain.getCorreoElectronico());
        entity.setTelefono(domain.getTelefono());
        entity.setFechaNacimiento(domain.getFechaNacimiento());
        entity.setDireccion(domain.getDireccion());
        entity.setRol(domain.getRol() != null ? domain.getRol().name() : null);
        entity.setNombreUsuario(domain.getNombreUsuario());
        entity.setContrasena(domain.getContrasena());
        entity.setActivo(domain.isActivo());
        
        return entity;
    }

    public static EmpleadoDomain toDomain(EmpleadoEntity entity) {
        if (entity == null) return null;
        
        EmpleadoDomain domain = new EmpleadoDomain();
        domain.setId(entity.getId());
        domain.setNombreCompleto(entity.getNombreCompleto());
        domain.setCedula(entity.getCedula());
        domain.setCorreoElectronico(entity.getCorreoElectronico());
        domain.setTelefono(entity.getTelefono());
        domain.setFechaNacimiento(entity.getFechaNacimiento());
        domain.setDireccion(entity.getDireccion());
        domain.setRol(parseRole(entity.getRol()));
        domain.setNombreUsuario(entity.getNombreUsuario());
        domain.setContrasena(entity.getContrasena());
        domain.setActivo(entity.isActivo());
        
        return domain;
    }

    private static Role parseRole(String rol) {
        if (rol == null) return null;
        try {
            return Role.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}

