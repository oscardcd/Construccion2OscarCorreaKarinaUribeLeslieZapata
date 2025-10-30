package clinickol.clinicmanagement.repository;

import clinickol.clinicmanagement.domain.model.Empleado;
import clinickol.clinicmanagement.domain.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByCedula(String cedula);

    Optional<Empleado> findByNombreUsuario(String nombreUsuario);

    Optional<Empleado> findByCorreoElectronico(String correoElectronico);

    List<Empleado> findByRol(Role rol);

    List<Empleado> findByActivoTrue();

    boolean existsByCedula(String cedula);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByCorreoElectronico(String correoElectronico);
}
