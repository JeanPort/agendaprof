package com.jean.agendaprof.api.common.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorValidationResponse {

    private Integer status;
    private String message;
    private String error;
    private String cause;
    private LocalDateTime timestamp;

    private Map<String, List<String>> erros;
}
