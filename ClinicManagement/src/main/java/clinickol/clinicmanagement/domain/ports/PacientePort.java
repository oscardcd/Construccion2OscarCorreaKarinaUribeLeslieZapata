package clinickol.clinicmanagement.domain.ports;

import clinickol.clinicmanagement.domain.model.PacienteDomain;

import java.util.List;

public interface PacientePort {
    PacienteDomain findByCedula(String cedula) throws Exception;
    
    PacienteDomain findById(Long id) throws Exception;
    
    List<PacienteDomain> findAll() throws Exception;
    
    List<PacienteDomain> findActivos() throws Exception;
    
    void save(PacienteDomain paciente) throws Exception;
    
    void delete(Long id) throws Exception;
    
    boolean existsByCedula(String cedula) throws Exception;
}

