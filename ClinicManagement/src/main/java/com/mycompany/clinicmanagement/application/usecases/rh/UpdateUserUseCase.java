package com.mycompany.clinicmanagement.application.usecases.rh;

import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para actualizar un usuario existente
 * Solo puede ser ejecutado por Recursos Humanos
 */
@Component
public class UpdateUserUseCase {

    private final UserServicePort userService;

    public UpdateUserUseCase(UserServicePort userService) {
        this.userService = userService;
    }

    /**
     * Ejecuta el caso de uso para actualizar un usuario
     * 
     * @param user      Usuario con los datos actualizados
     * @param updatedBy Usuario que actualiza (debe ser RRHH)
     * @return Usuario actualizado
     * @throws IllegalArgumentException Si el usuario actualizador no es RRHH
     */
    public User execute(User user, User updatedBy) {
        // Validar que el usuario actualizador sea RRHH
        if (!userService.canManageUsers(updatedBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede actualizar usuarios");
        }

        // Validar datos del usuario
        userService.validateUserData(user);

        // Actualizar el usuario
        return userService.updateUser(user, updatedBy);
    }
}
