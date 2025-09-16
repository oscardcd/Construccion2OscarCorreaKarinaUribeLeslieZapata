package com.mycompany.clinicmanagement.application.port;

import com.mycompany.clinicmanagement.domain.models.User;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) para el servicio de usuarios
 * Define las operaciones de negocio para la gestión de usuarios
 */
public interface UserServicePort {

    /**
     * Crea un nuevo usuario (solo Recursos Humanos)
     * 
     * @param user      Usuario a crear
     * @param createdBy Usuario que crea (debe ser RRHH)
     * @return Usuario creado
     * @throws IllegalArgumentException Si el usuario creador no es RRHH
     * @throws IllegalStateException    Si ya existe un usuario con la misma cédula
     *                                  o username
     */
    User createUser(User user, User createdBy);

    /**
     * Actualiza un usuario existente (solo Recursos Humanos)
     * 
     * @param user      Usuario con los datos actualizados
     * @param updatedBy Usuario que actualiza (debe ser RRHH)
     * @return Usuario actualizado
     * @throws IllegalArgumentException Si el usuario actualizador no es RRHH
     */
    User updateUser(User user, User updatedBy);

    /**
     * Elimina un usuario (solo Recursos Humanos)
     * 
     * @param userId    ID del usuario a eliminar
     * @param deletedBy Usuario que elimina (debe ser RRHH)
     * @throws IllegalArgumentException Si el usuario eliminador no es RRHH
     */
    void deleteUser(Long userId, User deletedBy);

    /**
     * Busca un usuario por ID
     * 
     * @param id ID del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findUserById(Long id);

    /**
     * Busca un usuario por número de cédula
     * 
     * @param documentNumber Número de cédula
     * @return Optional con el usuario si existe
     */
    Optional<User> findUserByDocumentNumber(String documentNumber);

    /**
     * Busca un usuario por nombre de usuario
     * 
     * @param username Nombre de usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Obtiene todos los usuarios
     * 
     * @return Lista de todos los usuarios
     */
    List<User> getAllUsers();

    /**
     * Obtiene usuarios por rol
     * 
     * @param role Rol de los usuarios a buscar
     * @return Lista de usuarios con el rol especificado
     */
    List<User> getUsersByRole(User.Role role);

    /**
     * Obtiene usuarios activos
     * 
     * @return Lista de usuarios activos
     */
    List<User> getActiveUsers();

    /**
     * Valida las credenciales de un usuario
     * 
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return Optional con el usuario si las credenciales son válidas
     */
    Optional<User> validateCredentials(String username, String password);

    /**
     * Cambia la contraseña de un usuario
     * 
     * @param userId      ID del usuario
     * @param oldPassword Contraseña actual
     * @param newPassword Nueva contraseña
     * @param changedBy   Usuario que cambia la contraseña
     * @return true si el cambio fue exitoso
     * @throws IllegalArgumentException Si la contraseña actual es incorrecta
     */
    boolean changePassword(Long userId, String oldPassword, String newPassword, User changedBy);

    /**
     * Activa o desactiva un usuario (solo Recursos Humanos)
     * 
     * @param userId    ID del usuario
     * @param active    Estado a establecer
     * @param updatedBy Usuario que actualiza (debe ser RRHH)
     * @return Usuario actualizado
     * @throws IllegalArgumentException Si el usuario actualizador no es RRHH
     */
    User setUserActiveStatus(Long userId, boolean active, User updatedBy);

    /**
     * Valida si un usuario puede realizar una acción específica
     * 
     * @param user   Usuario a validar
     * @param action Acción a realizar
     * @return true si el usuario puede realizar la acción
     */
    boolean canUserPerformAction(User user, String action);

    /**
     * Obtiene estadísticas de usuarios
     * 
     * @return Mapa con estadísticas de usuarios
     */
    java.util.Map<String, Object> getUserStatistics();

    /**
     * Valida los datos de un usuario antes de guardarlo
     * 
     * @param user Usuario a validar
     * @throws IllegalArgumentException Si los datos no son válidos
     */
    void validateUserData(User user);

    /**
     * Verifica si un usuario puede acceder a información de pacientes
     * 
     * @param user Usuario a verificar
     * @return true si puede acceder
     */
    boolean canAccessPatientInfo(User user);

    /**
     * Verifica si un usuario puede gestionar otros usuarios
     * 
     * @param user Usuario a verificar
     * @return true si puede gestionar usuarios
     */
    boolean canManageUsers(User user);

    /**
     * Verifica si un usuario puede gestionar inventario
     * 
     * @param user Usuario a verificar
     * @return true si puede gestionar inventario
     */
    boolean canManageInventory(User user);

    /**
     * Verifica si un usuario puede crear historias clínicas
     * 
     * @param user Usuario a verificar
     * @return true si puede crear historias clínicas
     */
    boolean canCreateMedicalRecords(User user);

    /**
     * Verifica si un usuario puede registrar signos vitales
     * 
     * @param user Usuario a verificar
     * @return true si puede registrar signos vitales
     */
    boolean canRegisterVitals(User user);
}
