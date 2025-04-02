package com.jean.agendaprof.api.common.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.jean.agendaprof.api.common.dtos.ErrorResponse;
import com.jean.agendaprof.api.common.dtos.ErrorValidationResponse;
import com.jean.agendaprof.core.exceptions.EmailAlreadyInUseException;
import com.jean.agendaprof.core.exceptions.ModelNotFoundException;
import com.jean.agendaprof.core.exceptions.PasswordsDoNotMatchException;
import com.jean.agendaprof.core.service.token.TokenServiceException;
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

    private final PropertyNamingStrategies.SnakeCaseStrategy snakeCaseStrategy = new PropertyNamingStrategies.SnakeCaseStrategy();

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

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e){
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

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<ErrorResponse> handleTokenServiceException(TokenServiceException e){
        var status = HttpStatus.UNAUTHORIZED;
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
        fieldErrors.forEach(fieldError -> {
            var fieldName = snakeCaseStrategy.translate(fieldError.getField());
            var fieldMessage = fieldError.getDefaultMessage();

            if (errors.containsKey(fieldName)){
                errors.get(fieldName).add(fieldMessage);
            }else {
                errors.put(fieldName, new ArrayList<String>(Arrays.asList(fieldMessage)));
            }
        });
        return errors;
    }


}
