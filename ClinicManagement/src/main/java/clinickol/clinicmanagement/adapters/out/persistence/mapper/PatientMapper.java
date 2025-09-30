package clinickol.clinicmanagement.adapters.out.persistence.mapper;

import clinickol.clinicmanagement.domain.model.PatientDomain;
import clinickol.clinicmanagement.domain.model.Patient;
import clinickol.clinicmanagement.domain.model.DocumentType;
import clinickol.clinicmanagement.domain.model.Gender;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(PatientDomain domain) {
        if (domain == null) {
            return null;
        }

        Patient entity = new Patient();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setApellido(domain.getApellido());
        entity.setEmail(domain.getEmail());
        entity.setTelefono(domain.getTelefono());
        entity.setDocumento(domain.getDocumento());

        if (domain.getTipoDocumento() != null) {
            entity.setTipoDocumento(DocumentType.valueOf(domain.getTipoDocumento()));
        }

        entity.setFechaNacimiento(domain.getFechaNacimiento());

        if (domain.getGenero() != null) {
            entity.setGenero(Gender.valueOf(domain.getGenero()));
        }

        entity.setDireccion(domain.getDireccion());
        entity.setCiudad(domain.getCiudad());
        entity.setCodigoPostal(domain.getCodigoPostal());
        entity.setContactoEmergencia(domain.getContactoEmergencia());
        entity.setTelefonoEmergencia(domain.getTelefonoEmergencia());
        entity.setAlergias(domain.getAlergias());
        entity.setMedicamentosActuales(domain.getMedicamentosActuales());
        entity.setHistorialMedico(domain.getHistorialMedico());
        entity.setGrupoSanguineo(domain.getGrupoSanguineo());
        entity.setPeso(domain.getPeso());
        entity.setAltura(domain.getAltura());
        entity.setActivo(domain.getActivo());
        entity.setFechaCreacion(domain.getFechaCreacion());
        entity.setFechaActualizacion(domain.getFechaActualizacion());

        return entity;
    }

    public PatientDomain toDomain(Patient entity) {
        if (entity == null) {
            return null;
        }

        PatientDomain domain = new PatientDomain();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setApellido(entity.getApellido());
        domain.setEmail(entity.getEmail());
        domain.setTelefono(entity.getTelefono());
        domain.setDocumento(entity.getDocumento());

        if (entity.getTipoDocumento() != null) {
            domain.setTipoDocumento(entity.getTipoDocumento().name());
        }

        domain.setFechaNacimiento(entity.getFechaNacimiento());

        if (entity.getGenero() != null) {
            domain.setGenero(entity.getGenero().name());
        }

        domain.setDireccion(entity.getDireccion());
        domain.setCiudad(entity.getCiudad());
        domain.setCodigoPostal(entity.getCodigoPostal());
        domain.setContactoEmergencia(entity.getContactoEmergencia());
        domain.setTelefonoEmergencia(entity.getTelefonoEmergencia());
        domain.setAlergias(entity.getAlergias());
        domain.setMedicamentosActuales(entity.getMedicamentosActuales());
        domain.setHistorialMedico(entity.getHistorialMedico());
        domain.setGrupoSanguineo(entity.getGrupoSanguineo());
        domain.setPeso(entity.getPeso());
        domain.setAltura(entity.getAltura());
        domain.setActivo(entity.getActivo());
        domain.setFechaCreacion(entity.getFechaCreacion());
        domain.setFechaActualizacion(entity.getFechaActualizacion());

        return domain;
    }
}
