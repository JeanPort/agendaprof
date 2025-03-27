package com.jean.agendaprof.core.exceptions;

public class ProfessorNotFoundException extends ModelNotFoundException{

    public ProfessorNotFoundException() {
        super("Professor not found");
    }

    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
