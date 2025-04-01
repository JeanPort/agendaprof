package com.jean.agendaprof.api.auth.services;

import com.jean.agendaprof.api.auth.dtos.LoginRequest;
import com.jean.agendaprof.api.auth.dtos.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
}
