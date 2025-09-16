package com.mycompany.clinicmanagement.application.port;

import com.mycompany.clinicmanagement.domain.models.User;
import java.util.List;
import java.util.Optional;

/**
 * Puerto (interfaz) para el repositorio de usuarios
 * Define las operaciones de persistencia para la entidad User
 */
public interface UserRepositoryPort {

    /**
     * Guarda un usuario en el repositorio
     * 
     * @param user Usuario a guardar
     * @return Usuario guardado con ID asignado
     */
    User save(User user);

    /**
     * Busca un usuario por su ID
     * 
     * @param id ID del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findById(Long id);

    /**
     * Busca un usuario por su número de cédula
     * 
     * @param documentNumber Número de cédula
     * @return Optional con el usuario si existe
     */
    Optional<User> findByDocumentNumber(String documentNumber);

    /**
     * Busca un usuario por su nombre de usuario
     * 
     * @param username Nombre de usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su email
     * 
     * @param email Email del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Obtiene todos los usuarios
     * 
     * @return Lista de todos los usuarios
     */
    List<User> findAll();

    /**
     * Obtiene usuarios por rol
     * 
     * @param role Rol de los usuarios a buscar
     * @return Lista de usuarios con el rol especificado
     */
    List<User> findByRole(User.Role role);

    /**
     * Obtiene usuarios activos
     * 
     * @return Lista de usuarios activos
     */
    List<User> findActiveUsers();

    /**
     * Verifica si existe un usuario con el número de cédula dado
     * 
     * @param documentNumber Número de cédula
     * @return true si existe, false en caso contrario
     */
    boolean existsByDocumentNumber(String documentNumber);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado
     * 
     * @param username Nombre de usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByUsername(String username);

    /**
     * Verifica si existe un usuario con el email dado
     * 
     * @param email Email del usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);

    /**
     * Actualiza un usuario existente
     * 
     * @param user Usuario con los datos actualizados
     * @return Usuario actualizado
     */
    User update(User user);

    /**
     * Elimina un usuario por su ID
     * 
     * @param id ID del usuario a eliminar
     */
    void deleteById(Long id);

    /**
     * Elimina un usuario por su número de cédula
     * 
     * @param documentNumber Número de cédula del usuario a eliminar
     */
    void deleteByDocumentNumber(String documentNumber);

    /**
     * Cuenta el total de usuarios
     * 
     * @return Número total de usuarios
     */
    long count();

    /**
     * Cuenta usuarios por rol
     * 
     * @param role Rol de los usuarios a contar
     * @return Número de usuarios con el rol especificado
     */
    long countByRole(User.Role role);
}
