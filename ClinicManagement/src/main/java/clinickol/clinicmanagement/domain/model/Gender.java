package clinickol.clinicmanagement.domain.model;

public enum Gender {
    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    OTRO("Otro"),
    NO_ESPECIFICADO("No especificado");

    private final String descripcion;

    Gender(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
