package clinickol.clinicmanagement.adapter.in.rest.controllers;

import clinickol.clinicmanagement.application.usecases.CitaUseCase;
import clinickol.clinicmanagement.domain.model.CitaDomain;
import clinickol.clinicmanagement.domain.model.enums.EstadoCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaUseCase citaUseCase;

    @PostMapping
    public ResponseEntity<String> crearCita(@RequestBody CitaDomain cita) throws Exception {
        citaUseCase.crearCita(cita);
        return ResponseEntity.ok("Cita creada exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCita(@PathVariable Long id, @RequestBody CitaDomain cita) throws Exception {
        citaUseCase.actualizarCita(id, cita);
        return ResponseEntity.ok("Cita actualizada exitosamente");
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarCita(@PathVariable Long id) throws Exception {
        citaUseCase.cancelarCita(id);
        return ResponseEntity.ok("Cita cancelada exitosamente");
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<String> completarCita(@PathVariable Long id) throws Exception {
        citaUseCase.completarCita(id);
        return ResponseEntity.ok("Cita completada exitosamente");
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<String> cambiarEstadoCita(
            @PathVariable Long id, 
            @RequestParam EstadoCita estado) throws Exception {
        citaUseCase.cambiarEstadoCita(id, estado);
        return ResponseEntity.ok("Estado de la cita actualizado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDomain> getCitaById(@PathVariable Long id) throws Exception {
        CitaDomain cita = citaUseCase.getCitaById(id);
        if (cita == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cita);
    }

    @GetMapping
    public ResponseEntity<List<CitaDomain>> getAllCitas() throws Exception {
        return ResponseEntity.ok(citaUseCase.getAllCitas());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaDomain>> getCitasByPaciente(@PathVariable Long pacienteId) throws Exception {
        return ResponseEntity.ok(citaUseCase.getCitasByPaciente(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaDomain>> getCitasByMedico(@PathVariable Long medicoId) throws Exception {
        return ResponseEntity.ok(citaUseCase.getCitasByMedico(medicoId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<CitaDomain>> getCitasByFechaHoraBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) throws Exception {
        return ResponseEntity.ok(citaUseCase.getCitasByFechaHoraBetween(inicio, fin));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaDomain>> getCitasByEstado(@PathVariable EstadoCita estado) throws Exception {
        return ResponseEntity.ok(citaUseCase.getCitasByEstado(estado));
    }

    @GetMapping("/paciente/{pacienteId}/estado/{estado}")
    public ResponseEntity<List<CitaDomain>> getCitasByPacienteAndEstado(
            @PathVariable Long pacienteId, 
            @PathVariable EstadoCita estado) throws Exception {
        return ResponseEntity.ok(citaUseCase.getCitasByPacienteAndEstado(pacienteId, estado));
    }

    @GetMapping("/medico/{medicoId}/estado/{estado}")
    public ResponseEntity<List<CitaDomain>> getCitasByMedicoAndEstado(
            @PathVariable Long medicoId, 
            @PathVariable EstadoCita estado) throws Exception {
        return ResponseEntity.ok(citaUseCase.getCitasByMedicoAndEstado(medicoId, estado));
    }
}

