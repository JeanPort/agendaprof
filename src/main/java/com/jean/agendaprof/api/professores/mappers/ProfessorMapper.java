package com.jean.agendaprof.api.professores.mappers;

import com.jean.agendaprof.api.professores.dtos.ProfessorRequest;
import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.core.models.Professor;

public interface ProfessorMapper {

    ProfessorResponse toProfessorResponse(Professor professor);
    Professor toProfessor(ProfessorRequest professorRequest);
    Professor toProfessorByProfessor(ProfessorRequest professorRequest, Professor professor);

}
