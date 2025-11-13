package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ordenes_medicamentos")
@IdClass(OrdenMedicamento.OrdenMedicamentoId.class)
public class OrdenMedicamento {

    @Id
    @Column(name = "numero_orden", length = 6)
    private String numeroOrden;

    @Id
    @Column(name = "numero_item")
    private Integer numeroItem;

    @ManyToOne
    @JoinColumn(name = "numero_orden", insertable = false, updatable = false)
    private Orden orden;

    @NotBlank
    @Column(name = "nombre_medicamento", nullable = false)
    private String nombreMedicamento;

    @NotBlank
    @Column(name = "dosis", nullable = false)
    private String dosis;

    @NotBlank
    @Column(name = "duracion_tratamiento", nullable = false)
    private String duracionTratamiento;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "costo", nullable = false)
    private Double costo;

    public OrdenMedicamento() {
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(String duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public static class OrdenMedicamentoId implements Serializable {
        private String numeroOrden;
        private Integer numeroItem;

        public OrdenMedicamentoId() {
        }

        public OrdenMedicamentoId(String numeroOrden, Integer numeroItem) {
            this.numeroOrden = numeroOrden;
            this.numeroItem = numeroItem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            OrdenMedicamentoId that = (OrdenMedicamentoId) o;
            return Objects.equals(numeroOrden, that.numeroOrden) && Objects.equals(numeroItem, that.numeroItem);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numeroOrden, numeroItem);
        }
    }
}
