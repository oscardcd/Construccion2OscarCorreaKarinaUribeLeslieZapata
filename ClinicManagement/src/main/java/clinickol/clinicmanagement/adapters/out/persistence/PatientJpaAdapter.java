package clinickol.clinicmanagement.adapters.out.persistence;

import clinickol.clinicmanagement.adapters.out.persistence.mapper.PatientMapper;
import clinickol.clinicmanagement.application.ports.out.PatientOutputPort;
import clinickol.clinicmanagement.domain.model.PatientDomain;
import clinickol.clinicmanagement.repository.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PatientJpaAdapter implements PatientOutputPort {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientJpaAdapter(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDomain guardar(PatientDomain patient) {
        var entity = patientMapper.toEntity(patient);
        var saved = patientRepository.save(entity);
        return patientMapper.toDomain(saved);
    }

    @Override
    public Optional<PatientDomain> buscarPorId(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDomain);
    }

    @Override
    public Optional<PatientDomain> buscarPorEmail(String email) {
        return patientRepository.findByEmail(email)
                .map(patientMapper::toDomain);
    }

    @Override
    public Optional<PatientDomain> buscarPorDocumento(String documento) {
        return patientRepository.findByDocumento(documento)
                .map(patientMapper::toDomain);
    }

    @Override
    public List<PatientDomain> buscarTodos() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDomain> buscarActivos() {
        return patientRepository.findByActivoTrue().stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDomain> buscarPorNombre(String nombre) {
        return patientRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDomain> buscarPorApellido(String apellido) {
        return patientRepository.findByApellidoContainingIgnoreCase(apellido).stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDomain> buscarPorGenero(String genero) {
        return patientRepository.findByGenero(clinickol.clinicmanagement.model.Gender.valueOf(genero)).stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDomain> buscarPorCiudad(String ciudad) {
        return patientRepository.findByCiudad(ciudad).stream()
                .map(patientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public boolean existePorEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    @Override
    public boolean existePorDocumento(String documento) {
        return patientRepository.existsByDocumento(documento);
    }

    @Override
    public boolean existePorId(Long id) {
        return patientRepository.existsById(id);
    }

    @Override
    public long contarActivos() {
        return patientRepository.countByActivoTrue();
    }

    @Override
    public long contarPorGenero(String genero) {
        return patientRepository.countByGenero(clinickol.clinicmanagement.model.Gender.valueOf(genero));
    }
}
