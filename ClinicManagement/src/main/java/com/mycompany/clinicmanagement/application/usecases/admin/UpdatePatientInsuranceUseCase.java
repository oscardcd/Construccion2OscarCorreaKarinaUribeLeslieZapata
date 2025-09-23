package com.mycompany.clinicmanagement.application.usecases.admin;

import com.mycompany.clinicmanagement.application.port.PatientServicePort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Caso de uso para actualizar información de seguro de un paciente
 * Solo puede ser ejecutado por Personal Administrativo
 */
@Component
public class UpdatePatientInsuranceUseCase {

    private final PatientServicePort patientService;

    public UpdatePatientInsuranceUseCase(PatientServicePort patientService) {
        this.patientService = patientService;
    }

    /**
     * Ejecuta el caso de uso para actualizar información de seguro
     * 
     * @param patientDocumentNumber Cédula del paciente
     * @param insuranceCompany      Compañía de seguros
     * @param policyNumber          Número de póliza
     * @param isActive              Si la póliza está activa
     * @param expirationDate        Fecha de vencimiento
     * @param updatedBy             Usuario que actualiza (debe ser Personal
     *                              Administrativo)
     * @return Paciente actualizado
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     */
    public Patient execute(String patientDocumentNumber, String insuranceCompany,
            String policyNumber, boolean isActive,
            LocalDate expirationDate, User updatedBy) {
        // Validar que el usuario sea Personal Administrativo
        if (!patientService.canAccessPatientInfo(updatedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede actualizar información de seguros");
        }

        // Actualizar información de seguro
        return patientService.updateInsuranceInfo(patientDocumentNumber, insuranceCompany,
                policyNumber, isActive, expirationDate, updatedBy);
    }
}
