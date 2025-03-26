package com.jean.agendaprof.api.professores.services;

import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;

import java.util.List;

public interface ProfessorService {

    List<ProfessorResponse> findAll(String q);
}
