package com.jean.agendaprof.core.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("A senha e a confirmação da senha não conferem.");
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
