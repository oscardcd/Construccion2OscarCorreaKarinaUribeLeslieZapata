package clinickol.clinicmanagement.adapter.out.security;

import clinickol.clinicmanagement.domain.model.EmpleadoDomain;
import clinickol.clinicmanagement.domain.model.auth.AuthCredentials;
import clinickol.clinicmanagement.domain.model.auth.TokenResponse;
import clinickol.clinicmanagement.domain.ports.AuthenticationPort;
import clinickol.clinicmanagement.domain.ports.EmpleadoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAdapter implements AuthenticationPort {

    @Autowired
    private EmpleadoPort empleadoPort;

    @Autowired
    private JwtAdapter jwtAdapter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse authenticate(AuthCredentials credentials) throws Exception {
        EmpleadoDomain empleado = empleadoPort.findByNombreUsuario(credentials.getNombreUsuario());
        
        if (empleado == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (!empleado.isActivo()) {
            throw new Exception("Usuario inactivo");
        }

        if (!passwordEncoder.matches(credentials.getContrasena(), empleado.getContrasena())) {
            throw new Exception("Credenciales inválidas");
        }

        String token = jwtAdapter.generateToken(
                empleado.getNombreUsuario(),
                empleado.getRol().name()
        );

        return new TokenResponse(token, empleado.getNombreUsuario(), empleado.getRol().name());
    }

    @Override
    public String generateToken(String nombreUsuario, String rol) throws Exception {
        return jwtAdapter.generateToken(nombreUsuario, rol);
    }

    @Override
    public boolean validateToken(String token) throws Exception {
        return jwtAdapter.validateToken(token);
    }

    @Override
    public String getUsernameFromToken(String token) throws Exception {
        return jwtAdapter.getUsernameFromToken(token);
    }
}

