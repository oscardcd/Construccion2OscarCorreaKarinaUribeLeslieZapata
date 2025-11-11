package clinickol.clinicmanagement.adapter.in.rest.controllers;

import clinickol.clinicmanagement.adapter.in.rest.mapper.AuthRestMapper;
import clinickol.clinicmanagement.adapter.in.rest.request.AuthRequest;
import clinickol.clinicmanagement.adapter.in.rest.response.TokenResponseDto;
import clinickol.clinicmanagement.application.usecases.LoginUseCase;
import clinickol.clinicmanagement.domain.model.auth.AuthCredentials;
import clinickol.clinicmanagement.domain.model.auth.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private LoginUseCase loginUseCase;

    @Autowired
    private AuthRestMapper authRestMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AuthRequest request) throws Exception {
        AuthCredentials credentials = authRestMapper.toDomain(request);
        TokenResponse token = loginUseCase.login(credentials);
        return ResponseEntity.ok(authRestMapper.toResponse(token));
    }
}

