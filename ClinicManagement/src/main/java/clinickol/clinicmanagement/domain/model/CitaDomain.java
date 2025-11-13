package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.EstadoCita;
import java.time.LocalDateTime;

public class CitaDomain {
    private Long id;
    private PacienteDomain paciente;
    private EmpleadoDomain medico;
    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private String motivo;
    private String notas;

    public CitaDomain() {
        this.estado = EstadoCita.PROGRAMADA;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteDomain getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDomain paciente) {
        this.paciente = paciente;
    }

    public EmpleadoDomain getMedico() {
        return medico;
    }

    public void setMedico(EmpleadoDomain medico) {
        this.medico = medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}

