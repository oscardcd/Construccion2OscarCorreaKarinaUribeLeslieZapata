package com.mycompany.clinicmanagement.adapter.in.builder;

import com.mycompany.clinicmanagement.domain.models.User;
import com.mycompany.clinicmanagement.domain.service.UserDomainService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Builder para crear objetos User de forma fluida y segura
 * Implementa el patrón Builder para facilitar la construcción de usuarios
 * con validaciones integradas
 */
@Component
public class UserBuilder {

    private final UserDomainService userDomainService;
    private final User user;

    // Patrones de validación
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{1,15}$");
    private static final Pattern DOCUMENT_PATTERN = Pattern.compile("^[0-9]{6,12}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{1,10}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    public UserBuilder(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
        this.user = new User();
    }

    /**
     * Crea una nueva instancia del builder
     */
    public static UserBuilder create(UserDomainService userDomainService) {
        return new UserBuilder(userDomainService);
    }

    /**
     * Establece el ID del usuario
     */
    public UserBuilder withId(Long id) {
        this.user.setId(id);
        return this;
    }

    /**
     * Establece el número de cédula del usuario
     */
    public UserBuilder withDocumentNumber(String documentNumber) {
        validateDocumentNumber(documentNumber);
        this.user.setDocumentNumber(documentNumber);
        return this;
    }

    /**
     * Establece el nombre completo del usuario
     */
    public UserBuilder withFullName(String fullName) {
        validateFullName(fullName);
        this.user.setFullName(fullName);
        return this;
    }

    /**
     * Establece el email del usuario
     */
    public UserBuilder withEmail(String email) {
        validateEmail(email);
        this.user.setEmail(email);
        return this;
    }

    /**
     * Establece el teléfono del usuario
     */
    public UserBuilder withPhone(String phone) {
        validatePhone(phone);
        this.user.setPhone(phone);
        return this;
    }

    /**
     * Establece la fecha de nacimiento del usuario
     */
    public UserBuilder withBirthDate(LocalDate birthDate) {
        validateBirthDate(birthDate);
        this.user.setBirthDate(birthDate);
        return this;
    }

    /**
     * Establece la fecha de nacimiento del usuario desde string
     */
    public UserBuilder withBirthDate(String birthDateString) {
        LocalDate birthDate = parseBirthDate(birthDateString);
        return withBirthDate(birthDate);
    }

    /**
     * Establece la dirección del usuario
     */
    public UserBuilder withAddress(String address) {
        validateAddress(address);
        this.user.setAddress(address);
        return this;
    }

    /**
     * Establece el rol del usuario
     */
    public UserBuilder withRole(User.Role role) {
        validateRole(role);
        this.user.setRole(role);
        return this;
    }

    /**
     * Establece el rol del usuario desde string
     */
    public UserBuilder withRole(String roleString) {
        User.Role role = parseRole(roleString);
        return withRole(role);
    }

    /**
     * Establece el nombre de usuario
     */
    public UserBuilder withUsername(String username) {
        validateUsername(username);
        this.user.setUsername(username);
        return this;
    }

    /**
     * Establece la contraseña del usuario
     */
    public UserBuilder withPassword(String password) {
        validatePassword(password);
        this.user.setPassword(password);
        return this;
    }

    /**
     * Establece el estado activo del usuario
     */
    public UserBuilder withActiveStatus(boolean active) {
        this.user.setActive(active);
        return this;
    }

    /**
     * Establece la fecha de creación del usuario
     */
    public UserBuilder withCreatedAt(java.time.LocalDateTime createdAt) {
        this.user.setCreatedAt(createdAt);
        return this;
    }

    /**
     * Establece la fecha de actualización del usuario
     */
    public UserBuilder withUpdatedAt(java.time.LocalDateTime updatedAt) {
        this.user.setUpdatedAt(updatedAt);
        return this;
    }

    /**
     * Construye el objeto User con todas las validaciones
     */
    public User build() {
        // Validar que todos los campos obligatorios estén presentes
        validateRequiredFields();

        // Aplicar validaciones del dominio
        userDomainService.validateUserData(user);

        // Establecer valores por defecto si no están presentes
        setDefaultValues();

        return user;
    }

