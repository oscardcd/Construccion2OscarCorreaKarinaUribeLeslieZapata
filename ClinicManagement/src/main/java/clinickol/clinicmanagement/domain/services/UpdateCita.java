package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.application.exceptions.BusinessException;
import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.model.enums.EstadoCita;
import clinickol.clinicmanagement.domain.ports.CitaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateCita {

    @Autowired
    private CitaPort citaPort;

    public void update(Long id, CitaDomain citaActualizada) throws Exception {
        CitaDomain cita = citaPort.findById(id);
        
        if (cita == null) {
            throw new BusinessException("Cita no encontrada con ID: " + id);
        }

        // Validar que la fecha y hora no sea en el pasado (si se está cambiando)
        if (citaActualizada.getFechaHora() != null && 
            !citaActualizada.getFechaHora().equals(cita.getFechaHora())) {
            if (citaActualizada.getFechaHora().isBefore(LocalDateTime.now())) {
                throw new BusinessException("No se puede programar una cita en el pasado");
            }
        }

        // Actualizar campos
        if (citaActualizada.getFechaHora() != null) {
            cita.setFechaHora(citaActualizada.getFechaHora());
        }
        if (citaActualizada.getEstado() != null) {
            cita.setEstado(citaActualizada.getEstado());
        }
        if (citaActualizada.getMotivo() != null) {
            cita.setMotivo(citaActualizada.getMotivo());
        }
        if (citaActualizada.getNotas() != null) {
            cita.setNotas(citaActualizada.getNotas());
        }

        // Guardar cambios
        citaPort.save(cita);
    }

    public void cambiarEstado(Long id, EstadoCita nuevoEstado) throws Exception {
        CitaDomain cita = citaPort.findById(id);
        
        if (cita == null) {
            throw new BusinessException("Cita no encontrada con ID: " + id);
        }

        cita.setEstado(nuevoEstado);
        citaPort.save(cita);
    }

    public void cancelar(Long id) throws Exception {
        cambiarEstado(id, EstadoCita.CANCELADA);
    }

    public void completar(Long id) throws Exception {
        cambiarEstado(id, EstadoCita.COMPLETADA);
    }
}

