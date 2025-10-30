package clinickol.clinicmanagement.adapter.in.rest.mapper;

import clinickol.clinicmanagement.adapter.in.rest.request.EmpleadoRequest;
import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoRestMapper {

    public EmpleadoDomain toDomain(EmpleadoRequest request) {
        if (request == null) return null;
        
        EmpleadoDomain domain = new EmpleadoDomain();
        domain.setNombreCompleto(request.getNombreCompleto());
        domain.setCedula(request.getCedula());
        domain.setCorreoElectronico(request.getCorreoElectronico());
        domain.setTelefono(request.getTelefono());
        domain.setFechaNacimiento(request.getFechaNacimiento());
        domain.setDireccion(request.getDireccion());
        
        if (request.getRol() != null) {
            domain.setRol(Role.valueOf(request.getRol().toUpperCase()));
        }
        
        domain.setNombreUsuario(request.getNombreUsuario());
        domain.setContrasena(request.getContrasena());
        
        return domain;
    }
}

