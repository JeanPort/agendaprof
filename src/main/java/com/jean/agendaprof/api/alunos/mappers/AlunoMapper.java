package com.jean.agendaprof.api.alunos.mappers;

import com.jean.agendaprof.api.alunos.dtos.AlunoRequest;
import com.jean.agendaprof.api.alunos.dtos.AlunoResponse;
import com.jean.agendaprof.core.models.Aluno;

public interface AlunoMapper {

    Aluno toAluno(AlunoRequest alunoRequest);
    AlunoResponse toAlunoResponse(Aluno aluno);
}
