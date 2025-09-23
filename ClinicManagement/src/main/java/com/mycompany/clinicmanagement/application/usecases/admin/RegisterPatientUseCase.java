package com.mycompany.clinicmanagement.application.usecases.admin;

import com.mycompany.clinicmanagement.application.port.PatientServicePort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para registrar un nuevo paciente
 * Solo puede ser ejecutado por Personal Administrativo
 */
@Component
public class RegisterPatientUseCase {

    private final PatientServicePort patientService;

    public RegisterPatientUseCase(PatientServicePort patientService) {
        this.patientService = patientService;
    }

    /**
     * Ejecuta el caso de uso para registrar un paciente
     * 
     * @param patient      Paciente a registrar
     * @param registeredBy Usuario que registra (debe ser Personal Administrativo)
     * @return Paciente registrado
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     * @throws IllegalStateException    Si ya existe un paciente con la misma cédula
     */
    public Patient execute(Patient patient, User registeredBy) {
        // Validar que el usuario sea Personal Administrativo
        if (!patientService.canAccessPatientInfo(registeredBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede registrar pacientes");
        }

        // Validar datos del paciente
        patientService.validatePatientData(patient);

        // Registrar el paciente
        return patientService.registerPatient(patient, registeredBy);
    }
}
