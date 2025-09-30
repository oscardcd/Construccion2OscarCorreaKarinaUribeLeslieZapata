package clinickol.clinicmanagement.application.ports.out;

import clinickol.clinicmanagement.domain.model.PatientDomain;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para persistencia de paciente
 * Define las operaciones que el repositorio debe implementar
 */
public interface PatientOutputPort {

    PatientDomain guardar(PatientDomain patient);

    Optional<PatientDomain> buscarPorId(Long id);

    Optional<PatientDomain> buscarPorEmail(String email);

    Optional<PatientDomain> buscarPorDocumento(String documento);

    List<PatientDomain> buscarTodos();

    List<PatientDomain> buscarActivos();

    List<PatientDomain> buscarPorNombre(String nombre);

    List<PatientDomain> buscarPorApellido(String apellido);

    List<PatientDomain> buscarPorGenero(String genero);

    List<PatientDomain> buscarPorCiudad(String ciudad);

    void eliminar(Long id);

    boolean existePorEmail(String email);

    boolean existePorDocumento(String documento);

    boolean existePorId(Long id);

    long contarActivos();

    long contarPorGenero(String genero);
}

