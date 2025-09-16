package com.mycompany.clinicmanagement.adapter.out.persistence;

import com.mycompany.clinicmanagement.application.port.UserRepositoryPort;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia para usuarios
 * Implementa UserRepositoryPort usando almacenamiento en memoria
 * En producción se reemplazaría por JPA/Hibernate con base de datos real
 */
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, User> usersByDocument = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, User> usersByUsername = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, User> usersByEmail = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }

        users.put(user.getId(), user);
        usersByDocument.put(user.getDocumentNumber(), user);
        usersByUsername.put(user.getUsername(), user);
        usersByEmail.put(user.getEmail(), user);

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByDocumentNumber(String documentNumber) {
        return Optional.ofNullable(usersByDocument.get(documentNumber));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(usersByUsername.get(username));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users.values());
    }

    @Override
    public List<User> findByRole(User.Role role) {
        return users.values().stream()
                .filter(user -> user.getRole() == role)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findActiveUsers() {
        return users.values().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return usersByDocument.containsKey(documentNumber);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usersByUsername.containsKey(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersByEmail.containsKey(email);
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("El usuario debe tener un ID para ser actualizado");
        }

        User existingUser = users.get(user.getId());
        if (existingUser == null) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + user.getId());
        }

        // Actualizar índices si cambió la información
        if (!existingUser.getDocumentNumber().equals(user.getDocumentNumber())) {
            usersByDocument.remove(existingUser.getDocumentNumber());
            usersByDocument.put(user.getDocumentNumber(), user);
        }

        if (!existingUser.getUsername().equals(user.getUsername())) {
            usersByUsername.remove(existingUser.getUsername());
            usersByUsername.put(user.getUsername(), user);
        }

        if (!existingUser.getEmail().equals(user.getEmail())) {
            usersByEmail.remove(existingUser.getEmail());
            usersByEmail.put(user.getEmail(), user);
        }

        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        User user = users.remove(id);
        if (user != null) {
            usersByDocument.remove(user.getDocumentNumber());
            usersByUsername.remove(user.getUsername());
            usersByEmail.remove(user.getEmail());
        }
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        User user = usersByDocument.remove(documentNumber);
        if (user != null) {
            users.remove(user.getId());
            usersByUsername.remove(user.getUsername());
            usersByEmail.remove(user.getEmail());
        }
    }

    @Override
    public long count() {
        return users.size();
    }

    @Override
    public long countByRole(User.Role role) {
        return users.values().stream()
                .filter(user -> user.getRole() == role)
                .count();
    }
}
