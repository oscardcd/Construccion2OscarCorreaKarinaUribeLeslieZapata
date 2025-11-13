package clinickol.clinicmanagement.application.usecases;

import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.MedicamentoDomain;
import clinickol.clinicmanagement.domain.model.enums.Role;
import clinickol.clinicmanagement.domain.ports.EmpleadoPort;
import clinickol.clinicmanagement.domain.ports.MedicamentoPort;
import clinickol.clinicmanagement.domain.services.CreateEmpleado;
import clinickol.clinicmanagement.domain.services.UpdateEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUseCase {

    @Autowired
    private CreateEmpleado createEmpleado;

    @Autowired
    private UpdateEmpleado updateEmpleado;

    @Autowired
    private EmpleadoPort empleadoPort;

    @Autowired
    private MedicamentoPort medicamentoPort;

    // Gestión de empleados
    public void createMedico(EmpleadoDomain empleado) throws Exception {
        empleado.setRol(Role.MEDICO);
        createEmpleado.create(empleado);
    }

    public void createEnfermero(EmpleadoDomain empleado) throws Exception {
        empleado.setRol(Role.ENFERMERO);
        createEmpleado.create(empleado);
    }

    public void createRecepcionista(EmpleadoDomain empleado) throws Exception {
        empleado.setRol(Role.RECEPCIONISTA);
        createEmpleado.create(empleado);
    }

    public void createAdmin(EmpleadoDomain empleado) throws Exception {
        empleado.setRol(Role.ADMIN);
        createEmpleado.create(empleado);
    }

    public void updateEmpleado(Long id, EmpleadoDomain empleado) throws Exception {
        updateEmpleado.update(id, empleado);
    }

    public void deactivateEmpleado(Long id) throws Exception {
        updateEmpleado.deactivate(id);
    }

    public List<EmpleadoDomain> getAllEmpleados() throws Exception {
        return empleadoPort.findAll();
    }

    public List<EmpleadoDomain> getEmpleadosByRol(Role rol) throws Exception {
        return empleadoPort.findByRol(rol);
    }

    // Gestión de medicamentos
    public void createMedicamento(MedicamentoDomain medicamento) throws Exception {
        medicamentoPort.save(medicamento);
    }

    public void updateMedicamento(Long id, MedicamentoDomain medicamento) throws Exception {
        MedicamentoDomain existing = medicamentoPort.findById(id);
        if (existing == null) {
            throw new Exception("Medicamento no encontrado");
        }
        medicamento.setId(id);
        medicamentoPort.save(medicamento);
    }

    public List<MedicamentoDomain> getAllMedicamentos() throws Exception {
        return medicamentoPort.findAll();
    }
}

