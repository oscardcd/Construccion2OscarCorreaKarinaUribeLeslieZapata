package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "procedimientos")
public class Procedimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @NotNull
    @DecimalMin("0.0")
    @Column(nullable = false)
    private Double costo;

    @Column(nullable = false)
    private boolean requiereEspecialista = false;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidadRequerida;

    @Column(nullable = false)
    private boolean activo = true;

    public Procedimiento() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public boolean isRequiereEspecialista() {
        return requiereEspecialista;
    }

    public void setRequiereEspecialista(boolean requiereEspecialista) {
        this.requiereEspecialista = requiereEspecialista;
    }

    public Especialidad getEspecialidadRequerida() {
        return especialidadRequerida;
    }

    public void setEspecialidadRequerida(Especialidad especialidadRequerida) {
        this.especialidadRequerida = especialidadRequerida;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
