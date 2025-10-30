package clinickol.clinicmanagement.domain.model;

public class ContactoEmergenciaDomain {
    private Long id;
    private String nombre;
    private String apellido;
    private String relacionConPaciente;
    private String telefonoEmergencia;

    public ContactoEmergenciaDomain() {
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRelacionConPaciente() {
        return relacionConPaciente;
    }

    public void setRelacionConPaciente(String relacionConPaciente) {
        this.relacionConPaciente = relacionConPaciente;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }
}

