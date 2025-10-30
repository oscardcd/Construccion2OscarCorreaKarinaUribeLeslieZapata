package clinickol.clinicmanagement.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SeguroMedicoDomain {
    private Long id;
    private String nombreCompania;
    private String numeroPoliza;
    private boolean estadoPoliza;
    private LocalDate vigenciaPoliza;

    public SeguroMedicoDomain() {
        this.estadoPoliza = true;
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

