package clinickol.clinicmanagement.domain.model.auth;

public class TokenResponse {
    private String token;
    private String tipo;
    private String nombreUsuario;
    private String rol;

    public TokenResponse() {
        this.tipo = "Bearer";
    }

    public TokenResponse(String token, String nombreUsuario, String rol) {
        this.token = token;
        this.tipo = "Bearer";
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

