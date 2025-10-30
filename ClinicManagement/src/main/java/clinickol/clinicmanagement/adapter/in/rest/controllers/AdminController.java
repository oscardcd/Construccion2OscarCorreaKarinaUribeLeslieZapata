package clinickol.clinicmanagement.adapter.in.rest.controllers;

import clinickol.clinicmanagement.adapter.in.rest.mapper.EmpleadoRestMapper;
import clinickol.clinicmanagement.adapter.in.rest.request.EmpleadoRequest;
import clinickol.clinicmanagement.application.usecases.AdminUseCase;
import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.MedicamentoDomain;
import clinickol.clinicmanagement.domain.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminUseCase adminUseCase;

    @Autowired
    private EmpleadoRestMapper empleadoRestMapper;

    // Gestión de empleados
    @PostMapping("/empleados/medico")
    public ResponseEntity<String> createMedico(@RequestBody EmpleadoRequest request) throws Exception {
        EmpleadoDomain empleado = empleadoRestMapper.toDomain(request);
        adminUseCase.createMedico(empleado);
        return ResponseEntity.ok("Médico creado exitosamente");
    }

    @PostMapping("/empleados/enfermero")
    public ResponseEntity<String> createEnfermero(@RequestBody EmpleadoRequest request) throws Exception {
        EmpleadoDomain empleado = empleadoRestMapper.toDomain(request);
        adminUseCase.createEnfermero(empleado);
        return ResponseEntity.ok("Enfermero creado exitosamente");
    }

    @PostMapping("/empleados/recepcionista")
    public ResponseEntity<String> createRecepcionista(@RequestBody EmpleadoRequest request) throws Exception {
        EmpleadoDomain empleado = empleadoRestMapper.toDomain(request);
        adminUseCase.createRecepcionista(empleado);
        return ResponseEntity.ok("Recepcionista creado exitosamente");
    }

    @PostMapping("/empleados/admin")
    public ResponseEntity<String> createAdmin(@RequestBody EmpleadoRequest request) throws Exception {
        EmpleadoDomain empleado = empleadoRestMapper.toDomain(request);
        adminUseCase.createAdmin(empleado);
        return ResponseEntity.ok("Administrador creado exitosamente");
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<String> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoRequest request) throws Exception {
        EmpleadoDomain empleado = empleadoRestMapper.toDomain(request);
        adminUseCase.updateEmpleado(id, empleado);
        return ResponseEntity.ok("Empleado actualizado exitosamente");
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<String> deactivateEmpleado(@PathVariable Long id) throws Exception {
        adminUseCase.deactivateEmpleado(id);
        return ResponseEntity.ok("Empleado desactivado exitosamente");
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoDomain>> getAllEmpleados() throws Exception {
        return ResponseEntity.ok(adminUseCase.getAllEmpleados());
    }

    @GetMapping("/empleados/rol/{rol}")
    public ResponseEntity<List<EmpleadoDomain>> getEmpleadosByRol(@PathVariable String rol) throws Exception {
        Role roleEnum = Role.valueOf(rol.toUpperCase());
        return ResponseEntity.ok(adminUseCase.getEmpleadosByRol(roleEnum));
    }

    // Gestión de medicamentos
    @PostMapping("/medicamentos")
    public ResponseEntity<String> createMedicamento(@RequestBody MedicamentoDomain medicamento) throws Exception {
        adminUseCase.createMedicamento(medicamento);
        return ResponseEntity.ok("Medicamento creado exitosamente");
    }

    @PutMapping("/medicamentos/{id}")
    public ResponseEntity<String> updateMedicamento(@PathVariable Long id, @RequestBody MedicamentoDomain medicamento) throws Exception {
        adminUseCase.updateMedicamento(id, medicamento);
        return ResponseEntity.ok("Medicamento actualizado exitosamente");
    }

    @GetMapping("/medicamentos")
    public ResponseEntity<List<MedicamentoDomain>> getAllMedicamentos() throws Exception {
        return ResponseEntity.ok(adminUseCase.getAllMedicamentos());
    }
}

