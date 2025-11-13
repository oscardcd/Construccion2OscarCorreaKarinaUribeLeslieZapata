package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.application.exceptions.BusinessException;
import clinickol.clinicmanagement.domain.model.PacienteDomain;
import clinickol.clinicmanagement.domain.ports.PacientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePaciente {

    @Autowired
    private PacientePort pacientePort;

    public void create(PacienteDomain paciente) throws Exception {
        // Validar cédula única
        if (pacientePort.existsByCedula(paciente.getCedula())) {
            throw new BusinessException("Ya existe un paciente con la cédula: " + paciente.getCedula());
        }

        // Guardar paciente
        pacientePort.save(paciente);
    }
}

