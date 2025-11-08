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
    private boolean activo = true;

    @Column(name = "copago_anual_acumulado")
    private Double copagoAnualAcumulado = 0.0;

    @Column(name = "annual_copay_accumulated")
    private Integer annualCopayAccumulated;

    public Patient() {
        this.annualCopayAccumulated = LocalDate.now().getYear();
    }

    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate), LocalDate.now()).getYears();
    }

    public void resetCopayIfNewYear() {
        int anoActual = LocalDate.now().getYear();
        if (this.currentCopayYear != currentYear) {
            this.annualCopayAccumulated = 0.0;
            this.currentCopayYear = currentYear;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = email;
    }

    public ContactoEmergencia getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(ContactoEmergencia contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public HealthInsurance gethealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance (HealthInsurance healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Double getCopagoAnualAcumulado() {
        return copagoAnualAcumulado;
    }

    public void setCopagoAnualAcumulado(Double copagoAnualAcumulado) {
        this.copagoAnualAcumulado = copagoAnualAcumulado;
    }

    public Integer getAnoCopagoActual() {
        return anoCopagoActual;
    }

    public void setAnoCopagoActual(Integer anoCopagoActual) {
        this.anoCopagoActual = anoCopagoActual;
    }
}
