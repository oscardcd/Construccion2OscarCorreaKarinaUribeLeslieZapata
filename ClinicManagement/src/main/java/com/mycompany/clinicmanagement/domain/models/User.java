package com.mycompany.clinicmanagement.domain.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad del dominio que representa un usuario del sistema
 * Solo puede ser creada/eliminada por Recursos Humanos
 */
public class User {
    private Long id;
    private String documentNumber; // Cédula - debe ser única
    private String fullName;
    private String email; // Debe ser válido con @ y dominio
    private String phone; // Entre 1 y 10 dígitos
    private LocalDate birthDate; // Formato DD/MM/YYYY, máximo 150 años
    private String address; // Máximo 30 caracteres
    private Role role; // Médico, Enfermera, Personal administrativo, Recursos Humanos
    private String username; // Único, máximo 15 caracteres, solo letras y números
    private String password; // Mayúscula, número, carácter especial, mínimo 8 caracteres
    private boolean isActive;

    public enum Role {
        RECURSOS_HUMANOS("Recursos Humanos"),
        PERSONAL_ADMINISTRATIVO("Personal Administrativo"),
        ENFERMERA("Enfermera"),
        MEDICO("Médico"),
        SOPORTE_INFORMACION("Soporte de Información");

        private final String displayName;

        Role(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Constructor por defecto
    public User() {
    }

    // Constructor con parámetros
    public User(Long id, String documentNumber, String fullName, String email,
            String phone, LocalDate birthDate, String address, Role role,
            String username, String password, boolean isActive) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
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

    public boolean canAccessPatientInfo() {
        return role == Role.PERSONAL_ADMINISTRATIVO ||
                role == Role.ENFERMERA ||
                role == Role.MEDICO;
    }

    public boolean canManageUsers() {
        return role == Role.RECURSOS_HUMANOS;
    }

    public boolean canManageInventory() {
        return role == Role.SOPORTE_INFORMACION;
    }

    public boolean canCreateMedicalRecords() {
        return role == Role.MEDICO;
    }

    public boolean canRegisterVitals() {
        return role == Role.ENFERMERA;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(documentNumber, user.documentNumber) ||
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
