package com.jean.agendaprof.api.professores.dtos;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
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
