package clinickol.clinicmanagement.application.service;

import clinickol.clinicmanagement.application.ports.in.PatientInputPort;
import clinickol.clinicmanagement.application.ports.out.PatientOutputPort;
import clinickol.clinicmanagement.domain.model.PatientDomain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientUseCaseService implements PatientInputPort {

    private final PatientOutputPort patientOutputPort;

    public PatientUseCaseService(PatientOutputPort patientOutputPort) {
        this.patientOutputPort = patientOutputPort;
    }

    @Override
    public PatientDomain crearPaciente(PatientDomain patient) {
        if (!patient.esValido()) {
            throw new RuntimeException("Datos del paciente inválidos");
        }

        if (patientOutputPort.existePorEmail(patient.getEmail())) {
            throw new RuntimeException("Ya existe un paciente con el email: " + patient.getEmail());
        }

        if (patientOutputPort.existePorDocumento(patient.getDocumento())) {
            throw new RuntimeException("Ya existe un paciente con el documento: " + patient.getDocumento());
        }

        return patientOutputPort.guardar(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDomain> obtenerTodosLosPacientes() {
        return patientOutputPort.buscarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDomain> obtenerPacientesActivos() {
        return patientOutputPort.buscarActivos();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientDomain> obtenerPacientePorId(Long id) {
        return patientOutputPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientDomain> obtenerPacientePorEmail(String email) {
        return patientOutputPort.buscarPorEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientDomain> obtenerPacientePorDocumento(String documento) {
        return patientOutputPort.buscarPorDocumento(documento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDomain> buscarPacientesPorNombre(String nombre) {
        return patientOutputPort.buscarPorNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDomain> buscarPacientesPorApellido(String apellido) {
        return patientOutputPort.buscarPorApellido(apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDomain> buscarPacientesPorGenero(String genero) {
        return patientOutputPort.buscarPorGenero(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDomain> buscarPacientesPorCiudad(String ciudad) {
        return patientOutputPort.buscarPorCiudad(ciudad);
    }

    @Override
    public PatientDomain actualizarPaciente(Long id, PatientDomain patientActualizado) {
        PatientDomain patient = patientOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        if (!patient.getEmail().equals(patientActualizado.getEmail()) &&
                patientOutputPort.existePorEmail(patientActualizado.getEmail())) {
            throw new RuntimeException("Ya existe un paciente con el email: " + patientActualizado.getEmail());
        }

        if (!patient.getDocumento().equals(patientActualizado.getDocumento()) &&
                patientOutputPort.existePorDocumento(patientActualizado.getDocumento())) {
            throw new RuntimeException("Ya existe un paciente con el documento: " + patientActualizado.getDocumento());
        }

        patient.setNombre(patientActualizado.getNombre());
        patient.setApellido(patientActualizado.getApellido());
        patient.setEmail(patientActualizado.getEmail());
        patient.setTelefono(patientActualizado.getTelefono());
        patient.setDocumento(patientActualizado.getDocumento());
        patient.setTipoDocumento(patientActualizado.getTipoDocumento());
        patient.setFechaNacimiento(patientActualizado.getFechaNacimiento());
        patient.setGenero(patientActualizado.getGenero());
        patient.setDireccion(patientActualizado.getDireccion());
        patient.setCiudad(patientActualizado.getCiudad());
        patient.setCodigoPostal(patientActualizado.getCodigoPostal());
        patient.setContactoEmergencia(patientActualizado.getContactoEmergencia());
        patient.setTelefonoEmergencia(patientActualizado.getTelefonoEmergencia());
        patient.setAlergias(patientActualizado.getAlergias());
        patient.setMedicamentosActuales(patientActualizado.getMedicamentosActuales());
        patient.setHistorialMedico(patientActualizado.getHistorialMedico());
        patient.setGrupoSanguineo(patientActualizado.getGrupoSanguineo());
        patient.setPeso(patientActualizado.getPeso());
        patient.setAltura(patientActualizado.getAltura());
        patient.setActivo(patientActualizado.getActivo());
        patient.setFechaActualizacion(LocalDateTime.now());

        return patientOutputPort.guardar(patient);
    }

    @Override
    public void desactivarPaciente(Long id) {
        PatientDomain patient = patientOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        patient.setActivo(false);
        patient.setFechaActualizacion(LocalDateTime.now());
        patientOutputPort.guardar(patient);
    }

    @Override
    public void activarPaciente(Long id) {
        PatientDomain patient = patientOutputPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        patient.setActivo(true);
        patient.setFechaActualizacion(LocalDateTime.now());
        patientOutputPort.guardar(patient);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!patientOutputPort.existePorId(id)) {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
        patientOutputPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarPacientesActivos() {
        return patientOutputPort.contarActivos();
    }

    @Override
    @Transactional(readOnly = true)
    public long contarPacientesPorGenero(String genero) {
        return patientOutputPort.contarPorGenero(genero);
    }
}

