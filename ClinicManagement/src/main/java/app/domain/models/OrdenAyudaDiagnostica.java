package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ordenes_ayudas_diagnosticas")
@IdClass(OrdenAyudaDiagnostica.OrdenAyudaDiagnosticaId.class)
public class OrdenAyudaDiagnostica {

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
    @Column(name = "nombre_ayuda_diagnostica", nullable = false)
    private String nombreAyudaDiagnostica;

    @NotNull
    @Min(1)
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "costo", nullable = false)
    private Double costo;

    @Column(name = "requiere_especialista", nullable = false)
    private boolean requiereEspecialista = false;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    public OrdenAyudaDiagnostica() {
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

    public String getNombreAyudaDiagnostica() {
        return nombreAyudaDiagnostica;
    }

    public void setNombreAyudaDiagnostica(String nombreAyudaDiagnostica) {
        this.nombreAyudaDiagnostica = nombreAyudaDiagnostica;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public static class OrdenAyudaDiagnosticaId implements Serializable {
        private String numeroOrden;
        private Integer numeroItem;

        public OrdenAyudaDiagnosticaId() {
        }

        public OrdenAyudaDiagnosticaId(String numeroOrden, Integer numeroItem) {
            this.numeroOrden = numeroOrden;
            this.numeroItem = numeroItem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            OrdenAyudaDiagnosticaId that = (OrdenAyudaDiagnosticaId) o;
            return Objects.equals(numeroOrden, that.numeroOrden) && Objects.equals(numeroItem, that.numeroItem);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numeroOrden, numeroItem);
        }
    }
}
