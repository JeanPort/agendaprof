package com.jean.agendaprof.core.exceptions;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException() {
        super("Not found");
    }

    public ModelNotFoundException(String message) {
        super(message);
    }
}
