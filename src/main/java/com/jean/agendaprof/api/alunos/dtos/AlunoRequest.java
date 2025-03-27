package com.jean.agendaprof.api.alunos.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlunoRequest {

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;
    @Email
    @NotNull
    @Size(max = 255)
    private String email;
    @Future
    private LocalDateTime dataAula;
}
