package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.application.exceptions.BusinessException;
import clinickol.clinicmanagement.domain.model.PacienteDomain;
import clinickol.clinicmanagement.domain.ports.PacientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePaciente {

    @Autowired
    private PacientePort pacientePort;

    public void update(Long id, PacienteDomain pacienteActualizado) throws Exception {
        PacienteDomain paciente = pacientePort.findById(id);
        
        if (paciente == null) {
            throw new BusinessException("Paciente no encontrado con ID: " + id);
        }

        // Actualizar campos
        paciente.setNombreCompleto(pacienteActualizado.getNombreCompleto());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setGenero(pacienteActualizado.getGenero());
        paciente.setDireccion(pacienteActualizado.getDireccion());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setCorreoElectronico(pacienteActualizado.getCorreoElectronico());
        paciente.setContactoEmergencia(pacienteActualizado.getContactoEmergencia());
        paciente.setSeguroMedico(pacienteActualizado.getSeguroMedico());

        // Guardar cambios
        pacientePort.save(paciente);
    }

    public void deactivate(Long id) throws Exception {
        PacienteDomain paciente = pacientePort.findById(id);
        
        if (paciente == null) {
            throw new BusinessException("Paciente no encontrado con ID: " + id);
        }

        paciente.setActivo(false);
        pacientePort.save(paciente);
    }
}

