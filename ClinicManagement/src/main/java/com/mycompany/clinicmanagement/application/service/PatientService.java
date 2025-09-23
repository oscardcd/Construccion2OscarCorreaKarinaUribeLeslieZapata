package com.mycompany.clinicmanagement.application.service;

import com.mycompany.clinicmanagement.application.port.PatientRepositoryPort;
import com.mycompany.clinicmanagement.application.port.PatientServicePort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import com.mycompany.clinicmanagement.domain.service.PatientDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del servicio de pacientes
 * Orquesta las operaciones de negocio y coordina entre dominio y persistencia
 */
@Service
@Transactional
public class PatientService implements PatientServicePort {

    private final PatientRepositoryPort patientRepository;
    private final PatientDomainService patientDomainService;

    public PatientService(PatientRepositoryPort patientRepository,
            PatientDomainService patientDomainService) {
        this.patientRepository = patientRepository;
        this.patientDomainService = patientDomainService;
    }

    @Override
    public Patient registerPatient(Patient patient, User registeredBy) {
        // Validar permisos
        if (!patientDomainService.canManagePatients(registeredBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede registrar pacientes");
        }

        // Validar datos del paciente
        patientDomainService.validatePatientData(patient);

        // Verificar que no exista un paciente con la misma cédula
        if (patientRepository.findByDocumentNumber(patient.getDocumentNumber()).isPresent()) {
            throw new IllegalStateException("Ya existe un paciente con la cédula: " + patient.getDocumentNumber());
        }

        // Guardar paciente
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient, User updatedBy) {
        // Validar permisos
        if (!patientDomainService.canManagePatients(updatedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede actualizar pacientes");
        }

        // Validar datos del paciente
        patientDomainService.validatePatientData(patient);

        // Verificar que el paciente existe
        Optional<Patient> existingPatient = patientRepository.findByDocumentNumber(patient.getDocumentNumber());
        if (existingPatient.isEmpty()) {
            throw new IllegalArgumentException("Paciente no encontrado con cédula: " + patient.getDocumentNumber());
        }

        // Actualizar datos
        Patient patientToUpdate = existingPatient.get();
        patientToUpdate.setFullName(patient.getFullName());
        patientToUpdate.setBirthDate(patient.getBirthDate());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());
        patientToUpdate.setEmail(patient.getEmail());
        patientToUpdate.setEmergencyContactName(patient.getEmergencyContactName());
        patientToUpdate.setEmergencyContactRelation(patient.getEmergencyContactRelation());
        patientToUpdate.setEmergencyContactPhone(patient.getEmergencyContactPhone());

        return patientRepository.save(patientToUpdate);
    }

    @Override
    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Optional<Patient> findPatientByDocumentNumber(String documentNumber) {
        return patientRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContaining(name);
    }

    @Override
    public List<Patient> getPatientsByGender(Patient.Gender gender) {
        return patientRepository.findByGender(gender);
    }

    @Override
    public List<Patient> getPatientsWithActiveInsurance() {
        return patientRepository.findWithActiveInsurance();
    }

    @Override
    public List<Patient> getPatientsWithoutInsurance() {
        return patientRepository.findWithoutInsurance();
    }

    @Override
    public List<Patient> getPatientsByAgeRange(int minAge, int maxAge) {
        return patientRepository.findByAgeRange(minAge, maxAge);
    }

    @Override
    public Patient updateInsuranceInfo(String patientDocumentNumber, String insuranceCompany,
            String policyNumber, boolean isActive, LocalDate expirationDate, User updatedBy) {

        // Validar permisos
        if (!patientDomainService.canManagePatients(updatedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede actualizar seguros");
        }

        // Buscar paciente
        Optional<Patient> patientOpt = patientRepository.findByDocumentNumber(patientDocumentNumber);
        if (patientOpt.isEmpty()) {
            throw new IllegalArgumentException("Paciente no encontrado con cédula: " + patientDocumentNumber);
        }

        Patient patient = patientOpt.get();
        patient.setInsuranceCompany(insuranceCompany);
        patient.setPolicyNumber(policyNumber);
        patient.setPolicyActive(isActive);
        patient.setPolicyExpirationDate(expirationDate);

        return patientRepository.save(patient);
    }

    @Override
    public Patient updateEmergencyContact(String patientDocumentNumber, String contactName,
            String relation, String phone, User updatedBy) {

        // Validar permisos
        if (!patientDomainService.canManagePatients(updatedBy)) {
            throw new IllegalArgumentException("Solo Personal Administrativo puede actualizar contactos de emergencia");
        }

        // Buscar paciente
        Optional<Patient> patientOpt = patientRepository.findByDocumentNumber(patientDocumentNumber);
        if (patientOpt.isEmpty()) {
            throw new IllegalArgumentException("Paciente no encontrado con cédula: " + patientDocumentNumber);
        }

        Patient patient = patientOpt.get();
        patient.setEmergencyContactName(contactName);
        patient.setEmergencyContactRelation(relation);
        patient.setEmergencyContactPhone(phone);

        return patientRepository.save(patient);
    }

    @Override
    public void validatePatientData(Patient patient) {
        patientDomainService.validatePatientData(patient);
    }

    @Override
    public boolean hasActiveInsurance(String patientDocumentNumber) {
        Optional<Patient> patient = patientRepository.findByDocumentNumber(patientDocumentNumber);
        return patient.map(Patient::hasActiveInsurance).orElse(false);
    }

    @Override
    public Map<String, Object> getPatientStatistics() {
        List<Patient> allPatients = patientRepository.findAll();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPatients", allPatients.size());
        stats.put("adultPatients", allPatients.stream().mapToInt(p -> p.isAdult() ? 1 : 0).sum());
        stats.put("minorPatients", allPatients.size() - allPatients.stream().mapToInt(p -> p.isAdult() ? 1 : 0).sum());
        stats.put("patientsWithInsurance", patientRepository.findWithActiveInsurance().size());
        stats.put("patientsWithoutInsurance", patientRepository.findWithoutInsurance().size());

        return stats;
    }

    @Override
    public Map<String, Object> getInsuranceStatistics() {
        List<Patient> allPatients = patientRepository.findAll();
        List<Patient> withInsurance = patientRepository.findWithActiveInsurance();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPatients", allPatients.size());
        stats.put("withActiveInsurance", withInsurance.size());
        stats.put("withoutInsurance", allPatients.size() - withInsurance.size());
        stats.put("insuranceCoveragePercentage",
                allPatients.isEmpty() ? 0.0 : (double) withInsurance.size() / allPatients.size() * 100);

        return stats;
    }

    @Override
    public boolean canAccessPatientInfo(User user) {
        return patientDomainService.canAccessPatientInfo(user);
    }

    @Override
    public List<Map<String, Object>> getPatientChangeHistory(String patientDocumentNumber) {
        // Implementación simple - en un sistema real se guardaría el historial
        return List.of();
    }

    @Override
    public Map<String, Object> exportPatientData(String patientDocumentNumber) {
        Optional<Patient> patient = patientRepository.findByDocumentNumber(patientDocumentNumber);
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }

        Map<String, Object> exportData = new HashMap<>();
        Patient p = patient.get();
        exportData.put("id", p.getId());
        exportData.put("documentNumber", p.getDocumentNumber());
        exportData.put("fullName", p.getFullName());
        exportData.put("age", p.getAge());
        exportData.put("gender", p.getGender().getDisplayName());
        exportData.put("phone", p.getPhone());
        exportData.put("email", p.getEmail());
        exportData.put("hasActiveInsurance", p.hasActiveInsurance());
        exportData.put("exportDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return exportData;
    }
}
