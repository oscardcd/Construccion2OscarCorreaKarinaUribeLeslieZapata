package clinickol.clinicmanagement.adapter.in.rest.mapper;

import clinickol.clinicmanagement.adapter.in.rest.request.AuthRequest;
import clinickol.clinicmanagement.adapter.in.rest.response.TokenResponseDto;
import clinickol.clinicmanagement.domain.model.auth.AuthCredentials;
import clinickol.clinicmanagement.domain.model.auth.TokenResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthRestMapper {

    public AuthCredentials toDomain(AuthRequest request) {
        if (request == null) return null;
        
        AuthCredentials credentials = new AuthCredentials();
        credentials.setNombreUsuario(request.getNombreUsuario());
        credentials.setContrasena(request.getContrasena());
        
        return credentials;
    }

    public TokenResponseDto toResponse(TokenResponse tokenResponse) {
        if (tokenResponse == null) return null;
        
        return new TokenResponseDto(
            tokenResponse.getToken(),
            tokenResponse.getTipo(),
            tokenResponse.getNombreUsuario(),
            tokenResponse.getRol()
        );
    }
}

