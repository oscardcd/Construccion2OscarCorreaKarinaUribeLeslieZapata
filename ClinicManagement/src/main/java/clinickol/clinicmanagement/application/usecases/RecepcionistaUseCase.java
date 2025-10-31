package clinickol.clinicmanagement.application.usecases;

import clinickol.clinicmanagement.domain.model.PacienteDomain;
import clinickol.clinicmanagement.domain.ports.PacientePort;
import clinickol.clinicmanagement.domain.services.CreatePaciente;
import clinickol.clinicmanagement.domain.services.UpdatePaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepcionistaUseCase {

    @Autowired
    private CreatePaciente createPaciente;

    @Autowired
    private UpdatePaciente updatePaciente;

    @Autowired
    private PacientePort pacientePort;

    public void registerPaciente(PacienteDomain paciente) throws Exception {
        createPaciente.create(paciente);
    }

    public void updatePaciente(Long id, PacienteDomain paciente) throws Exception {
        updatePaciente.update(id, paciente);
    }

    public void deactivatePaciente(Long id) throws Exception {
        updatePaciente.deactivate(id);
    }

    public PacienteDomain getPacienteByCedula(String cedula) throws Exception {
        return pacientePort.findByCedula(cedula);
    }

    public PacienteDomain getPacienteById(Long id) throws Exception {
        return pacientePort.findById(id);
    }

    public List<PacienteDomain> getAllPacientes() throws Exception {
        return pacientePort.findAll();
    }

    public List<PacienteDomain> getActivePacientes() throws Exception {
        return pacientePort.findActivos();
    }
}

