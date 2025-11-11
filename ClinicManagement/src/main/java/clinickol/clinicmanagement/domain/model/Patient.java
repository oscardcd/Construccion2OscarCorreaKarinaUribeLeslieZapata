package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLong;

    @NotBlank
    @Column(unique = true, nullable = false, length = 10)
    private String id;

    @NotBlank
    @Column(unique = true, nullable = false, length = 10)
    private String identificationNumber;


    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Past
    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @NotBlank
    @Column(nullable = false)
    private String adress;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    @Column(length = 10, nullable = false)
    private String phoneNumber;

    @Email
    @Column(nullable = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contacto_emergencia_id")
    private EmergencyContact emergencyContact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seguro_medico_id")
    private HealthInsurance healthInsurance;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "annual_copay_accumulated")
    private Double annualCopayAccumulated = 0.0;

    @Column(name = "current_copay_year")
    private Integer currentCopayYear;

    public Patient() {
    this.currentCopayYear = LocalDate.now().getYear();
}

    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void resetCopayIfNewYear() {
        int currentYear = LocalDate.now().getYear();
        if (this.currentCopayYear == null || !this.currentCopayYear.equals(currentYear)) {
            this.annualCopayAccumulated = 0.0;
            this.currentCopayYear = currentYear;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificaionNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumbera) {
        this.identificationNumber = identificationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getbirthDate() {
        return birthDate;
    }

    public void setbirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String telefono) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = email;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public HealthInsurance gethealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance (HealthInsurance healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getAnnualCopayAccumulated() {
        return annualCopayAccumulated;
    }

    public void setAnnualCopayAccumulated(Double annualCopayAccumulated) {
        this.annualCopayAccumulated = annualCopayAccumulated;
    }

    public Integer getCurrentCopayYear() {
        return currentCopayYear;
    }

    public void setCurrentCopayYear(Integer currentCopayYear) {
        this.currentCopayYear = currentCopayYear;
    }
}