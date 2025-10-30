package clinickol.clinicmanagement.infrastructure.persistence.repository;

import clinickol.clinicmanagement.infrastructure.persistence.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoJpaRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByCedula(String cedula);

    Optional<EmpleadoEntity> findByNombreUsuario(String nombreUsuario);

    Optional<EmpleadoEntity> findByCorreoElectronico(String correoElectronico);

    List<EmpleadoEntity> findByRol(String rol);

    List<EmpleadoEntity> findByActivoTrue();

    boolean existsByCedula(String cedula);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByCorreoElectronico(String correoElectronico);
}

