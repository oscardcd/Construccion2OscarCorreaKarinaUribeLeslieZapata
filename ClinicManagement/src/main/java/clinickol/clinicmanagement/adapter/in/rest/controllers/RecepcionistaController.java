package clinickol.clinicmanagement.adapter.in.rest.controllers;

import clinickol.clinicmanagement.application.usecases.RecepcionistaUseCase;
import clinickol.clinicmanagement.domain.model.PacienteDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recepcionista")
@CrossOrigin(origins = "*")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaUseCase recepcionistaUseCase;

    @PostMapping("/pacientes")
    public ResponseEntity<String> registerPaciente(@RequestBody PacienteDomain paciente) throws Exception {
        recepcionistaUseCase.registerPaciente(paciente);
        return ResponseEntity.ok("Paciente registrado exitosamente");
    }

    @PutMapping("/pacientes/{id}")
    public ResponseEntity<String> updatePaciente(@PathVariable Long id, @RequestBody PacienteDomain paciente) throws Exception {
        recepcionistaUseCase.updatePaciente(id, paciente);
        return ResponseEntity.ok("Paciente actualizado exitosamente");
    }

    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<String> deactivatePaciente(@PathVariable Long id) throws Exception {
        recepcionistaUseCase.deactivatePaciente(id);
        return ResponseEntity.ok("Paciente desactivado exitosamente");
    }

    @GetMapping("/pacientes/cedula/{cedula}")
    public ResponseEntity<PacienteDomain> getPacienteByCedula(@PathVariable String cedula) throws Exception {
        PacienteDomain paciente = recepcionistaUseCase.getPacienteByCedula(cedula);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<PacienteDomain> getPacienteById(@PathVariable Long id) throws Exception {
        PacienteDomain paciente = recepcionistaUseCase.getPacienteById(id);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<PacienteDomain>> getAllPacientes() throws Exception {
        return ResponseEntity.ok(recepcionistaUseCase.getAllPacientes());
    }

    @GetMapping("/pacientes/activos")
    public ResponseEntity<List<PacienteDomain>> getActivePacientes() throws Exception {
        return ResponseEntity.ok(recepcionistaUseCase.getActivePacientes());
    }
}

