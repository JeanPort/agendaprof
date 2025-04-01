package com.jean.agendaprof.api.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 20)
    private String password;
}
