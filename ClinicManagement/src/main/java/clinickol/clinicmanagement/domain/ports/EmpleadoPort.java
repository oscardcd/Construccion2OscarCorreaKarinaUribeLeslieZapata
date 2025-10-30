package clinickol.clinicmanagement.domain.ports;

import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.enums.Role;

import java.util.List;

public interface EmpleadoPort {
    EmpleadoDomain findByCedula(String cedula) throws Exception;
    
    EmpleadoDomain findByNombreUsuario(String nombreUsuario) throws Exception;
    
    EmpleadoDomain findByCorreoElectronico(String correoElectronico) throws Exception;
    
    EmpleadoDomain findById(Long id) throws Exception;
    
    List<EmpleadoDomain> findAll() throws Exception;
    
    List<EmpleadoDomain> findByRol(Role rol) throws Exception;
    
    List<EmpleadoDomain> findActivos() throws Exception;
    
    void save(EmpleadoDomain empleado) throws Exception;
    
    void delete(Long id) throws Exception;
    
    boolean existsByCedula(String cedula) throws Exception;
    
    boolean existsByNombreUsuario(String nombreUsuario) throws Exception;
    
    boolean existsByCorreoElectronico(String correoElectronico) throws Exception;
}

