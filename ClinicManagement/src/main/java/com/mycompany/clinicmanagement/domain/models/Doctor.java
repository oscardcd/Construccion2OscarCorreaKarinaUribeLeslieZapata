package com.mycompany.clinicmanagement.domain.models;

import java.util.Objects;

/**
 * Entidad del dominio que representa un médico en la clínica
 */
public class Doctor {
    private Long id;
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;
    private String address;
    private boolean isActive;
    private String schedule;

    // Constructor por defecto
    public Doctor() {}

    // Constructor con parámetros
    public Doctor(Long id, String licenseNumber, String firstName, String lastName, 
                  String specialty, String phone, String email, String address, 
                  boolean isActive, String schedule) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
        this.schedule = schedule;
    }

    // Métodos de negocio
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullTitle() {
        return "Dr. " + getFullName() + " - " + specialty;
    }

    public boolean isAvailable() {
        return isActive;
    }

    public boolean hasValidContact() {
        return (phone != null && !phone.trim().isEmpty()) || 
               (email != null && !email.trim().isEmpty());
    }

    public boolean hasValidLicense() {
        return licenseNumber != null && !licenseNumber.trim().isEmpty();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(licenseNumber, doctor.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", specialty='" + specialty + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
