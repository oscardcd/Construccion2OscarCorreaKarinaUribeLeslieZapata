package clinickol.clinicmanagement.infrastructure.persistence.mapper;

import clinickol.clinicmanagement.domain.model.PacienteDomain;
import clinickol.clinicmanagement.domain.model.enums.Gender;
import clinickol.clinicmanagement.infrastructure.persistence.entities.PacienteEntity;

public class PacienteMapper {

    public static PacienteEntity toEntity(PacienteDomain domain) {
        if (domain == null) return null;
        
        PacienteEntity entity = new PacienteEntity();
        entity.setId(domain.getId());
        entity.setCedula(domain.getCedula());
        entity.setNombreCompleto(domain.getNombreCompleto());
        entity.setFechaNacimiento(domain.getFechaNacimiento());
        entity.setGenero(domain.getGenero() != null ? domain.getGenero().name() : null);
        entity.setDireccion(domain.getDireccion());
        entity.setTelefono(domain.getTelefono());
        entity.setCorreoElectronico(domain.getCorreoElectronico());
        entity.setContactoEmergencia(ContactoEmergenciaMapper.toEntity(domain.getContactoEmergencia()));
        entity.setSeguroMedico(SeguroMedicoMapper.toEntity(domain.getSeguroMedico()));
        entity.setActivo(domain.isActivo());
        entity.setCopagoAnualAcumulado(domain.getCopagoAnualAcumulado());
        entity.setAnoCopagoActual(domain.getAnoCopagoActual());
        
        return entity;
    }

    public static PacienteDomain toDomain(PacienteEntity entity) {
        if (entity == null) return null;
        
        PacienteDomain domain = new PacienteDomain();
        domain.setId(entity.getId());
        domain.setCedula(entity.getCedula());
        domain.setNombreCompleto(entity.getNombreCompleto());
        domain.setFechaNacimiento(entity.getFechaNacimiento());
        domain.setGenero(parseGender(entity.getGenero()));
        domain.setDireccion(entity.getDireccion());
        domain.setTelefono(entity.getTelefono());
        domain.setCorreoElectronico(entity.getCorreoElectronico());
        domain.setContactoEmergencia(ContactoEmergenciaMapper.toDomain(entity.getContactoEmergencia()));
        domain.setSeguroMedico(SeguroMedicoMapper.toDomain(entity.getSeguroMedico()));
        domain.setActivo(entity.isActivo());
        domain.setCopagoAnualAcumulado(entity.getCopagoAnualAcumulado());
        domain.setAnoCopagoActual(entity.getAnoCopagoActual());
        
        return domain;
    }

    private static Gender parseGender(String genero) {
        if (genero == null) return null;
        try {
            return Gender.valueOf(genero.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}

