package com.jean.agendaprof.api.common.handlers;

import com.jean.agendaprof.api.common.dtos.ErrorResponse;
import com.jean.agendaprof.core.exceptions.EmailAlreadyInUseException;
import com.jean.agendaprof.core.exceptions.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFoundException e){
        var status = HttpStatus.NOT_FOUND;
        var error = ErrorResponse.builder()
                .status(status.value())
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .cause(e.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyInUseException(EmailAlreadyInUseException e){
        var status = HttpStatus.BAD_REQUEST;
        var error = ErrorResponse.builder()
                .status(status.value())
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .cause(e.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(status).body(error);
    }
}
