package clinickol.clinicmanagement.service;

import clinickol.clinicmanagement.domain.model.Paciente;
import clinickol.clinicmanagement.domain.model.Patient;
import clinickol.clinicmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registrarPaciente(Patient patient) {
        if (patientRepository.existsByCedula(patient.getCedula())) {
            throw new RuntimeException("Ya existe un paciente con la cédula: " + patient.getCedula());
        }
        patient.reiniciarCopagoSiEsNuevoAno();
        return patientRepository.save(patient);
    }

    @Transactional(readOnly = true)
    public List<Patient> obtenerTodos() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Patient> obtenerPorId(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Patient> obtenerPorCedula(String cedula) {
        return patientRepository.findByCedula(cedula);
    }

    @Transactional(readOnly = true)
    public List<Patient> obtenerActivos() {
        return patientRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Patient> buscarPorNombre(String nombre) {
        return patientRepository.findByNombreCompletoContainingIgnoreCase(nombre);
    }

    public Patient actualizar(Long id, Patient pacienteActualizado) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        paciente.setNombreCompleto(pacienteActualizado.getNombreCompleto());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setGenero(pacienteActualizado.getGenero());
        paciente.setDireccion(pacienteActualizado.getDireccion());
        paciente.setTelefono(pacienteActualizado.getTelefono());
        paciente.setCorreoElectronico(pacienteActualizado.getCorreoElectronico());

        if (pacienteActualizado.getContactoEmergencia() != null) {
            patient.setContactoEmergencia(pacienteActualizado.getContactoEmergencia());
        }

        if (pacienteActualizado.getSeguroMedico() != null) {
            patient.setSeguroMedico(pacienteActualizado.getSeguroMedico());
        }

        return patientRepository.save(patient);
    }

    public void agregarCopago(String cedula, Double monto) {
        Patient patient = patientRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        patient.reiniciarCopagoSiEsNuevoAno();
        patient.setCopagoAnualAcumulado(patient.getCopagoAnualAcumulado() + monto);
        patientRepository.save(patient);
    }

    public void desactivar(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
        patient.setActivo(false);
        patientRepository.save(pati9);
    }
}
