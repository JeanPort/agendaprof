package com.jean.agendaprof.api.auth.services;

import com.jean.agendaprof.api.auth.dtos.LoginRequest;
import com.jean.agendaprof.api.auth.dtos.LoginResponse;
import com.jean.agendaprof.core.models.AuthenticatedUser;
import com.jean.agendaprof.core.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var username = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        var authentication = authenticationManager.authenticate(username);
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();
        var token = tokenService.gerarAccessToken(professor.getEmail());
        var refreshToken = tokenService.gerarRefreshToken(professor.getEmail());
        return LoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
