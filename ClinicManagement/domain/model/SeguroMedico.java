package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "seguros_medicos")
public class SeguroMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombreCompania;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String numeroPoliza;

    @Column(nullable = false)
    private boolean estadoPoliza = true;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate vigenciaPoliza;

    public SeguroMedico() {
    }

    public long getDiasVigencia() {
        if (vigenciaPoliza == null) {
            return 0;
        }
        LocalDate hoy = LocalDate.now();
        if (vigenciaPoliza.isBefore(hoy)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(hoy, vigenciaPoliza);
    }

    public boolean isVigente() {
        return estadoPoliza && vigenciaPoliza != null && vigenciaPoliza.isAfter(LocalDate.now());
    }

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
