package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.TipoOrden;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @Column(name = "numero_orden", length = 6)
    @Pattern(regexp = "\\d{1,6}")
    private String numeroOrden;

    @NotBlank
    @Column(name = "cedula_paciente", nullable = false, length = 10)
    private String cedulaPaciente;

    @NotBlank
    @Column(name = "cedula_medico", nullable = false, length = 10)
    private String cedulaMedico;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_orden", nullable = false)
    private TipoOrden tipoOrden;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenMedicamento> medicamentos = new ArrayList<>();

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenProcedimiento> procedimientos = new ArrayList<>();

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenAyudaDiagnostica> ayudasDiagnosticas = new ArrayList<>();

    @Column(name = "total")
    private Double total = 0.0;

    public Orden() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public void calcularTotal() {
        double sum = 0.0;
        for (OrdenMedicamento om : medicamentos) {
            sum += om.getCosto();
        }
        for (OrdenProcedimiento op : procedimientos) {
            sum += op.getCosto();
        }
        for (OrdenAyudaDiagnostica oa : ayudasDiagnosticas) {
            sum += oa.getCosto();
        }
        this.total = sum;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getCedulaMedico() {
        return cedulaMedico;
    }

    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoOrden getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(TipoOrden tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public List<OrdenMedicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<OrdenMedicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<OrdenProcedimiento> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(List<OrdenProcedimiento> procedimientos) {
        this.procedimientos = procedimientos;
    }

    public List<OrdenAyudaDiagnostica> getAyudasDiagnosticas() {
        return ayudasDiagnosticas;
    }

    public void setAyudasDiagnosticas(List<OrdenAyudaDiagnostica> ayudasDiagnosticas) {
        this.ayudasDiagnosticas = ayudasDiagnosticas;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
