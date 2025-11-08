package clinickol.clinicmanagement.controller;

import clinickol.clinicmanagement.domain.model.Medication;
import clinickol.clinicmanagement.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/medication")
@CrossOrigin(origins = "*")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<Medication> crear(@Valid @RequestBody Medication medication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicationService.crear(medication));
    }

    @GetMapping
    public ResponseEntity<List<Medication>> obtenerTodos() {
        return ResponseEntity.ok(medicationService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return medicamentoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Medication>> obtenerActivos() {
        return ResponseEntity.ok(medicamentoService.obtenerActivos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Medication>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(medicamentoService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Medication medicamento) {
        try {
            return ResponseEntity.ok(medicamentoService.actualizar(id, medicamento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
