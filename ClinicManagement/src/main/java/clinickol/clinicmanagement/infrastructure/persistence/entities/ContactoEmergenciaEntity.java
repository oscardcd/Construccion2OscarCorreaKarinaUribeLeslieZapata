package clinickol.clinicmanagement.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contactos_emergencia")
public class ContactoEmergenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String relacionConPaciente;

    @Column(length = 10, nullable = false)
    private String telefonoEmergencia;

    public ContactoEmergenciaEntity() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRelacionConPaciente() {
        return relacionConPaciente;
    }

    public void setRelacionConPaciente(String relacionConPaciente) {
        this.relacionConPaciente = relacionConPaciente;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }
}

