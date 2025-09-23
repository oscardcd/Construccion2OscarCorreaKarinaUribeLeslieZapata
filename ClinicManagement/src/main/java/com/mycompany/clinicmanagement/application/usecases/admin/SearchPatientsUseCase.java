package com.mycompany.clinicmanagement.application.usecases.admin;

import com.mycompany.clinicmanagement.application.port.PatientServicePort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Caso de uso para buscar pacientes
 * Solo puede ser ejecutado por Personal Administrativo
 */
@Component
public class SearchPatientsUseCase {

    private final PatientServicePort patientService;

    public SearchPatientsUseCase(PatientServicePort patientService) {
        this.patientService = patientService;
    }

    /**
     * Ejecuta el caso de uso para buscar pacientes por nombre
     * 
     * @param name       Nombre o parte del nombre a buscar
     * @param searchedBy Usuario que busca (debe ser Personal Administrativo)
     * @return Lista de pacientes que coinciden con el nombre
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     */
    public List<Patient> executeByName(String name, User searchedBy) {
        // Validar que el usuario sea Personal Administrativo
        if (!patientService.canAccessPatientInfo(searchedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede buscar pacientes");
        }

        // Buscar pacientes por nombre
        return patientService.searchPatientsByName(name);
    }

    /**
     * Ejecuta el caso de uso para obtener todos los pacientes
     * 
     * @param requestedBy Usuario que solicita (debe ser Personal Administrativo)
     * @return Lista de todos los pacientes
     * @throws IllegalArgumentException Si el usuario no es Personal Administrativo
     */
    public List<Patient> executeGetAll(User requestedBy) {
        // Validar que el usuario sea Personal Administrativo
        if (!patientService.canAccessPatientInfo(requestedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede ver pacientes");
        }

        // Obtener todos los pacientes
        return patientService.getAllPatients();
    }
}
