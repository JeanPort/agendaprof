package com.jean.agendaprof.core.exceptions;

public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException() {
        super("Email ja esta em uso");
    }

    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
