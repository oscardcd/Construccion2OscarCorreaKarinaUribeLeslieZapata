package com.mycompany.clinicmanagement.adapter.in.web;

import com.mycompany.clinicmanagement.application.usecases.rh.CreateUserUseCase;
import com.mycompany.clinicmanagement.application.usecases.rh.DeleteUserUseCase;
import com.mycompany.clinicmanagement.application.usecases.rh.GetUserStatisticsUseCase;
import com.mycompany.clinicmanagement.application.usecases.rh.UpdateUserUseCase;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestión de usuarios
 * Adaptador de entrada que expone la funcionalidad de RRHH
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserStatisticsUseCase getUserStatisticsUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserStatisticsUseCase getUserStatisticsUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.getUserStatisticsUseCase = getUserStatisticsUseCase;
    }

    /**
     * Crea un nuevo usuario
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request,
            @RequestHeader("X-User-ID") Long currentUserId) {
        try {
            // TODO: Obtener usuario actual del contexto de seguridad
            User currentUser = getCurrentUser(currentUserId);

            User user = new User();
            user.setDocumentNumber(request.documentNumber());
            user.setFullName(request.fullName());
            user.setEmail(request.email());
            user.setPhone(request.phone());
            user.setBirthDate(request.birthDate());
            user.setAddress(request.address());
            user.setRole(User.Role.valueOf(request.role()));
            user.setUsername(request.username());
            user.setPassword(request.password());
            user.setActive(true);

            User createdUser = createUserUseCase.execute(user, currentUser);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CreateUserResponse(createdUser.getId(), "Usuario creado exitosamente"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Error de validación", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error interno", "Error inesperado al crear usuario"));
        }
    }

    /**
     * Actualiza un usuario existente
     * PUT /api/users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
            @RequestBody UpdateUserRequest request,
            @RequestHeader("X-User-ID") Long currentUserId) {
        try {
            // TODO: Obtener usuario actual del contexto de seguridad
            User currentUser = getCurrentUser(currentUserId);

            User user = new User();
            user.setId(id);
            user.setDocumentNumber(request.documentNumber());
            user.setFullName(request.fullName());
            user.setEmail(request.email());
            user.setPhone(request.phone());
            user.setBirthDate(request.birthDate());
            user.setAddress(request.address());
            user.setRole(User.Role.valueOf(request.role()));
            user.setUsername(request.username());
            user.setPassword(request.password());
            user.setActive(request.active());

            User updatedUser = updateUserUseCase.execute(user, currentUser);

            return ResponseEntity.ok(new UpdateUserResponse(updatedUser.getId(), "Usuario actualizado exitosamente"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Error de validación", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error interno", "Error inesperado al actualizar usuario"));
        }
    }

    /**
     * Elimina un usuario
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id,
            @RequestHeader("X-User-ID") Long currentUserId) {
        try {
            // TODO: Obtener usuario actual del contexto de seguridad
            User currentUser = getCurrentUser(currentUserId);

            deleteUserUseCase.execute(id, currentUser);

            return ResponseEntity.ok(new DeleteUserResponse("Usuario eliminado exitosamente"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Error de validación", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error interno", "Error inesperado al eliminar usuario"));
        }
    }

    /**
     * Obtiene estadísticas de usuarios
     * GET /api/users/statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<?> getUserStatistics(@RequestHeader("X-User-ID") Long currentUserId) {
        try {
            // TODO: Obtener usuario actual del contexto de seguridad
            User currentUser = getCurrentUser(currentUserId);

            Map<String, Object> statistics = getUserStatisticsUseCase.execute(currentUser);

            return ResponseEntity.ok(statistics);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Error de validación", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error interno", "Error inesperado al obtener estadísticas"));
        }
    }

    /**
     * TODO: Implementar obtención del usuario actual desde el contexto de seguridad
     */
    private User getCurrentUser(Long userId) {
        // Implementación temporal - en producción se obtendría del contexto de
        // seguridad
        User user = new User();
        user.setId(userId);
        user.setRole(User.Role.RECURSOS_HUMANOS);
        user.setActive(true);
        return user;
    }

    // DTOs para requests y responses
    public record CreateUserRequest(
            String documentNumber,
            String fullName,
            String email,
            String phone,
            java.time.LocalDate birthDate,
            String address,
            String role,
            String username,
            String password) {
    }

    public record UpdateUserRequest(
            String documentNumber,
            String fullName,
            String email,
            String phone,
            java.time.LocalDate birthDate,
            String address,
            String role,
            String username,
            String password,
            boolean active) {
    }

    public record CreateUserResponse(Long userId, String message) {
    }

    public record UpdateUserResponse(Long userId, String message) {
    }

    public record DeleteUserResponse(String message) {
    }

    public record ErrorResponse(String error, String message) {
    }
}
