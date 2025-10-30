package clinickol.clinicmanagement.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "seguros_medicos")
public class SeguroMedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCompania;

    @Column(nullable = false, unique = true)
    private String numeroPoliza;

    @Column(nullable = false)
    private boolean estadoPoliza = true;

    @Column(nullable = false)
    private LocalDate vigenciaPoliza;

    public SeguroMedicoEntity() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public boolean isEstadoPoliza() {
        return estadoPoliza;
    }

    public void setEstadoPoliza(boolean estadoPoliza) {
        this.estadoPoliza = estadoPoliza;
    }

    public LocalDate getVigenciaPoliza() {
        return vigenciaPoliza;
    }

    public void setVigenciaPoliza(LocalDate vigenciaPoliza) {
        this.vigenciaPoliza = vigenciaPoliza;
    }
}

