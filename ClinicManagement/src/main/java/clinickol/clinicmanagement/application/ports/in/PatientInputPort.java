package clinickol.clinicmanagement.application.ports.in;

import clinickol.clinicmanagement.domain.model.PatientDomain;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para operaciones de paciente
 * Define los casos de uso disponibles
 */
public interface PatientInputPort {

    PatientDomain crearPaciente(PatientDomain patient);

    List<PatientDomain> obtenerTodosLosPacientes();

    List<PatientDomain> obtenerPacientesActivos();

    Optional<PatientDomain> obtenerPacientePorId(Long id);

    Optional<PatientDomain> obtenerPacientePorEmail(String email);

    Optional<PatientDomain> obtenerPacientePorDocumento(String documento);

    List<PatientDomain> buscarPacientesPorNombre(String nombre);

    List<PatientDomain> buscarPacientesPorApellido(String apellido);

    List<PatientDomain> buscarPacientesPorGenero(String genero);

    List<PatientDomain> buscarPacientesPorCiudad(String ciudad);

    PatientDomain actualizarPaciente(Long id, PatientDomain patient);

    void desactivarPaciente(Long id);

    void activarPaciente(Long id);

    void eliminarPaciente(Long id);

    long contarPacientesActivos();

    long contarPacientesPorGenero(String genero);
}
