package clinickol.clinicmanagement.controller;

import clinickol.clinicmanagement.domain.model.Paciente;
import clinickol.clinicmanagement.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteService.registrarPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerTodos() {
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<?> obtenerPorCedula(@PathVariable String cedula) {
        return pacienteService.obtenerPorCedula(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Paciente>> obtenerActivos() {
        return ResponseEntity.ok(pacienteService.obtenerActivos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(pacienteService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        try {
            Paciente pacienteActualizado = pacienteService.actualizar(id, paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable Long id) {
        try {
            pacienteService.desactivar(id);
            return ResponseEntity.ok().body("Paciente desactivado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
