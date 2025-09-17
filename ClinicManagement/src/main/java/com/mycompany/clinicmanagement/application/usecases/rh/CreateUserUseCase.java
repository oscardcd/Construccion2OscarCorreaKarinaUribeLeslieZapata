package com.mycompany.clinicmanagement.application.usecases.rh;

import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para crear un nuevo usuario
 * Solo puede ser ejecutado por Recursos Humanos
 */
@Component
public class CreateUserUseCase {

    private final UserServicePort userService;

    public CreateUserUseCase(UserServicePort userService) {
        this.userService = userService;
    }

    /**
     * Ejecuta el caso de uso para crear un usuario
     * 
     * @param user      Usuario a crear
     * @param createdBy Usuario que crea (debe ser RRHH)
     * @return Usuario creado
     * @throws IllegalArgumentException Si el usuario creador no es RRHH
     * @throws IllegalStateException    Si ya existe un usuario con la misma cédula
     *                                  o username
     */
    public User execute(User user, User createdBy) {
        // Validar que el usuario creador sea RRHH
        if (!userService.canManageUsers(createdBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede crear usuarios");
        }

        // Validar datos del usuario
        userService.validateUserData(user);

        // Crear el usuario
        return userService.createUser(user, createdBy);
    }
}
