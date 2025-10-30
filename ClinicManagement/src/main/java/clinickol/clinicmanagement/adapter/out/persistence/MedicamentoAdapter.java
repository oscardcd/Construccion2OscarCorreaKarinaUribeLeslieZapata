package clinickol.clinicmanagement.adapter.out.persistence;

import clinickol.clinicmanagement.domain.model.MedicamentoDomain;
import clinickol.clinicmanagement.domain.ports.MedicamentoPort;
import clinickol.clinicmanagement.infrastructure.persistence.entities.MedicamentoEntity;
import clinickol.clinicmanagement.infrastructure.persistence.mapper.MedicamentoMapper;
import clinickol.clinicmanagement.infrastructure.persistence.repository.MedicamentoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoAdapter implements MedicamentoPort {

    @Autowired
    private MedicamentoJpaRepository medicamentoJpaRepository;

    @Override
    public MedicamentoDomain findById(Long id) throws Exception {
        Optional<MedicamentoEntity> entity = medicamentoJpaRepository.findById(id);
        return entity.map(MedicamentoMapper::toDomain).orElse(null);
    }

    @Override
    public List<MedicamentoDomain> findAll() throws Exception {
        return medicamentoJpaRepository.findAll()
                .stream()
                .map(MedicamentoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicamentoDomain> findActivos() throws Exception {
        return medicamentoJpaRepository.findByActivoTrue()
                .stream()
                .map(MedicamentoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(MedicamentoDomain medicamento) throws Exception {
        MedicamentoEntity entity = MedicamentoMapper.toEntity(medicamento);
        medicamentoJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        medicamentoJpaRepository.deleteById(id);
    }
}

