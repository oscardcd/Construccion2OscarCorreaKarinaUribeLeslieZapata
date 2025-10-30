package clinickol.clinicmanagement.domain.services;

import clinickol.clinicmanagement.domain.model.auth.AuthCredentials;
import clinickol.clinicmanagement.domain.model.auth.TokenResponse;
import clinickol.clinicmanagement.domain.ports.AuthenticationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationPort authenticationPort;

    public TokenResponse authenticate(AuthCredentials credentials) throws Exception {
        return authenticationPort.authenticate(credentials);
    }
}

