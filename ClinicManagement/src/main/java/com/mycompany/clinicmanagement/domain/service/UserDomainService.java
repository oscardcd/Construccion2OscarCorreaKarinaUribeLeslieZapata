package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Servicio de dominio para validaciones y reglas de negocio de usuarios
 * Contiene lógica pura del dominio sin dependencias externas
 */
@Service
public class UserDomainService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{1,15}$");
    private static final Pattern DOCUMENT_PATTERN = Pattern.compile("^[0-9]{6,12}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{1,10}$");

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_AGE = 150;

    /**
     * Valida los datos de un usuario según las reglas de negocio
     * 
     * @param user Usuario a validar
     * @throws IllegalArgumentException Si los datos no son válidos
     */
    public void validateUserData(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        validateDocumentNumber(user.getDocumentNumber());
        validateFullName(user.getFullName());
        validateEmail(user.getEmail());
        validatePhone(user.getPhone());
        validateBirthDate(user.getBirthDate());
        validateAddress(user.getAddress());
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        validateRole(user.getRole());
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
    private void validateBirthDate(java.time.LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }

        int age = java.time.LocalDate.now().getYear() - birthDate.getYear();
        if (age < 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y " + MAX_AGE + " años");
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

        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException(
                    "La contraseña debe tener al menos " + MIN_PASSWORD_LENGTH + " caracteres");
        }

        if (!hasValidPasswordFormat(password)) {
            throw new IllegalArgumentException(
                    "La contraseña debe incluir al menos una mayúscula, un número y un carácter especial");
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
     * Verifica si la contraseña tiene el formato correcto
     */
    private boolean hasValidPasswordFormat(String password) {
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]{}|;:,.<>?".indexOf(ch) >= 0);

        return hasUpperCase && hasDigit && hasSpecialChar;
    }

    /**
     * Verifica si un usuario puede realizar una acción específica
     * 
     * @param user   Usuario a verificar
     * @param action Acción a realizar
     * @return true si puede realizar la acción
     */
    public boolean canUserPerformAction(User user, String action) {
        if (user == null || !user.isActive()) {
            return false;
        }

        return switch (action.toLowerCase()) {
            case "create_user", "update_user", "delete_user" -> user.getRole() == User.Role.RECURSOS_HUMANOS;
            case "register_patient", "update_patient" -> user.getRole() == User.Role.PERSONAL_ADMINISTRATIVO;
            case "register_vitals" -> user.getRole() == User.Role.ENFERMERA;
            case "create_medical_record", "create_order" -> user.getRole() == User.Role.MEDICO;
            case "manage_inventory" -> user.getRole() == User.Role.SOPORTE_INFORMACION;
            default -> false;
        };
    }

    /**
     * Verifica si un usuario puede acceder a información de pacientes
     * 
     * @param user Usuario a verificar
     * @return true si puede acceder
     */
    public boolean canAccessPatientInfo(User user) {
        return user != null && user.isActive() &&
                (user.getRole() == User.Role.PERSONAL_ADMINISTRATIVO ||
                        user.getRole() == User.Role.ENFERMERA ||
                        user.getRole() == User.Role.MEDICO);
    }

    /**
     * Verifica si un usuario puede gestionar otros usuarios
     * 
     * @param user Usuario a verificar
     * @return true si puede gestionar usuarios
     */
    public boolean canManageUsers(User user) {
        return user != null && user.isActive() &&
                user.getRole() == User.Role.RECURSOS_HUMANOS;
    }

    /**
     * Verifica si un usuario puede gestionar inventario
     * 
     * @param user Usuario a verificar
     * @return true si puede gestionar inventario
     */
    public boolean canManageInventory(User user) {
        return user != null && user.isActive() &&
                user.getRole() == User.Role.SOPORTE_INFORMACION;
    }

    /**
     * Verifica si un usuario puede crear historias clínicas
     * 
     * @param user Usuario a verificar
     * @return true si puede crear historias clínicas
     */
    public boolean canCreateMedicalRecords(User user) {
        return user != null && user.isActive() &&
                user.getRole() == User.Role.MEDICO;
    }

    /**
     * Verifica si un usuario puede registrar signos vitales
     * 
     * @param user Usuario a verificar
     * @return true si puede registrar signos vitales
     */
    public boolean canRegisterVitals(User user) {
        return user != null && user.isActive() &&
                user.getRole() == User.Role.ENFERMERA;
    }
}
