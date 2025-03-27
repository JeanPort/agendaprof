package com.jean.agendaprof.api.alunos.service;

import com.jean.agendaprof.api.alunos.dtos.AlunoRequest;
import com.jean.agendaprof.api.alunos.dtos.AlunoResponse;

public interface AlunoService {

    AlunoResponse insert(AlunoRequest alunoRequest, Long professorId);
}
