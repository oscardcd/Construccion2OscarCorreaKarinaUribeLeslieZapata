package clinickol.clinicmanagement.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String cedula;

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 20)
    private String genero;

    @Column(nullable = false)
    private String direccion;

    @Column(length = 10, nullable = false)
    private String telefono;

    @Column(nullable = true)
    private String correoElectronico;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contacto_emergencia_id")
    private ContactoEmergenciaEntity contactoEmergencia;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seguro_medico_id")
    private SeguroMedicoEntity seguroMedico;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(name = "copago_anual_acumulado")
    private Double copagoAnualAcumulado = 0.0;

    @Column(name = "ano_copago_actual")
    private Integer anoCopagoActual;

    public PacienteEntity() {
        this.anoCopagoActual = LocalDate.now().getYear();
    }

    // Getters and Setters
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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

    public ContactoEmergenciaEntity getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(ContactoEmergenciaEntity contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public SeguroMedicoEntity getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(SeguroMedicoEntity seguroMedico) {
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

