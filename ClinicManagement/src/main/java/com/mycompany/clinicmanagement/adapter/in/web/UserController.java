package com.mycompany.clinicmanagement.adapter.in.web;

import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para gestión de usuarios
 * Expone endpoints para operaciones CRUD de usuarios
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServicePort userService;

    public UserController(UserServicePort userService) {
        this.userService = userService;
    }

    /**
     * Crea un nuevo usuario
     * POST /api/users
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user,
            @RequestHeader("X-User-Id") Long currentUserId) {
        try {
            // Obtener usuario actual (en producción se obtendría del token JWT)
            User currentUser = userService.findUserById(currentUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario actual no encontrado"));

            User createdUser = userService.createUser(user, currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Obtiene un usuario por ID
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene un usuario por número de cédula
     * GET /api/users/document/{documentNumber}
     */
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<?> getUserByDocument(@PathVariable String documentNumber) {
        Optional<User> user = userService.findUserByDocumentNumber(documentNumber);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene un usuario por nombre de usuario
     * GET /api/users/username/{username}
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene todos los usuarios
     * GET /api/users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Obtiene usuarios por rol
     * GET /api/users/role/{role}
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        try {
            User.Role userRole = User.Role.valueOf(role.toUpperCase());
            List<User> users = userService.getUsersByRole(userRole);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Obtiene usuarios activos
     * GET /api/users/active
     */
    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> users = userService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Actualiza un usuario
     * PUT /api/users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
            @RequestBody User user,
            @RequestHeader("X-User-Id") Long currentUserId) {
        try {
            // Obtener usuario actual
            User currentUser = userService.findUserById(currentUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario actual no encontrado"));

            user.setId(id);
            User updatedUser = userService.updateUser(user, currentUser);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Elimina un usuario
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id,
            @RequestHeader("X-User-Id") Long currentUserId) {
        try {
            // Obtener usuario actual
            User currentUser = userService.findUserById(currentUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario actual no encontrado"));

            userService.deleteUser(id, currentUser);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Cambia la contraseña de un usuario
     * PUT /api/users/{id}/password
     */
    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id,
            @RequestBody Map<String, String> passwordData,
            @RequestHeader("X-User-Id") Long currentUserId) {
        try {
            // Obtener usuario actual
            User currentUser = userService.findUserById(currentUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario actual no encontrado"));

            String oldPassword = passwordData.get("oldPassword");
            String newPassword = passwordData.get("newPassword");

            if (oldPassword == null || newPassword == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Se requieren oldPassword y newPassword"));
            }

            boolean success = userService.changePassword(id, oldPassword, newPassword, currentUser);
            if (success) {
                return ResponseEntity.ok(Map.of("message", "Contraseña actualizada exitosamente"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "No se pudo actualizar la contraseña"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Activa o desactiva un usuario
     * PUT /api/users/{id}/status
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> setUserStatus(@PathVariable Long id,
            @RequestBody Map<String, Boolean> statusData,
            @RequestHeader("X-User-Id") Long currentUserId) {
        try {
            // Obtener usuario actual
            User currentUser = userService.findUserById(currentUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario actual no encontrado"));

            Boolean active = statusData.get("active");
            if (active == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Se requiere el campo 'active'"));
            }

            User updatedUser = userService.setUserActiveStatus(id, active, currentUser);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Obtiene estadísticas de usuarios
     * GET /api/users/statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getUserStatistics() {
        Map<String, Object> statistics = userService.getUserStatistics();
        return ResponseEntity.ok(statistics);
    }

    /**
     * Valida credenciales de usuario
     * POST /api/users/validate
     */
    @PostMapping("/validate")
    public ResponseEntity<?> validateCredentials(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Se requieren username y password"));
        }

        Optional<User> user = userService.validateCredentials(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "user", user.get()));
        } else {
            return ResponseEntity.ok(Map.of("valid", false));
        }
    }

    /**
     * Obtiene información de permisos de un usuario
     * GET /api/users/{id}/permissions
     */
    @GetMapping("/{id}/permissions")
    public ResponseEntity<?> getUserPermissions(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Boolean> permissions = Map.of(
                "canAccessPatientInfo", userService.canAccessPatientInfo(user.get()),
                "canManageUsers", userService.canManageUsers(user.get()),
                "canManageInventory", userService.canManageInventory(user.get()),
                "canCreateMedicalRecords", userService.canCreateMedicalRecords(user.get()),
                "canRegisterVitals", userService.canRegisterVitals(user.get()));

        return ResponseEntity.ok(permissions);
    }
}
