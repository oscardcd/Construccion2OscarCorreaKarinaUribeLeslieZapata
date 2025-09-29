package clinickol.clinicmanagement.model;

/**
 * Enum para los tipos de documento de identidad
 */
public enum DocumentType {
    CEDULA("Cédula"),
    PASAPORTE("Pasaporte"),
    TARJETA_IDENTIDAD("Tarjeta de Identidad"),
    CEDULA_EXTRANJERIA("Cédula de Extranjería"),
    REGISTRO_CIVIL("Registro Civil");

    private final String descripcion;

    DocumentType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
