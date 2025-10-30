package clinickol.clinicmanagement.application.usecases;

import clinickol.clinicmanagement.domain.model.auth.AuthCredentials;
import clinickol.clinicmanagement.domain.model.auth.TokenResponse;
import clinickol.clinicmanagement.domain.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    @Autowired
    private AuthenticationService authenticationService;

    public TokenResponse login(AuthCredentials credentials) throws Exception {
        return authenticationService.authenticate(credentials);
    }
}

