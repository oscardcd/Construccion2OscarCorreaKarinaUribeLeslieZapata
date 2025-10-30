package clinickol.clinicmanagement.adapter.out.persistence;

import clinickol.clinicmanagement.domain.model.PacienteDomain;
import clinickol.clinicmanagement.domain.ports.PacientePort;
import clinickol.clinicmanagement.infrastructure.persistence.entities.PacienteEntity;
import clinickol.clinicmanagement.infrastructure.persistence.mapper.PacienteMapper;
import clinickol.clinicmanagement.infrastructure.persistence.repository.PacienteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteAdapter implements PacientePort {

    @Autowired
    private PacienteJpaRepository pacienteJpaRepository;

    @Override
    public PacienteDomain findByCedula(String cedula) throws Exception {
        Optional<PacienteEntity> entity = pacienteJpaRepository.findByCedula(cedula);
        return entity.map(PacienteMapper::toDomain).orElse(null);
    }

    @Override
    public PacienteDomain findById(Long id) throws Exception {
        Optional<PacienteEntity> entity = pacienteJpaRepository.findById(id);
        return entity.map(PacienteMapper::toDomain).orElse(null);
    }

    @Override
    public List<PacienteDomain> findAll() throws Exception {
        return pacienteJpaRepository.findAll()
                .stream()
                .map(PacienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PacienteDomain> findActivos() throws Exception {
        return pacienteJpaRepository.findByActivoTrue()
                .stream()
                .map(PacienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PacienteDomain paciente) throws Exception {
        PacienteEntity entity = PacienteMapper.toEntity(paciente);
        pacienteJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        pacienteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByCedula(String cedula) throws Exception {
        return pacienteJpaRepository.existsByCedula(cedula);
    }
}

