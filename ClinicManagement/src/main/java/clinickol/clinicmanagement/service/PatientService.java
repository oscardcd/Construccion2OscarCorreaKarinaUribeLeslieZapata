package clinickol.clinicmanagement.service;

import clinickol.clinicmanagement.model.Patient;
import clinickol.clinicmanagement.model.Genero;
import clinickol.clinicmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de Pacientes
 */
@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Crear un nuevo paciente
     */
    public Patient crearPaciente(Patient patient) {
        // Validar que no exista un paciente con el mismo email
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Ya existe un paciente con el email: " + patient.getEmail());
        }

        // Validar que no exista un paciente con el mismo documento
        if (patientRepository.existsByDocumento(patient.getDocumento())) {
            throw new RuntimeException("Ya existe un paciente con el documento: " + patient.getDocumento());
        }

        return patientRepository.save(patient);
    }

    /**
     * Obtener todos los pacientes
     */
    @Transactional(readOnly = true)
    public List<Patient> obtenerTodosLosPacientes() {
        return patientRepository.findAll();
    }

    /**
     * Obtener pacientes activos
     */
    @Transactional(readOnly = true)
    public List<Patient> obtenerPacientesActivos() {
        return patientRepository.findByActivoTrue();
    }

    /**
     * Obtener paciente por ID
     */
    @Transactional(readOnly = true)
    public Optional<Patient> obtenerPacientePorId(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Obtener paciente por email
     */
    @Transactional(readOnly = true)
    public Optional<Patient> obtenerPacientePorEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    /**
     * Obtener paciente por documento
     */
    @Transactional(readOnly = true)
    public Optional<Patient> obtenerPacientePorDocumento(String documento) {
        return patientRepository.findByDocumento(documento);
    }

    /**
     * Buscar pacientes por nombre
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorNombre(String nombre) {
        return patientRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Buscar pacientes por apellido
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorApellido(String apellido) {
        return patientRepository.findByApellidoContainingIgnoreCase(apellido);
    }

    /**
     * Buscar pacientes por nombre completo
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorNombreCompleto(String nombreCompleto) {
        return patientRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto);
    }

    /**
     * Buscar pacientes por género
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorGenero(Genero genero) {
        return patientRepository.findByGenero(genero);
    }

    /**
     * Buscar pacientes por ciudad
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorCiudad(String ciudad) {
        return patientRepository.findByCiudad(ciudad);
    }

    /**
     * Buscar pacientes por rango de edad
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorRangoEdad(int edadMinima, int edadMaxima) {
        LocalDate fechaFin = LocalDate.now().minusYears(edadMinima);
        LocalDate fechaInicio = LocalDate.now().minusYears(edadMaxima + 1);
        return patientRepository.findByFechaNacimientoBetween(fechaInicio, fechaFin);
    }

    /**
     * Buscar pacientes con alergias específicas
     */
    @Transactional(readOnly = true)
    public List<Patient> buscarPacientesPorAlergias(String alergia) {
        return patientRepository.findByAlergiasContaining(alergia);
    }

    /**
     * Actualizar paciente
     */
    public Patient actualizarPaciente(Long id, Patient patientActualizado) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        // Validar email único si cambió
        if (!patient.getEmail().equals(patientActualizado.getEmail()) &&
                patientRepository.existsByEmail(patientActualizado.getEmail())) {
            throw new RuntimeException("Ya existe un paciente con el email: " + patientActualizado.getEmail());
        }

        // Validar documento único si cambió
        if (!patient.getDocumento().equals(patientActualizado.getDocumento()) &&
                patientRepository.existsByDocumento(patientActualizado.getDocumento())) {
            throw new RuntimeException("Ya existe un paciente con el documento: " + patientActualizado.getDocumento());
        }

        // Actualizar campos
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

        return patientRepository.save(patient);
    }

    /**
     * Desactivar paciente (soft delete)
     */
    public void desactivarPaciente(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        patient.setActivo(false);
        patientRepository.save(patient);
    }

    /**
     * Activar paciente
     */
    public void activarPaciente(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        patient.setActivo(true);
        patientRepository.save(patient);
    }

    /**
     * Eliminar paciente permanentemente
     */
    public void eliminarPaciente(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }
        patientRepository.deleteById(id);
    }

    /**
     * Obtener estadísticas de pacientes
     */
    @Transactional(readOnly = true)
    public long contarPacientesActivos() {
        return patientRepository.countByActivoTrue();
    }

    /**
     * Obtener estadísticas por género
     */
    @Transactional(readOnly = true)
    public long contarPacientesPorGenero(Genero genero) {
        return patientRepository.countByGenero(genero);
    }
}
