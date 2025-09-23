package com.mycompany.clinicmanagement.domain.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad del dominio que representa un paciente en la clínica
 * Gestionada por Personal Administrativo
 */
public class Patient {
    private Long id;
    private String documentNumber; // Cédula - número único de identificación
    private String fullName; // Nombre completo
    private LocalDate birthDate; // Fecha de nacimiento, máximo 150 años
    private Gender gender; // Masculino, femenino, otro
    private String address; // Dirección actual
    private String phone; // 10 dígitos
    private String email; // Correo electrónico (opcional)

    // Información de contacto de emergencia
    private String emergencyContactName;
    private String emergencyContactRelation;
    private String emergencyContactPhone; // 10 dígitos

    // Información de seguro médico
    private String insuranceCompany;
    private String policyNumber;
    private boolean isPolicyActive;
    private LocalDate policyExpirationDate; // DD/MM/YYYY

    public enum Gender {
        MASCULINO("Masculino"),
        FEMENINO("Femenino"),
        OTRO("Otro");

        private final String displayName;

        Gender(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Constructor por defecto
    public Patient() {
    }

    // Constructor con parámetros
    public Patient(Long id, String documentNumber, String fullName, LocalDate birthDate,
            Gender gender, String address, String phone, String email,
            String emergencyContactName, String emergencyContactRelation,
            String emergencyContactPhone, String insuranceCompany,
            String policyNumber, boolean isPolicyActive, LocalDate policyExpirationDate) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactRelation = emergencyContactRelation;
        this.emergencyContactPhone = emergencyContactPhone;
        this.insuranceCompany = insuranceCompany;
        this.policyNumber = policyNumber;
        this.isPolicyActive = isPolicyActive;
        this.policyExpirationDate = policyExpirationDate;
    }

    // Métodos de negocio
    public int getAge() {
        if (birthDate == null)
            return 0;
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public boolean isAdult() {
        return getAge() >= 18;
    }

    public boolean hasValidContact() {
        return (phone != null && !phone.trim().isEmpty()) ||
                (email != null && !email.trim().isEmpty());
    }

    public boolean hasValidEmergencyContact() {
        return emergencyContactName != null && !emergencyContactName.trim().isEmpty() &&
                emergencyContactPhone != null && !emergencyContactPhone.trim().isEmpty();
    }

    public boolean hasActiveInsurance() {
        return isPolicyActive && policyExpirationDate != null &&
                policyExpirationDate.isAfter(LocalDate.now());
    }

    public boolean hasValidInsurance() {
        return insuranceCompany != null && !insuranceCompany.trim().isEmpty() &&
                policyNumber != null && !policyNumber.trim().isEmpty();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }

    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public boolean isPolicyActive() {
        return isPolicyActive;
    }

    public void setPolicyActive(boolean policyActive) {
        isPolicyActive = policyActive;
    }

    public LocalDate getPolicyExpirationDate() {
        return policyExpirationDate;
    }

    public void setPolicyExpirationDate(LocalDate policyExpirationDate) {
        this.policyExpirationDate = policyExpirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Patient patient = (Patient) o;
        return Objects.equals(documentNumber, patient.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + getAge() +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", hasActiveInsurance=" + hasActiveInsurance() +
                '}';
    }
}
