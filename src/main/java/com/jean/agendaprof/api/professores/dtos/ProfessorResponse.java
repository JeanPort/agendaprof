package com.jean.agendaprof.api.professores.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfessorResponse {

    private Long id;

    private String nome;

    private String email;

    private String idade;

    private String descricao;

    private BigDecimal valorHora;

    private String fotoPerfil;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
