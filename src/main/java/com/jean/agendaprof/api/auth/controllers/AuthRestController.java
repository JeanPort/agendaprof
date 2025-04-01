package com.jean.agendaprof.api.auth.controllers;

import com.jean.agendaprof.api.auth.dtos.LoginRequest;
import com.jean.agendaprof.api.auth.dtos.LoginResponse;
import com.jean.agendaprof.api.auth.services.AuthService;
import com.jean.agendaprof.api.common.routes.ApiRoutes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

}
