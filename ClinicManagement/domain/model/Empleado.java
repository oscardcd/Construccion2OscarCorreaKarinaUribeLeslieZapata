package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombreCompleto;

    @NotBlank
    @Column(unique = true, nullable = false, length = 10)
    private String cedula;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String correoElectronico;

    @NotBlank
    @Pattern(regexp = "\\d{1,10}")
    @Column(length = 10)
    private String telefono;

    @Past
    @NotNull
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank
    @Size(max = 30)
    @Column(length = 30)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Role rol;

    @NotBlank
    @Size(max = 15)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Column(unique = true, nullable = false, length = 15)
    private String nombreUsuario;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private boolean activo = true;

    public Empleado() {
    }

    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
