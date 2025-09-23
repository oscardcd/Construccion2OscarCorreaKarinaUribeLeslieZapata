package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Servicio de dominio para validaciones y reglas de negocio de pacientes
 * Contiene lógica pura del dominio sin dependencias externas
 */
@Service
public class PatientDomainService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");

    private static final Pattern DOCUMENT_PATTERN = Pattern.compile("^[0-9]{6,12}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");

    private static final int MAX_AGE = 150;
    private static final int MAX_ADDRESS_LENGTH = 30;

    /**
     * Valida los datos de un paciente según las reglas de negocio
     * 
     * @param patient Paciente a validar
     * @throws IllegalArgumentException Si los datos no son válidos
     */
    public void validatePatientData(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }

        validateDocumentNumber(patient.getDocumentNumber());
        validateFullName(patient.getFullName());
        validateBirthDate(patient.getBirthDate());
        validateGender(patient.getGender());
        validateAddress(patient.getAddress());
        validatePhone(patient.getPhone());
        validateEmail(patient.getEmail());
        validateEmergencyContact(patient);
        validateInsuranceInfo(patient);
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
     * Valida la fecha de nacimiento
     */
    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        }

        int age = LocalDate.now().getYear() - birthDate.getYear();
        if (age < 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y " + MAX_AGE + " años");
        }
    }

    /**
     * Valida el género
     */
    private void validateGender(Patient.Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("El género es obligatorio");
        }
    }

    /**
     * Valida la dirección
     */
    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }

        if (address.length() > MAX_ADDRESS_LENGTH) {
            throw new IllegalArgumentException("La dirección no puede exceder " + MAX_ADDRESS_LENGTH + " caracteres");
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
            throw new IllegalArgumentException("El teléfono debe contener exactamente 10 dígitos");
        }
    }

    /**
     * Valida el email (opcional)
     */
    private void validateEmail(String email) {
        if (email != null && !email.trim().isEmpty()) {
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                throw new IllegalArgumentException("El formato del email no es válido");
            }
        }
    }

    /**
     * Valida la información de contacto de emergencia
     */
    private void validateEmergencyContact(Patient patient) {
        if (patient.getEmergencyContactName() == null || patient.getEmergencyContactName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del contacto de emergencia es obligatorio");
        }

        if (patient.getEmergencyContactRelation() == null || patient.getEmergencyContactRelation().trim().isEmpty()) {
            throw new IllegalArgumentException("La relación del contacto de emergencia es obligatoria");
        }

        if (patient.getEmergencyContactPhone() == null || patient.getEmergencyContactPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono del contacto de emergencia es obligatorio");
        }

        if (!PHONE_PATTERN.matcher(patient.getEmergencyContactPhone()).matches()) {
            throw new IllegalArgumentException(
                    "El teléfono del contacto de emergencia debe contener exactamente 10 dígitos");
        }
    }

    /**
     * Valida la información de seguro médico
     */
    private void validateInsuranceInfo(Patient patient) {
        // La información de seguro es opcional, pero si se proporciona debe ser válida
        if (patient.getInsuranceCompany() != null && !patient.getInsuranceCompany().trim().isEmpty()) {
            if (patient.getPolicyNumber() == null || patient.getPolicyNumber().trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Si se proporciona la compañía de seguros, el número de póliza es obligatorio");
            }
        }

        if (patient.getPolicyNumber() != null && !patient.getPolicyNumber().trim().isEmpty()) {
            if (patient.getInsuranceCompany() == null || patient.getInsuranceCompany().trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Si se proporciona el número de póliza, la compañía de seguros es obligatoria");
            }
        }
    }

    /**
     * Verifica si un paciente tiene seguro activo
     * 
     * @param patient Paciente a verificar
     * @return true si tiene seguro activo
     */
    public boolean hasActiveInsurance(Patient patient) {
        if (patient == null) {
            return false;
        }

        return patient.isPolicyActive() &&
                patient.getPolicyExpirationDate() != null &&
                patient.getPolicyExpirationDate().isAfter(LocalDate.now());
    }

    /**
     * Verifica si un paciente tiene información de contacto válida
     * 
     * @param patient Paciente a verificar
     * @return true si tiene contacto válido
     */
    public boolean hasValidContact(Patient patient) {
        if (patient == null) {
            return false;
        }

        return (patient.getPhone() != null && !patient.getPhone().trim().isEmpty()) ||
                (patient.getEmail() != null && !patient.getEmail().trim().isEmpty());
    }

    /**
     * Verifica si un paciente tiene contacto de emergencia válido
     * 
     * @param patient Paciente a verificar
     * @return true si tiene contacto de emergencia válido
     */
    public boolean hasValidEmergencyContact(Patient patient) {
        if (patient == null) {
            return false;
        }

        return patient.getEmergencyContactName() != null && !patient.getEmergencyContactName().trim().isEmpty() &&
                patient.getEmergencyContactPhone() != null && !patient.getEmergencyContactPhone().trim().isEmpty();
    }

    /**
     * Calcula la edad de un paciente
     * 
     * @param patient Paciente
     * @return Edad en años
     */
    public int calculateAge(Patient patient) {
        if (patient == null || patient.getBirthDate() == null) {
            return 0;
        }

        return LocalDate.now().getYear() - patient.getBirthDate().getYear();
    }

    /**
     * Verifica si un paciente es adulto
     * 
     * @param patient Paciente
     * @return true si es adulto
     */
    public boolean isAdult(Patient patient) {
        return calculateAge(patient) >= 18;
    }

    /**
     * Valida que un paciente pueda ser registrado
     * 
     * @param patient Paciente a validar
     * @throws IllegalArgumentException Si no puede ser registrado
     */
    public void validatePatientCanBeRegistered(Patient patient) {
        validatePatientData(patient);

        if (!hasValidContact(patient)) {
            throw new IllegalArgumentException("El paciente debe tener al menos un medio de contacto válido");
        }

        if (!hasValidEmergencyContact(patient)) {
            throw new IllegalArgumentException("El paciente debe tener un contacto de emergencia válido");
        }
    }
}
