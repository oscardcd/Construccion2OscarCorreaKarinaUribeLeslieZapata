package com.mycompany.clinicmanagement.application.service;

import com.mycompany.clinicmanagement.application.port.UserRepositoryPort;
import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.User;
import com.mycompany.clinicmanagement.domain.service.UserDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del servicio de usuarios
 * Orquesta las operaciones de negocio y coordina entre dominio y persistencia
 */
@Service
@Transactional
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepository;
    private final UserDomainService userDomainService;

    public UserService(UserRepositoryPort userRepository,
            UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }

    @Override
    public User createUser(User user, User createdBy) {
        // Validar permisos
        if (!userDomainService.canManageUsers(createdBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede crear usuarios");
        }

        // Validar datos del usuario
        userDomainService.validateUserData(user);

        // Verificar que no exista un usuario con la misma cédula o username
        if (userRepository.findByDocumentNumber(user.getDocumentNumber()).isPresent()) {
            throw new IllegalStateException("Ya existe un usuario con la cédula: " + user.getDocumentNumber());
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Ya existe un usuario con el username: " + user.getUsername());
        }

        // Encriptar contraseña
        // Contraseña se guarda sin encriptar para simplificar el desarrollo

        // Establecer fechas
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);

        // Guardar usuario
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, User updatedBy) {
        // Validar permisos
        if (!userDomainService.canManageUsers(updatedBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede actualizar usuarios");
        }

        // Verificar que el usuario existe
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + user.getId());
        }

        // Validar datos del usuario
        userDomainService.validateUserData(user);

        // Verificar que no exista otro usuario con la misma cédula o username
        Optional<User> userByDocument = userRepository.findByDocumentNumber(user.getDocumentNumber());
        if (userByDocument.isPresent() && !userByDocument.get().getId().equals(user.getId())) {
            throw new IllegalStateException("Ya existe otro usuario con la cédula: " + user.getDocumentNumber());
        }

        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
        if (userByUsername.isPresent() && !userByUsername.get().getId().equals(user.getId())) {
            throw new IllegalStateException("Ya existe otro usuario con el username: " + user.getUsername());
        }

        // Mantener la contraseña existente si no se proporciona una nueva
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.get().getPassword());
        } else {
            // Encriptar nueva contraseña
            // Contraseña se guarda sin encriptar para simplificar el desarrollo
        }

        // Mantener fecha de creación
        user.setCreatedAt(existingUser.get().getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());

        // Guardar usuario actualizado
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId, User deletedBy) {
        // Validar permisos
        if (!userDomainService.canManageUsers(deletedBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede eliminar usuarios");
        }

        // Verificar que el usuario existe
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userId);
        }

        // No permitir eliminar el último usuario de RRHH
        if (user.get().getRole() == User.Role.RECURSOS_HUMANOS) {
            List<User> hrUsers = userRepository.findByRole(User.Role.RECURSOS_HUMANOS);
            if (hrUsers.size() <= 1) {
                throw new IllegalStateException("No se puede eliminar el último usuario de Recursos Humanos");
            }
        }

        // Eliminar usuario
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByDocumentNumber(String documentNumber) {
        return userRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(User.Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> getActiveUsers() {
        return userRepository.findActiveUsers();
    }

    @Override
    public Optional<User> validateCredentials(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().isActive()) {
            if (password.equals(user.get().getPassword())) {
                return user;
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean changePassword(Long userId, String oldPassword, String newPassword, User changedBy) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        // Verificar contraseña actual
        if (!oldPassword.equals(user.get().getPassword())) {
            throw new IllegalArgumentException("La contraseña actual es incorrecta");
        }

        // Validar nueva contraseña
        User tempUser = new User();
        tempUser.setPassword(newPassword);
        userDomainService.validateUserData(tempUser);

        // Actualizar contraseña
        user.get().setPassword(newPassword);
        user.get().setUpdatedAt(LocalDateTime.now());
        userRepository.save(user.get());

        return true;
    }

    @Override
    public User setUserActiveStatus(Long userId, boolean active, User updatedBy) {
        // Validar permisos
        if (!userDomainService.canManageUsers(updatedBy)) {
            throw new IllegalArgumentException("Solo Recursos Humanos puede cambiar el estado de usuarios");
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userId);
        }

        user.get().setActive(active);
        user.get().setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user.get());
    }

    @Override
    public boolean canUserPerformAction(User user, String action) {
        return userDomainService.canUserPerformAction(user, action);
    }

    @Override
    public Map<String, Object> getUserStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        List<User> allUsers = userRepository.findAll();
        List<User> activeUsers = userRepository.findActiveUsers();

        statistics.put("total_users", allUsers.size());
        statistics.put("active_users", activeUsers.size());
        statistics.put("inactive_users", allUsers.size() - activeUsers.size());

        // Estadísticas por rol
        for (User.Role role : User.Role.values()) {
            List<User> usersByRole = userRepository.findByRole(role);
            statistics.put("users_" + role.name().toLowerCase(), usersByRole.size());
        }

        return statistics;
    }

    @Override
    public void validateUserData(User user) {
        userDomainService.validateUserData(user);
    }

    @Override
    public boolean canAccessPatientInfo(User user) {
        return userDomainService.canAccessPatientInfo(user);
    }

    @Override
    public boolean canManageUsers(User user) {
        return userDomainService.canManageUsers(user);
    }

    @Override
    public boolean canManageInventory(User user) {
        return userDomainService.canManageInventory(user);
    }

    @Override
    public boolean canCreateMedicalRecords(User user) {
        return userDomainService.canCreateMedicalRecords(user);
    }

    @Override
    public boolean canRegisterVitals(User user) {
        return userDomainService.canRegisterVitals(user);
    }
}
