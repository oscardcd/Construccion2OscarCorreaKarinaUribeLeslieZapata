package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.application.exceptions.BusinessException;
import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.ports.EmpleadoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateEmpleado {

    @Autowired
    private EmpleadoPort empleadoPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(EmpleadoDomain empleado) throws Exception {
        // Validar cédula única
        if (empleadoPort.existsByCedula(empleado.getCedula())) {
            throw new BusinessException("Ya existe un empleado con la cédula: " + empleado.getCedula());
        }

        // Validar nombre de usuario único
        if (empleadoPort.existsByNombreUsuario(empleado.getNombreUsuario())) {
            throw new BusinessException("El nombre de usuario ya está en uso: " + empleado.getNombreUsuario());
        }

        // Validar correo electrónico único
        if (empleadoPort.existsByCorreoElectronico(empleado.getCorreoElectronico())) {
            throw new BusinessException("El correo electrónico ya está registrado: " + empleado.getCorreoElectronico());
        }

        // Encriptar contraseña
        empleado.setContrasena(passwordEncoder.encode(empleado.getContrasena()));

        // Guardar empleado
        empleadoPort.save(empleado);
    }
}

