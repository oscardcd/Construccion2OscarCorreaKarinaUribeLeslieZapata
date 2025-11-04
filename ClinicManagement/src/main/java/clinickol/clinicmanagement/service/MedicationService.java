package clinickol.clinicmanagement.service;
import clinickol.clinicmanagement.domain.model.Medication;
import clinickol.clinicmanagement.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicationService {

    private final MedicationRepository medicationrepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationrepository= medicationRepository;
    }

    public Medication create(Medication medication) {
        return medicationrepository.save(medication);
    }

    @Transactional(readOnly = true)
    public List<Medication> getAll() {
        return medicationrepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Medication> getById(Long id) {
        return medicationrepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Medication> getActiveMedications() {
        return medicationrepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Medication> searchByName(String nombre) {
        return medicationrepository.findByNameContainingIgnoreCase(name);

    public Medication update(Long id, Medication updatedMedication) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with ID: " + id));
    
        medication.setName(updatedMedication.getName());
        medication.setDescription(updatedMedication.getDescription());
        medication.setCost(updatedMedication.getCost());
        medication.setStock(updatedMedication.getStock());

        return medicationRepository.save(medication);
    }

    public void delete(Long id) {
        medicationRepository.deleteById(id);
    }
}
}
