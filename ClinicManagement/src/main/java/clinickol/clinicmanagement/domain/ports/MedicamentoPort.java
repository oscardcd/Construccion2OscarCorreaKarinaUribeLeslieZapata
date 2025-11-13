package clinickol.clinicmanagement.domain.ports;

import clinickol.clinicmanagement.domain.model.MedicamentoDomain;

import java.util.List;

public interface MedicamentoPort {
    MedicamentoDomain findById(Long id) throws Exception;
    
    List<MedicamentoDomain> findAll() throws Exception;
    
    List<MedicamentoDomain> findActivos() throws Exception;
    
    void save(MedicamentoDomain medicamento) throws Exception;
    
    void delete(Long id) throws Exception;
}

