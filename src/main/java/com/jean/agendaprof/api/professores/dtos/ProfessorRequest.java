package com.jean.agendaprof.api.professores.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfessorRequest {

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;
    @NotNull
    @Email
    private String email;
    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 18, message = "A idade mínima permitida é 18 anos.")
    @Max(value = 100, message = "A idade máxima permitida é 100 anos.")
    private String idade;
    @NotNull
    @Size(min = 10, max = 500)
    private String descricao;
    @NotNull
    @Size(min = 6, max = 255)
    private String password;
    @NotNull
    @Size(min = 6, max = 255)
    private String passwordConfirmation;

    @NotNull(message = "O valor da hora é obrigatório.")
    @DecimalMin(value = "10.0", message = "O valor mínimo permitido é 10.0.")
    @DecimalMax(value = "500.0", message = "O valor máximo permitido é 500.0.")
    private BigDecimal valorHora;
}
