package com.jean.agendaprof.api.common.handlers;

import com.jean.agendaprof.api.common.dtos.ErrorResponse;
import com.jean.agendaprof.api.common.dtos.ErrorValidationResponse;
import com.jean.agendaprof.core.exceptions.EmailAlreadyInUseException;
import com.jean.agendaprof.core.exceptions.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        var erros = getErrors(e.getFieldErrors());

        var status = HttpStatus.BAD_REQUEST;
        var error = ErrorValidationResponse.builder()
                .status(status.value())
                .message("Error de validação")
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .cause(e.getClass().getSimpleName())
                .erros(erros)
                .build();
        return ResponseEntity.status(status).body(error);
    }

    private Map<String, List<String>> getErrors(List<FieldError> fieldErrors) {
        var errors = new HashMap<String, List<String>>();
        for (var field : fieldErrors){
            if (!errors.containsKey(field.getField())){
                var list = new ArrayList<String>();
                list.add(field.getDefaultMessage());
                errors.put(field.getField(), list);
            }else {
                errors.get(field.getField()).add(field.getDefaultMessage());
            }
        }
        return errors;
    }
}
