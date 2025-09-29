package clinickol.clinicmanagement.model;

/**
 * Enum para los géneros
 */
public enum Genero {
    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    OTRO("Otro"),
    NO_ESPECIFICADO("No especificado");

    private final String descripcion;

    Genero(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
