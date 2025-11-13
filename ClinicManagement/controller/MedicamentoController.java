package clinickol.clinicmanagement.controller;

import clinickol.clinicmanagement.domain.model.Medicamento;
import clinickol.clinicmanagement.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<Medicamento> crear(@Valid @RequestBody Medicamento medicamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.crear(medicamento));
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> obtenerTodos() {
        return ResponseEntity.ok(medicamentoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return medicamentoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Medicamento>> obtenerActivos() {
        return ResponseEntity.ok(medicamentoService.obtenerActivos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Medicamento>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(medicamentoService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Medicamento medicamento) {
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
