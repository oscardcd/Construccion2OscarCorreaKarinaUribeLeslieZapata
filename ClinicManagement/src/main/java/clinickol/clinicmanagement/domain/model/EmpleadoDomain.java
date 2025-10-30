package clinickol.clinicmanagement.domain.model;

import clinickol.clinicmanagement.domain.model.enums.Role;

public class EmpleadoDomain extends Persona {
    private String correoElectronico;
    private Role rol;
    private String nombreUsuario;
    private String contrasena;
    private boolean activo;

    public EmpleadoDomain() {
        this.activo = true;
    }

    // Getters and Setters
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

