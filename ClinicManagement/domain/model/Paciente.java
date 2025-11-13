package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false, length = 10)
    private String cedula;

    @NotBlank
    @Column(nullable = false)
    private String nombreCompleto;

    @Past
    @NotNull
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Gender genero;

    @NotBlank
    @Column(nullable = false)
    private String direccion;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    @Column(length = 10, nullable = false)
    private String telefono;

    @Email
    @Column(nullable = true)
    private String correoElectronico;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contacto_emergencia_id")
    private ContactoEmergencia contactoEmergencia;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seguro_medico_id")
    private SeguroMedico seguroMedico;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(name = "copago_anual_acumulado")
    private Double copagoAnualAcumulado = 0.0;

    @Column(name = "ano_copago_actual")
    private Integer anoCopagoActual;

    public Paciente() {
        this.anoCopagoActual = LocalDate.now().getYear();
    }

    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public void reiniciarCopagoSiEsNuevoAno() {
        int anoActual = LocalDate.now().getYear();
        if (this.anoCopagoActual != anoActual) {
            this.copagoAnualAcumulado = 0.0;
            this.anoCopagoActual = anoActual;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public ContactoEmergencia getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(ContactoEmergencia contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public SeguroMedico getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(SeguroMedico seguroMedico) {
        this.seguroMedico = seguroMedico;
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
