package clinickol.clinicmanagement.adapter.out.persistence;

import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.enums.Role;
import clinickol.clinicmanagement.domain.ports.EmpleadoPort;
import clinickol.clinicmanagement.infrastructure.persistence.entities.EmpleadoEntity;
import clinickol.clinicmanagement.infrastructure.persistence.mapper.EmpleadoMapper;
import clinickol.clinicmanagement.infrastructure.persistence.repository.EmpleadoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoAdapter implements EmpleadoPort {

    @Autowired
    private EmpleadoJpaRepository empleadoJpaRepository;

    @Override
    public EmpleadoDomain findByCedula(String cedula) throws Exception {
        Optional<EmpleadoEntity> entity = empleadoJpaRepository.findByCedula(cedula);
        return entity.map(EmpleadoMapper::toDomain).orElse(null);
    }

    @Override
    public EmpleadoDomain findByNombreUsuario(String nombreUsuario) throws Exception {
        Optional<EmpleadoEntity> entity = empleadoJpaRepository.findByNombreUsuario(nombreUsuario);
        return entity.map(EmpleadoMapper::toDomain).orElse(null);
    }

    @Override
    public EmpleadoDomain findByCorreoElectronico(String correoElectronico) throws Exception {
        Optional<EmpleadoEntity> entity = empleadoJpaRepository.findByCorreoElectronico(correoElectronico);
        return entity.map(EmpleadoMapper::toDomain).orElse(null);
    }

    @Override
    public EmpleadoDomain findById(Long id) throws Exception {
        Optional<EmpleadoEntity> entity = empleadoJpaRepository.findById(id);
        return entity.map(EmpleadoMapper::toDomain).orElse(null);
    }

    @Override
    public List<EmpleadoDomain> findAll() throws Exception {
        return empleadoJpaRepository.findAll()
                .stream()
                .map(EmpleadoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoDomain> findByRol(Role rol) throws Exception {
        return empleadoJpaRepository.findByRol(rol.name())
                .stream()
                .map(EmpleadoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoDomain> findActivos() throws Exception {
        return empleadoJpaRepository.findByActivoTrue()
                .stream()
                .map(EmpleadoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(EmpleadoDomain empleado) throws Exception {
        EmpleadoEntity entity = EmpleadoMapper.toEntity(empleado);
        empleadoJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        empleadoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByCedula(String cedula) throws Exception {
        return empleadoJpaRepository.existsByCedula(cedula);
    }

    @Override
    public boolean existsByNombreUsuario(String nombreUsuario) throws Exception {
        return empleadoJpaRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existsByCorreoElectronico(String correoElectronico) throws Exception {
        return empleadoJpaRepository.existsByCorreoElectronico(correoElectronico);
    }
}

