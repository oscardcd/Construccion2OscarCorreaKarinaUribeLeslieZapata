package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ordenes_procedimientos")
@IdClass(OrdenProcedimiento.OrdenProcedimientoId.class)
public class OrdenProcedimiento {

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
    @Column(name = "nombre_procedimiento", nullable = false)
    private String nombreProcedimiento;

    @NotNull
    @Min(1)
    @Column(name = "numero_veces", nullable = false)
    private Integer numeroVeces;

    @NotBlank
    @Column(name = "frecuencia", nullable = false)
    private String frecuencia;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "costo", nullable = false)
    private Double costo;

    @Column(name = "requiere_especialista", nullable = false)
    private boolean requiereEspecialista = false;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    public OrdenProcedimiento() {
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

    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }

    public void setNombreProcedimiento(String nombreProcedimiento) {
        this.nombreProcedimiento = nombreProcedimiento;
    }

    public Integer getNumeroVeces() {
        return numeroVeces;
    }

    public void setNumeroVeces(Integer numeroVeces) {
        this.numeroVeces = numeroVeces;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public static class OrdenProcedimientoId implements Serializable {
        private String numeroOrden;
        private Integer numeroItem;

        public OrdenProcedimientoId() {
        }

        public OrdenProcedimientoId(String numeroOrden, Integer numeroItem) {
            this.numeroOrden = numeroOrden;
            this.numeroItem = numeroItem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            OrdenProcedimientoId that = (OrdenProcedimientoId) o;
            return Objects.equals(numeroOrden, that.numeroOrden) && Objects.equals(numeroItem, that.numeroItem);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numeroOrden, numeroItem);
        }
    }
}
