package com.mycompany.clinicmanagement.application.usecases.rh;

import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Caso de uso para obtener estadísticas de usuarios
 * Solo puede ser ejecutado por Recursos Humanos
 */
@Component
public class GetUserStatisticsUseCase {

    private final UserServicePort userService;

    public GetUserStatisticsUseCase(UserServicePort userService) {
        this.userService = userService;
    }

    /**
     * Ejecuta el caso de uso para obtener estadísticas de usuarios
     * 
     * @param requestedBy Usuario que solicita las estadísticas (debe ser RRHH)
     * @return Mapa con estadísticas de usuarios
     * @throws IllegalArgumentException Si el usuario solicitante no es RRHH
     */
    public Map<String, Object> execute(User requestedBy) {
        // Validar que el usuario solicitante sea RRHH
        if (!userService.canManageUsers(requestedBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede ver estadísticas de usuarios");
        }

        // Obtener estadísticas
        return userService.getUserStatistics();
    }
}
