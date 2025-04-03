package com.jean.agendaprof.api.auth.services;

import com.jean.agendaprof.api.auth.dtos.LoginRequest;
import com.jean.agendaprof.api.auth.dtos.LoginResponse;
import com.jean.agendaprof.api.auth.dtos.RefreshRequest;
import com.jean.agendaprof.core.exceptions.ProfessorNotFoundException;
import com.jean.agendaprof.core.models.AuthenticatedUser;
import com.jean.agendaprof.core.repositories.ProfessorRepository;
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
    private final ProfessorRepository professorRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var username = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        var authentication = authenticationManager.authenticate(username);
        var professor = ((AuthenticatedUser) authentication.getPrincipal()).getProfessor();//Posso pegar direto no loginRequest
        var token = tokenService.gerarAccessToken(professor.getEmail());
        var refreshToken = tokenService.gerarRefreshToken(professor.getEmail());
        return LoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponse refresh(RefreshRequest refreshRequest) {
        var subject = tokenService.getSubjectDoRefreshtoken(refreshRequest.getRefreshToken());
        if (!professorRepository.existsByEmail(subject)){
            throw new ProfessorNotFoundException();
        }
        tokenService.invalidarTokens(refreshRequest.getRefreshToken());
        return LoginResponse.builder()
                .token(tokenService.gerarAccessToken(subject))
                .refreshToken(tokenService.gerarRefreshToken(subject))
                .build();
    }

    @Override
    public void logout(String token, RefreshRequest refreshRequest) {
        tokenService.invalidarTokens(token, refreshRequest.getRefreshToken());
    }
}
