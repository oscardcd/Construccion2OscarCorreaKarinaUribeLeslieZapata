package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.Gender;
import java.time.LocalDate;

public class PacienteDomain extends Persona {
    private Gender genero;
    private String correoElectronico;
    private ContactoEmergenciaDomain contactoEmergencia;
    private SeguroMedicoDomain seguroMedico;
    private boolean activo;
    private Double copagoAnualAcumulado;
    private Integer anoCopagoActual;

    public PacienteDomain() {
        this.activo = true;
        this.copagoAnualAcumulado = 0.0;
        this.anoCopagoActual = LocalDate.now().getYear();
    }

    public void reiniciarCopagoSiEsNuevoAno() {
        int anoActual = LocalDate.now().getYear();
        if (this.anoCopagoActual != anoActual) {
            this.copagoAnualAcumulado = 0.0;
            this.anoCopagoActual = anoActual;
        }
    }

    // Getters and Setters
    public Gender getGenero() {
        return genero;
    }

    public void setGenero(Gender genero) {
        this.genero = genero;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public ContactoEmergenciaDomain getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(ContactoEmergenciaDomain contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public SeguroMedicoDomain getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(SeguroMedicoDomain seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Double getCopagoAnualAcumulado() {
        return copagoAnualAcumulado;
    }

    public void setCopagoAnualAcumulado(Double copagoAnualAcumulado) {
        this.copagoAnualAcumulado = copagoAnualAcumulado;
    }

    public Integer getAnoCopagoActual() {
        return anoCopagoActual;
    }

    public void setAnoCopagoActual(Integer anoCopagoActual) {
        this.anoCopagoActual = anoCopagoActual;
    }
}

