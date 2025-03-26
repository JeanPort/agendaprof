package com.jean.agendaprof.api.professores.mappers;

import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.core.models.Professor;

public interface ProfessorMapper {

    ProfessorResponse toProfessorResponse(Professor professor);
}
