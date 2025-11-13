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
        if (credentials == null || credentials.getNombreUsuario() == null || credentials.getNombreUsuario().trim().isEmpty()) {
            throw new Exception("El nombre de usuario es requerido");
        }
        
        if (credentials.getContrasena() == null || credentials.getContrasena().trim().isEmpty()) {
            throw new Exception("La contraseña es requerida");
        }
        
        EmpleadoDomain empleado = empleadoPort.findByNombreUsuario(credentials.getNombreUsuario().trim());
        
        if (empleado == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (!empleado.isActivo()) {
            throw new Exception("Usuario inactivo");
        }

        // Verificar contraseña
        boolean passwordMatches = false;
        String storedPassword = empleado.getContrasena();
        
        // Si la contraseña almacenada parece ser BCrypt (empieza con $2a$, $2b$, o $2y$)
        if (storedPassword != null && (storedPassword.startsWith("$2a$") || 
                                       storedPassword.startsWith("$2b$") || 
                                       storedPassword.startsWith("$2y$"))) {
            // Comparar con BCrypt
            passwordMatches = passwordEncoder.matches(credentials.getContrasena(), storedPassword);
        } else {
            // Contraseña en texto plano (para migración gradual)
            passwordMatches = credentials.getContrasena().equals(storedPassword);
            
            // Si la contraseña coincide y está en texto plano, actualizarla a BCrypt
            if (passwordMatches) {
                empleado.setContrasena(passwordEncoder.encode(credentials.getContrasena()));
                empleadoPort.save(empleado);
            }
        }
        
        if (!passwordMatches) {
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

