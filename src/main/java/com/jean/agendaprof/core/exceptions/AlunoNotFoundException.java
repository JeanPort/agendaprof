package com.jean.agendaprof.core.exceptions;

public class AlunoNotFoundException extends ModelNotFoundException{

    public AlunoNotFoundException() {
        super("Aluno not found");
    }

    public AlunoNotFoundException(String message) {
        super(message);
    }
}
