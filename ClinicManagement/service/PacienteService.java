package clinickol.clinicmanagement.service;

import clinickol.clinicmanagement.domain.model.Paciente;
import clinickol.clinicmanagement.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) {
        if (pacienteRepository.existsByCedula(paciente.getCedula())) {
            throw new RuntimeException("Ya existe un paciente con la cédula: " + paciente.getCedula());
        }
        paciente.reiniciarCopagoSiEsNuevoAno();
        return pacienteRepository.save(paciente);
    }

    @Transactional(readOnly = true)
    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Paciente> obtenerPorCedula(String cedula) {
        return pacienteRepository.findByCedula(cedula);
    }

    @Transactional(readOnly = true)
    public List<Paciente> obtenerActivos() {
        return pacienteRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.findByNombreCompletoContainingIgnoreCase(nombre);
    }

    public Paciente actualizar(Long id, Paciente pacienteActualizado) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        paciente.setNombreCompleto(pacienteActualizado.getNombreCompleto());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setGenero(pacienteActualizado.getGenero());
        paciente.setDireccion(pacienteActualizado.getDireccion());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setCorreoElectronico(pacienteActualizado.getCorreoElectronico());

        if (pacienteActualizado.getContactoEmergencia() != null) {
            paciente.setContactoEmergencia(pacienteActualizado.getContactoEmergencia());
        }

        if (pacienteActualizado.getSeguroMedico() != null) {
            paciente.setSeguroMedico(pacienteActualizado.getSeguroMedico());
        }

        return pacienteRepository.save(paciente);
    }

    public void agregarCopago(String cedula, Double monto) {
        Paciente paciente = pacienteRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.reiniciarCopagoSiEsNuevoAno();
        paciente.setCopagoAnualAcumulado(paciente.getCopagoAnualAcumulado() + monto);
        pacienteRepository.save(paciente);
    }

    public void desactivar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
        paciente.setActivo(false);
        pacienteRepository.save(paciente);
    }
}
