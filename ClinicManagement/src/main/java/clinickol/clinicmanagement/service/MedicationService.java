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

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Medication create(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Transactional(readOnly = true)
    public List<Medication> getAll() {
        return medicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Medication> getById(Long id) {
        return medicationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Medication> getActive() {
        return medicationRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Medication> searchByName(String name) {
        return medicationRepository.findByDescriptionContainingIgnoreCase(name);
    }

    public Medication update(Long id, Medication updatedMedication) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with ID: " + id));

        medication.setName(updatedMedication.getName());
        medication.setDescription(updatedMedication.getDescription());
        medication.setCost(updatedMedication.getCost());
        medication.setStock(updatedMedication.getStock());
        medication.setActive(updatedMedication.isActive());

        return medicationRepository.save(medication);
    }

    public void delete(Long id) {
        medicationRepository.deleteById(id);
    }
}
