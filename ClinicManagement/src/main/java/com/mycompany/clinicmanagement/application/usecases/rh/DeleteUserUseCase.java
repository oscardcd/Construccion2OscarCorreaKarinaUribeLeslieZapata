package com.mycompany.clinicmanagement.application.usecases.rh;

import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para eliminar un usuario
 * Solo puede ser ejecutado por Recursos Humanos
 */
@Component
public class DeleteUserUseCase {

    private final UserServicePort userService;

    public DeleteUserUseCase(UserServicePort userService) {
        this.userService = userService;
    }

    /**
     * Ejecuta el caso de uso para eliminar un usuario
     * 
     * @param userId    ID del usuario a eliminar
     * @param deletedBy Usuario que elimina (debe ser RRHH)
     * @throws IllegalArgumentException Si el usuario eliminador no es RRHH
     */
    public void execute(Long userId, User deletedBy) {
        // Validar que el usuario eliminador sea RRHH
        if (!userService.canManageUsers(deletedBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede eliminar usuarios");
        }

        // Eliminar el usuario
        userService.deleteUser(userId, deletedBy);
    }
}