    /**
     * Construye el objeto User sin validaciones (para casos especiales)
     */
    public User buildWithoutValidation() {
        setDefaultValues();
        return user;
    }

    /**
     * Valida el número de cédula
     */
    private void validateDocumentNumber(String documentNumber) {
        if (documentNumber == null || documentNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cédula es obligatorio");
        }

        if (!DOCUMENT_PATTERN.matcher(documentNumber).matches()) {
            throw new IllegalArgumentException("El número de cédula debe contener entre 6 y 12 dígitos");
        }
    }

    /**
     * Valida el nombre completo
     */
    private void validateFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }

        if (fullName.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres");
        }
    }

    /**
     * Valida el email
     */
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
    }

    /**
     * Valida el teléfono
     */
    private void validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException("El teléfono debe contener entre 1 y 10 dígitos");
        }
    }

    /**
     * Valida la fecha de nacimiento
     */
    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }

        int age = LocalDate.now().getYear() - birthDate.getYear();
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y 150 años");
        }
    }

    /**
     * Valida la dirección
     */
    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }

        if (address.length() > 30) {
            throw new IllegalArgumentException("La dirección no puede exceder 30 caracteres");
        }
    }

    /**
     * Valida el rol
     */
    private void validateRole(User.Role role) {
        if (role == null) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
    }

    /**
     * Valida el nombre de usuario
     */
    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException(
                    "El nombre de usuario debe contener solo letras y números, máximo 15 caracteres");
        }
    }

    /**
     * Valida la contraseña
     */
    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException(
                    "La contraseña debe incluir al menos una mayúscula, un número y un carácter especial");
        }
    }

    /**
     * Parsea la fecha de nacimiento desde string
     */
    private LocalDate parseBirthDate(String birthDateString) {
        if (birthDateString == null || birthDateString.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }

        try {
            return LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use yyyy-MM-dd");
        }
    }

    /**
     * Parsea el rol desde string
     */
    private User.Role parseRole(String roleString) {
        if (roleString == null || roleString.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }

        try {
            return User.Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol inválido. Valores válidos: " +
                    java.util.Arrays.toString(User.Role.values()));
        }
    }

    /**
     * Valida que todos los campos obligatorios estén presentes
     */
    private void validateRequiredFields() {
        if (user.getDocumentNumber() == null) {
            throw new IllegalArgumentException("El número de cédula es obligatorio");
        }
        if (user.getFullName() == null) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (user.getPhone() == null) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (user.getBirthDate() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }
        if (user.getAddress() == null) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        if (user.getUsername() == null) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
    }

    /**
     * Establece valores por defecto
     */
    private void setDefaultValues() {
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(java.time.LocalDateTime.now());
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(java.time.LocalDateTime.now());
        }
        // isActive es boolean, no puede ser null, pero verificamos si está inicializado
        // En este caso, siempre establecemos true por defecto
        user.setActive(true);
    }

    /**
     * Resetea el builder para crear un nuevo usuario
     */
    public UserBuilder reset() {
        return new UserBuilder(userDomainService);
    }

    /**
     * Crea un usuario de ejemplo para testing
     */
    public static User createSampleUser(UserDomainService userDomainService) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber("12345678")
                .withFullName("Juan Pérez")
                .withEmail("juan.perez@example.com")
                .withPhone("1234567890")
                .withBirthDate("1990-01-01")
                .withAddress("Calle 123 #45-67")
                .withRole(User.Role.RECURSOS_HUMANOS)
                .withUsername("jperez")
                .withPassword("Password123!")
                .build();
    }

    /**
     * Crea un usuario médico de ejemplo
     */
    public static User createSampleDoctor(UserDomainService userDomainService) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber("87654321")
                .withFullName("Dr. María García")
                .withEmail("maria.garcia@clinic.com")
                .withPhone("0987654321")
                .withBirthDate("1985-05-15")
                .withAddress("Carrera 7 #32-10")
                .withRole(User.Role.MEDICO)
                .withUsername("mgarcia")
                .withPassword("Doctor123!")
                .build();
    }
}
