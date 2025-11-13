package clinickol.clinicmanagement.adapter.in.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequest {
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;
    
    @JsonProperty("contrasena")
    private String contrasena;

    public AuthRequest() {
    }

    public AuthRequest(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    // Getters and Setters
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
}

