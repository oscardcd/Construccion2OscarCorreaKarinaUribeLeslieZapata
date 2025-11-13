package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.application.exceptions.BusinessException;
import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.ports.CitaPort;
import clinickol.clinicmanagement.domain.ports.PacientePort;
import clinickol.clinicmanagement.domain.ports.EmpleadoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateCita {

    @Autowired
    private CitaPort citaPort;

    @Autowired
    private PacientePort pacientePort;

    @Autowired
    private EmpleadoPort empleadoPort;

    public void create(CitaDomain cita) throws Exception {
        // Validar que el paciente existe y cargar el objeto completo
        if (cita.getPaciente() == null || cita.getPaciente().getId() == null) {
            throw new BusinessException("El paciente es requerido");
        }
        
        var paciente = pacientePort.findById(cita.getPaciente().getId());
        if (paciente == null) {
            throw new BusinessException("Paciente no encontrado con ID: " + cita.getPaciente().getId());
        }
        cita.setPaciente(paciente);

        // Validar que el médico existe y cargar el objeto completo
        if (cita.getMedico() == null || cita.getMedico().getId() == null) {
            throw new BusinessException("El médico es requerido");
        }
        
        var medico = empleadoPort.findById(cita.getMedico().getId());
        if (medico == null) {
            throw new BusinessException("Médico no encontrado con ID: " + cita.getMedico().getId());
        }
        cita.setMedico(medico);

        // Validar que la fecha y hora no sea en el pasado
        if (cita.getFechaHora() == null) {
            throw new BusinessException("La fecha y hora de la cita es requerida");
        }
        
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new BusinessException("No se puede programar una cita en el pasado");
        }

        // Validar que no haya conflicto de horario para el paciente
        if (citaPort.existsByPacienteIdAndFechaHora(cita.getPaciente().getId(), cita.getFechaHora())) {
            throw new BusinessException("El paciente ya tiene una cita programada en esta fecha y hora");
        }

        // Validar que no haya conflicto de horario para el médico
        if (citaPort.existsByMedicoIdAndFechaHora(cita.getMedico().getId(), cita.getFechaHora())) {
            throw new BusinessException("El médico ya tiene una cita programada en esta fecha y hora");
        }

        // Validar motivo
        if (cita.getMotivo() == null || cita.getMotivo().trim().isEmpty()) {
            throw new BusinessException("El motivo de la cita es requerido");
        }

        // Guardar cita
        citaPort.save(cita);
    }
}

