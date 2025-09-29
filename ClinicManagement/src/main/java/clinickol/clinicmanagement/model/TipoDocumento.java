package clinickol.clinicmanagement.model;

/**
 * Enum para los tipos de documento de identidad
 */
public enum TipoDocumento {
    CEDULA("Cédula"),
    PASAPORTE("Pasaporte"),
    TARJETA_IDENTIDAD("Tarjeta de Identidad"),
    CEDULA_EXTRANJERIA("Cédula de Extranjería"),
    REGISTRO_CIVIL("Registro Civil");

    private final String descripcion;

    TipoDocumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
