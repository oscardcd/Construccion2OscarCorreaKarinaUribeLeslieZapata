package com.mycompany.clinicmanagement.application.usecases.admin;

import com.mycompany.clinicmanagement.application.port.PatientServicePort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para actualizar un paciente existente
 * Solo puede ser ejecutado por Personal Administrativo
 */
@Component
public class UpdatePatientUseCase {

    private final PatientServicePort patientService;

    public UpdatePatientUseCase(PatientServicePort patientService) {
        this.patientService = patientService;
    }

    /**
     * Ejecuta el caso de uso para actualizar un paciente
     * 
     * @param patient   Paciente con los datos actualizados
     * @param updatedBy Usuario que actualiza (debe ser Personal Administrativo)
     * @return Paciente actualizado
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     */
    public Patient execute(Patient patient, User updatedBy) {
        // Validar que el usuario sea Personal Administrativo
        if (!patientService.canAccessPatientInfo(updatedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede actualizar pacientes");
        }

        // Validar datos del paciente
        patientService.validatePatientData(patient);

        // Actualizar el paciente
        return patientService.updatePatient(patient, updatedBy);
    }
}
