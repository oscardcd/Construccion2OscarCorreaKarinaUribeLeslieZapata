package clinickol.clinicmanagement.service;

import clinickol.clinicmanagement.domain.model.Medicamento;
import clinickol.clinicmanagement.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Medicamento crear(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Transactional(readOnly = true)
    public List<Medicamento> obtenerTodos() {
        return medicamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Medicamento> obtenerPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Medicamento> obtenerActivos() {
        return medicamentoRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Medicamento actualizar(Long id, Medicamento medicamentoActualizado) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con ID: " + id));

        medicamento.setNombre(medicamentoActualizado.getNombre());
        medicamento.setDescripcion(medicamentoActualizado.getDescripcion());
        medicamento.setCosto(medicamentoActualizado.getCosto());
        medicamento.setStock(medicamentoActualizado.getStock());

        return medicamentoRepository.save(medicamento);
    }

    public void eliminar(Long id) {
        medicamentoRepository.deleteById(id);
    }
}
