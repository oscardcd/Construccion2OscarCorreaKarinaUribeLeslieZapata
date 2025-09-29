package clinickol.clinicmanagement.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Doctor en el sistema
 * Extiende de User para heredar atributos comunes
 */
@Entity
@Table(name = "doctors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Doctor extends User {

    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    @Column(name = "numero_licencia", unique = true, nullable = false, length = 50)
    private String numeroLicencia;

    @Column(name = "anos_experiencia")
    private Integer anosExperiencia;

    @Column(name = "consultorio", length = 50)
    private String consultorio;

    @Column(name = "horario_atencion", length = 200)
    private String horarioAtencion;

    @Column(name = "tarifa_consulta")
    private Double tarifaConsulta;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas = new ArrayList<>();

    // Constructores
    public Doctor() {
        super();
    }

    public Doctor(String nombre, String apellido, String email, String telefono,
            String documento, TipoDocumento tipoDocumento, String especialidad,
            String numeroLicencia) {
        super(nombre, apellido, email, telefono, documento, tipoDocumento);
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
    }

    // Implementación del método abstracto
    @Override
    public String getTipoUsuario() {
        return "DOCTOR";
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Integer getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(Integer anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public Double getTarifaConsulta() {
        return tarifaConsulta;
    }

    public void setTarifaConsulta(Double tarifaConsulta) {
        this.tarifaConsulta = tarifaConsulta;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", numeroLicencia='" + numeroLicencia + '\'' +
                ", activo=" + getActivo() +
                '}';
    }
}
