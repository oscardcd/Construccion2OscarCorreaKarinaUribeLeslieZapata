package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.application.exceptions.BusinessException;
import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.ports.EmpleadoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmpleado {

    @Autowired
    private EmpleadoPort empleadoPort;

    public void update(Long id, EmpleadoDomain empleadoActualizado) throws Exception {
        EmpleadoDomain empleado = empleadoPort.findById(id);
        
        if (empleado == null) {
            throw new BusinessException("Empleado no encontrado con ID: " + id);
        }

        // Actualizar campos
        empleado.setNombreCompleto(empleadoActualizado.getNombreCompleto());
        empleado.setCorreoElectronico(empleadoActualizado.getCorreoElectronico());
        empleado.setTelefono(empleadoActualizado.getTelefono());
        empleado.setDireccion(empleadoActualizado.getDireccion());
        empleado.setRol(empleadoActualizado.getRol());

        // Guardar cambios
        empleadoPort.save(empleado);
    }

    public void deactivate(Long id) throws Exception {
        EmpleadoDomain empleado = empleadoPort.findById(id);
        
        if (empleado == null) {
            throw new BusinessException("Empleado no encontrado con ID: " + id);
        }

        empleado.setActivo(false);
        empleadoPort.save(empleado);
    }
}

