package com.jean.agendaprof.api.auth.controllers;

import com.jean.agendaprof.api.auth.dtos.LoginRequest;
import com.jean.agendaprof.api.auth.dtos.LoginResponse;
import com.jean.agendaprof.api.auth.dtos.RefreshRequest;
import com.jean.agendaprof.api.auth.services.AuthService;
import com.jean.agendaprof.api.common.routes.ApiRoutes;
import com.jean.agendaprof.api.common.utils.JwtBeareDefalt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping(ApiRoutes.LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var res = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping(ApiRoutes.REFRESH)
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestBody RefreshRequest refreshRequest){
        var token = authService.refresh(refreshRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping(ApiRoutes.LOGOUT)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> logout(@RequestHeader String authorization, @Valid @RequestBody RefreshRequest refreshRequest){
        var token = authorization.substring(JwtBeareDefalt.TOKEN_FINAL.length());
        authService.logout(token, refreshRequest);
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }

}
