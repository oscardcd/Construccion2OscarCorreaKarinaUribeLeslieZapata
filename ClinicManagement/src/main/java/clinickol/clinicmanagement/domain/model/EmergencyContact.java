package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String relationshipWithPatient;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    @Column(length = 10, nullable = false)
    private String emergencyPhoneNumber;

    public EmergencyContact() {
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRelationshipWithPatient() {
        return relationshipWithPatient;
    }

    public void setRelationshipWithPatient(String relationshipWithPatient) {
        this.relationshipWithPatient = relationshipWithPatient;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    
}
