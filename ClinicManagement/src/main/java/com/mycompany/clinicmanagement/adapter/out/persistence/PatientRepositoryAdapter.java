package com.mycompany.clinicmanagement.adapter.out.persistence;

import com.mycompany.clinicmanagement.application.port.PatientRepositoryPort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia para pacientes
 * Implementa PatientRepositoryPort usando almacenamiento en memoria
 * En producción se reemplazaría por JPA/Hibernate con base de datos real
 */
@Repository
public class PatientRepositoryAdapter implements PatientRepositoryPort {

    private final ConcurrentHashMap<Long, Patient> patients = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Patient> patientsByDocument = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Patient> patientsByEmail = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(idGenerator.getAndIncrement());
        }

        patients.put(patient.getId(), patient);
        patientsByDocument.put(patient.getDocumentNumber(), patient);

        if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
            patientsByEmail.put(patient.getEmail(), patient);
        }

        return patient;
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(patients.get(id));
    }

    @Override
    public Optional<Patient> findByDocumentNumber(String documentNumber) {
        return Optional.ofNullable(patientsByDocument.get(documentNumber));
    }

    @Override
    public List<Patient> findAll() {
        return List.copyOf(patients.values());
    }

    @Override
    public List<Patient> findByGender(Patient.Gender gender) {
        return patients.values().stream()
                .filter(patient -> patient.getGender() == gender)
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findWithActiveInsurance() {
        return patients.values().stream()
                .filter(this::hasActiveInsurance)
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findWithoutInsurance() {
        return patients.values().stream()
                .filter(patient -> !hasActiveInsurance(patient))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findByNameContaining(String name) {
        String searchName = name.toLowerCase();
        return patients.values().stream()
                .filter(patient -> patient.getFullName().toLowerCase().contains(searchName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findByAgeRange(int minAge, int maxAge) {
        return patients.values().stream()
                .filter(patient -> {
                    int age = calculateAge(patient);
                    return age >= minAge && age <= maxAge;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return patientsByDocument.containsKey(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return patientsByEmail.containsKey(email);
    }

    @Override
    public Patient update(Patient patient) {
        if (patient.getId() == null) {
            throw new IllegalArgumentException("El paciente debe tener un ID para ser actualizado");
        }

        Patient existingPatient = patients.get(patient.getId());
        if (existingPatient == null) {
            throw new IllegalArgumentException("Paciente no encontrado con ID: " + patient.getId());
        }

        // Actualizar índices si cambió la información
        if (!existingPatient.getDocumentNumber().equals(patient.getDocumentNumber())) {
            patientsByDocument.remove(existingPatient.getDocumentNumber());
            patientsByDocument.put(patient.getDocumentNumber(), patient);
        }

        if (!existingPatient.getEmail().equals(patient.getEmail())) {
            if (existingPatient.getEmail() != null && !existingPatient.getEmail().trim().isEmpty()) {
                patientsByEmail.remove(existingPatient.getEmail());
            }
            if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
                patientsByEmail.put(patient.getEmail(), patient);
            }
        }

        patients.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public void deleteById(Long id) {
        Patient patient = patients.remove(id);
        if (patient != null) {
            patientsByDocument.remove(patient.getDocumentNumber());
            if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
                patientsByEmail.remove(patient.getEmail());
            }
        }
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        Patient patient = patientsByDocument.remove(documentNumber);
        if (patient != null) {
            patients.remove(patient.getId());
            if (patient.getEmail() != null && !patient.getEmail().trim().isEmpty()) {
                patientsByEmail.remove(patient.getEmail());
            }
        }
    }

    @Override
    public long count() {
        return patients.size();
    }

    @Override
    public long countByGender(Patient.Gender gender) {
        return patients.values().stream()
                .filter(patient -> patient.getGender() == gender)
                .count();
    }

    @Override
    public long countWithActiveInsurance() {
        return patients.values().stream()
                .filter(this::hasActiveInsurance)
                .count();
    }

    /**
     * Verifica si un paciente tiene seguro activo
     */
    private boolean hasActiveInsurance(Patient patient) {
        return patient.isPolicyActive() &&
                patient.getPolicyExpirationDate() != null &&
                patient.getPolicyExpirationDate().isAfter(LocalDate.now());
    }

    /**
     * Calcula la edad de un paciente
     */
    private int calculateAge(Patient patient) {
        if (patient.getBirthDate() == null) {
            return 0;
        }
        return LocalDate.now().getYear() - patient.getBirthDate().getYear();
    }
}
