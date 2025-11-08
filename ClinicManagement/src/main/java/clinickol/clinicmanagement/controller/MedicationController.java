package clinickol.clinicmanagement.controller;

import clinickol.clinicmanagement.domain.model.Medication;
import clinickol.clinicmanagement.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarY/medicationS")
@CrossOrigin(origins = "*")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<Medication> crear(@Valid @RequestBody Medication medication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicationService.create(medication));
    }

    @GetMapping
    public ResponseEntity<List<Medication>> obtenerTodos() {
        return ResponseEntity.ok(medicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return medicationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active")
    public ResponseEntity<Object> getActive() {
        return ResponseEntity.ok(medicationService.getActive());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medication>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(medicationService.searchByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Medication medicamento) {
        try {
            return ResponseEntity.ok(medicationService.update(id, medicamento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        medicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
