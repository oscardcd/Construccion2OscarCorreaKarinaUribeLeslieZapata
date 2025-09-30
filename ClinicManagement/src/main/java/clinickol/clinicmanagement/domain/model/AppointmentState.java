package clinickol.clinicmanagement.domain.model;

public enum AppointmentState {
    PROGRAMADA("Programada"),
    CONFIRMADA("Confirmada"),
    EN_PROGRESO("En Progreso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    NO_ASISTIO("No Asistió");

    private final String descripcion;

    AppointmentState(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
