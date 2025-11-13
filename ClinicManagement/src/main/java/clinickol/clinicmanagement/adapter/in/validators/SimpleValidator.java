package clinickol.clinicmanagement.adapter.in.validators;

import clinickol.clinicmanagement.application.exceptions.InputsException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class SimpleValidator {

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^\\d{10}$");
    
    private static final Pattern CEDULA_PATTERN = 
        Pattern.compile("^\\d{1,10}$");

    public void validateEmail(String email) throws InputsException {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InputsException("Email inválido: " + email);
        }
    }

    public void validatePhone(String phone) throws InputsException {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            throw new InputsException("Teléfono inválido. Debe tener 10 dígitos: " + phone);
        }
    }

    public void validateCedula(String cedula) throws InputsException {
        if (cedula == null || !CEDULA_PATTERN.matcher(cedula).matches()) {
            throw new InputsException("Cédula inválida: " + cedula);
        }
    }

    public void validateNotEmpty(String value, String fieldName) throws InputsException {
        if (value == null || value.trim().isEmpty()) {
            throw new InputsException("El campo " + fieldName + " es requerido");
        }
    }

    public void validatePastDate(LocalDate date, String fieldName) throws InputsException {
        if (date == null) {
            throw new InputsException("El campo " + fieldName + " es requerido");
        }
        if (!date.isBefore(LocalDate.now())) {
            throw new InputsException("El campo " + fieldName + " debe ser una fecha pasada");
        }
    }

    public void validateFutureDate(LocalDate date, String fieldName) throws InputsException {
        if (date == null) {
            throw new InputsException("El campo " + fieldName + " es requerido");
        }
        if (!date.isAfter(LocalDate.now())) {
            throw new InputsException("El campo " + fieldName + " debe ser una fecha futura");
        }
    }
}

