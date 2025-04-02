package com.jean.agendaprof.api.auth.services;

import com.jean.agendaprof.api.auth.dtos.LoginRequest;
import com.jean.agendaprof.api.auth.dtos.LoginResponse;
import com.jean.agendaprof.api.auth.dtos.RefreshRequest;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refresh(RefreshRequest refreshRequest);
}
