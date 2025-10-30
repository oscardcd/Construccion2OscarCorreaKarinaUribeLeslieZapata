package clinickol.clinicmanagement.domain.ports;

import clinickol.clinicmanagement.domain.model.auth.AuthCredentials;
import clinickol.clinicmanagement.domain.model.auth.TokenResponse;

public interface AuthenticationPort {
    TokenResponse authenticate(AuthCredentials credentials) throws Exception;
    
    String generateToken(String nombreUsuario, String rol) throws Exception;
    
    boolean validateToken(String token) throws Exception;
    
    String getUsernameFromToken(String token) throws Exception;
}

