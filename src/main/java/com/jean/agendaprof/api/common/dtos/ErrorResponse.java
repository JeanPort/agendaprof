package com.jean.agendaprof.api.common.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private Integer status;
    private String message;
    private String error;
    private String cause;
    private LocalDateTime timestamp;

}
