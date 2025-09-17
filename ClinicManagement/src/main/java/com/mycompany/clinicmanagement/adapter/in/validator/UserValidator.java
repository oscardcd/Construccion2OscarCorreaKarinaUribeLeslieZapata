package com.mycompany.clinicmanagement.adapter.in.validator;

import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Validador para datos de entrada de usuarios
 * Proporciona validaciones específicas para formularios y APIs
 */
@Component
public class UserValidator {

    // Patrones de validación
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{1,15}$");
    private static final Pattern DOCUMENT_PATTERN = Pattern.compile("^[0-9]{6,12}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{1,10}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    /**
     * Valida el nombre completo
     */
    public String nameValidator(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("El nombre completo es obligatorio");
        }

        String trimmedName = name.trim();
        if (trimmedName.length() < 2) {
            throw new ValidationException("El nombre debe tener al menos 2 caracteres");
        }

        if (trimmedName.length() > 100) {
            throw new ValidationException("El nombre no puede exceder 100 caracteres");
        }

        // Validar que contenga solo letras, espacios y caracteres especiales permitidos
        if (!trimmedName.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\-\\.]+$")) {
            throw new ValidationException("El nombre solo puede contener letras, espacios, guiones y puntos");
        }

        return trimmedName;
    }

    /**
     * Valida el número de cédula
     */
    public String documentValidator(String document) throws ValidationException {
        if (document == null || document.trim().isEmpty()) {
            throw new ValidationException("El número de cédula es obligatorio");
        }

        String trimmedDocument = document.trim();
        if (!DOCUMENT_PATTERN.matcher(trimmedDocument).matches()) {
            throw new ValidationException("El número de cédula debe contener entre 6 y 12 dígitos");
        }

        return trimmedDocument;
    }

    /**
     * Valida la edad
     */
    public int ageValidator(String ageString) throws ValidationException {
        if (ageString == null || ageString.trim().isEmpty()) {
            throw new ValidationException("La edad es obligatoria");
        }

        try {
            int age = Integer.parseInt(ageString.trim());
            if (age < 0 || age > 150) {
                throw new ValidationException("La edad debe estar entre 0 y 150 años");
            }
            return age;
        } catch (NumberFormatException e) {
            throw new ValidationException("La edad debe ser un número válido");
        }
    }

    /**
     * Valida la edad desde fecha de nacimiento
     */
    public int ageValidator(LocalDate birthDate) throws ValidationException {
        if (birthDate == null) {
            throw new ValidationException("La fecha de nacimiento es obligatoria");
        }

        int age = LocalDate.now().getYear() - birthDate.getYear();
        if (age < 0 || age > 150) {
            throw new ValidationException("La edad debe estar entre 0 y 150 años");
        }

        return age;
    }

    /**
     * Valida el nombre de usuario
     */
    public String userNameValidator(String userName) throws ValidationException {
        if (userName == null || userName.trim().isEmpty()) {
            throw new ValidationException("El nombre de usuario es obligatorio");
        }

        String trimmedUserName = userName.trim();
        if (!USERNAME_PATTERN.matcher(trimmedUserName).matches()) {
            throw new ValidationException(
                    "El nombre de usuario debe contener solo letras y números, máximo 15 caracteres");
        }

        return trimmedUserName;
    }

    /**
     * Valida la contraseña
     */
    public String passwordValidator(String password) throws ValidationException {
        if (password == null || password.isEmpty()) {
            throw new ValidationException("La contraseña es obligatoria");
        }

        if (password.length() < 8) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres");
        }

        if (password.length() > 128) {
            throw new ValidationException("La contraseña no puede exceder 128 caracteres");
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException(
                    "La contraseña debe incluir al menos una mayúscula, un número y un carácter especial");
        }

        return password;
    }

    /**
     * Valida el email
     */
    public String emailValidator(String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("El email es obligatorio");
        }

        String trimmedEmail = email.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(trimmedEmail).matches()) {
            throw new ValidationException("El formato del email no es válido");
        }

        if (trimmedEmail.length() > 254) {
            throw new ValidationException("El email no puede exceder 254 caracteres");
        }

        return trimmedEmail;
    }

    /**
     * Valida el teléfono
     */
    public String phoneValidator(String phone) throws ValidationException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidationException("El teléfono es obligatorio");
        }

        String trimmedPhone = phone.trim();
        if (!PHONE_PATTERN.matcher(trimmedPhone).matches()) {
            throw new ValidationException("El teléfono debe contener entre 1 y 10 dígitos");
        }

        return trimmedPhone;
    }

    /**
     * Valida la dirección
     */
    public String addressValidator(String address) throws ValidationException {
        if (address == null || address.trim().isEmpty()) {
            throw new ValidationException("La dirección es obligatoria");
        }

        String trimmedAddress = address.trim();
        if (trimmedAddress.length() < 5) {
            throw new ValidationException("La dirección debe tener al menos 5 caracteres");
        }

        if (trimmedAddress.length() > 200) {
            throw new ValidationException("La dirección no puede exceder 200 caracteres");
        }

        return trimmedAddress;
    }

    /**
     * Valida el rol
     */
    public User.Role roleValidator(String roleString) throws ValidationException {
        if (roleString == null || roleString.trim().isEmpty()) {
            throw new ValidationException("El rol es obligatorio");
        }

        try {
            return User.Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Rol inválido. Valores válidos: " +
                    java.util.Arrays.toString(User.Role.values()));
        }
    }

    /**
     * Valida la fecha de nacimiento
     */
    public LocalDate birthDateValidator(String birthDateString) throws ValidationException {
        if (birthDateString == null || birthDateString.trim().isEmpty()) {
            throw new ValidationException("La fecha de nacimiento es obligatoria");
        }

        try {
            LocalDate birthDate = LocalDate.parse(birthDateString.trim());
            ageValidator(birthDate); // Validar que la edad sea válida
            return birthDate;
        } catch (Exception e) {
            throw new ValidationException("Formato de fecha inválido. Use yyyy-MM-dd");
        }
    }

    /**
     * Valida que un usuario tenga todos los campos obligatorios
     */
    public void validateCompleteUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("El usuario no puede ser nulo");
        }

        nameValidator(user.getFullName());
        documentValidator(user.getDocumentNumber());
        emailValidator(user.getEmail());
        phoneValidator(user.getPhone());
        addressValidator(user.getAddress());
        userNameValidator(user.getUsername());
        passwordValidator(user.getPassword());

        if (user.getBirthDate() == null) {
            throw new ValidationException("La fecha de nacimiento es obligatoria");
        }

        if (user.getRole() == null) {
            throw new ValidationException("El rol es obligatorio");
        }
    }

    /**
     * Valida que un usuario tenga los campos mínimos para actualización
     */
    public void validateUserForUpdate(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("El usuario no puede ser nulo");
        }

        if (user.getId() == null) {
            throw new ValidationException("El ID del usuario es obligatorio para actualización");
        }

        // Validar solo los campos que están presentes
        if (user.getFullName() != null) {
            nameValidator(user.getFullName());
        }
        if (user.getEmail() != null) {
            emailValidator(user.getEmail());
        }
        if (user.getPhone() != null) {
            phoneValidator(user.getPhone());
        }
        if (user.getAddress() != null) {
            addressValidator(user.getAddress());
        }
        if (user.getUsername() != null) {
            userNameValidator(user.getUsername());
        }
        if (user.getPassword() != null) {
            passwordValidator(user.getPassword());
        }
    }

    /**
     * Excepción personalizada para errores de validación
     */
    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}
