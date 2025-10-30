package clinickol.clinicmanagement.adapter.in.rest.response;

public class TokenResponseDto {
    private String token;
    private String tipo;
    private String nombreUsuario;
    private String rol;

    public TokenResponseDto() {
    }

    public TokenResponseDto(String token, String tipo, String nombreUsuario, String rol) {
        this.token = token;
        this.tipo = tipo;
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

