package app.domain.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class DiagnosticOrder {

    @Id
    @Column(name = "id_orden", length = 6)
    @Pattern(regexp = "\\d{1,6}")
    private String idOrden;

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
    private OrderType OrderType;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationOrder> medicamentos = new ArrayList<>();

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedureOrder> procedimientos = new ArrayList<>();

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenAyudaDiagnostica> ayudasDiagnosticas = new ArrayList<>();

    @Column(name = "total")
    private Double total = 0.0;

    public DiagnosticOrder() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public void calcularTotal() {
        double sum = 0.0;
        for (MedicationOrder om : medicamentos) {
            sum += om.getCosto();
        }
        for (ProcedureOrder op : procedimientos) {
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

    public OrderType getTipoOrdenOrderType() {
        return OrderType;
    }

    public void setOrderType(OrderType OrderType) {
        this.OrderType = OrderType;
    }

    public List<MedicationOrder> getMedicationOrders() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicationOrder> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<ProcedureOrder> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(List<ProcedureOrder> procedimientos) {
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
