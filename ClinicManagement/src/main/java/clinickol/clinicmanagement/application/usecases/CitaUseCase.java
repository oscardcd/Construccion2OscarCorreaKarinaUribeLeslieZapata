package clinickol.clinicmanagement.application.usecases;

import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.model.enums.EstadoCita;
import clinickol.clinicmanagement.domain.ports.CitaPort;
import clinickol.clinicmanagement.domain.services.CreateCita;
import clinickol.clinicmanagement.domain.services.UpdateCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaUseCase {

    @Autowired
    private CreateCita createCita;

    @Autowired
    private UpdateCita updateCita;

    @Autowired
    private CitaPort citaPort;

    public void crearCita(CitaDomain cita) throws Exception {
        createCita.create(cita);
    }

    public void actualizarCita(Long id, CitaDomain cita) throws Exception {
        updateCita.update(id, cita);
    }

    public void cancelarCita(Long id) throws Exception {
        updateCita.cancelar(id);
    }

    public void completarCita(Long id) throws Exception {
        updateCita.completar(id);
    }

    public void cambiarEstadoCita(Long id, EstadoCita estado) throws Exception {
        updateCita.cambiarEstado(id, estado);
    }

    public CitaDomain getCitaById(Long id) throws Exception {
        return citaPort.findById(id);
    }

    public List<CitaDomain> getAllCitas() throws Exception {
        return citaPort.findAll();
    }

    public List<CitaDomain> getCitasByPaciente(Long pacienteId) throws Exception {
        return citaPort.findByPacienteId(pacienteId);
    }

    public List<CitaDomain> getCitasByMedico(Long medicoId) throws Exception {
        return citaPort.findByMedicoId(medicoId);
    }

    public List<CitaDomain> getCitasByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin) throws Exception {
        return citaPort.findByFechaHoraBetween(inicio, fin);
    }

    public List<CitaDomain> getCitasByEstado(EstadoCita estado) throws Exception {
        return citaPort.findByEstado(estado);
    }

    public List<CitaDomain> getCitasByPacienteAndEstado(Long pacienteId, EstadoCita estado) throws Exception {
        return citaPort.findByPacienteIdAndEstado(pacienteId, estado);
    }

    public List<CitaDomain> getCitasByMedicoAndEstado(Long medicoId, EstadoCita estado) throws Exception {
        return citaPort.findByMedicoIdAndEstado(medicoId, estado);
    }
}

