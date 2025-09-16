package app.domain.models;

import java.time.LocalDate;
import java.util.Objects;

public class Patient {

    private Long id;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String address;
    private String bloodType;
    private String emergencyContact;
    private String emergencyPhone;

    // Constructor por defecto
    public Patient() {
    }

    // Constructor con parámetros
    public Patient(Long id, String documentNumber, String firstName, String lastName,
            LocalDate birthDate, String phone, String email, String address,
            String bloodType, String emergencyContact, String emergencyPhone) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
        this.emergencyPhone = emergencyPhone;
    }

    // Métodos de negocio
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public boolean isAdult() {
        return getAge() >= 18;
    }

    public boolean hasValidContact() {
        return (phone != null && !phone.trim().isEmpty())
                || (email != null && !email.trim().isEmpty());
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(documentNumber, patient.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber);
    }

    @Override
    public String toString() {
        return "Patient{"
                + "id=" + id
                + ", documentNumber='" + documentNumber + '\''
                + ", fullName='" + getFullName() + '\''
                + ", age=" + getAge()
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
