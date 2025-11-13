package clinickol.clinicmanagement.service;

import clinickol.clinicmanagement.domain.model.Empleado;
import clinickol.clinicmanagement.domain.model.enums.Role;
import clinickol.clinicmanagement.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado crearEmpleado(Empleado empleado) {
        if (empleadoRepository.existsByCedula(empleado.getCedula())) {
            throw new RuntimeException("Ya existe un empleado con la cédula: " + empleado.getCedula());
        }
        if (empleadoRepository.existsByNombreUsuario(empleado.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso: " + empleado.getNombreUsuario());
        }
        if (empleadoRepository.existsByCorreoElectronico(empleado.getCorreoElectronico())) {
            throw new RuntimeException("El correo electrónico ya está registrado: " + empleado.getCorreoElectronico());
        }
        return empleadoRepository.save(empleado);
    }

    @Transactional(readOnly = true)
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Empleado> obtenerPorCedula(String cedula) {
        return empleadoRepository.findByCedula(cedula);
    }

    @Transactional(readOnly = true)
    public List<Empleado> obtenerPorRol(Role rol) {
        return empleadoRepository.findByRol(rol);
    }

    @Transactional(readOnly = true)
    public List<Empleado> obtenerActivos() {
        return empleadoRepository.findByActivoTrue();
    }

    public Empleado actualizar(Long id, Empleado empleadoActualizado) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));

        empleado.setNombreCompleto(empleadoActualizado.getNombreCompleto());
        empleado.setCorreoElectronico(empleadoActualizado.getCorreoElectronico());
        empleado.setTelefono(empleadoActualizado.getTelefono());
        empleado.setDireccion(empleadoActualizado.getDireccion());
        empleado.setRol(empleadoActualizado.getRol());

        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    public void desactivar(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
        empleado.setActivo(false);
        empleadoRepository.save(empleado);
    }
}
