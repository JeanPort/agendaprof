package com.jean.agendaprof.api.alunos.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlunoResponse {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataAula;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
