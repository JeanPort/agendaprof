package com.jean.agendaprof.api.alunos.mappers;

import com.jean.agendaprof.api.alunos.dtos.AlunoRequest;
import com.jean.agendaprof.api.alunos.dtos.AlunoResponse;
import com.jean.agendaprof.core.models.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapperImpl implements AlunoMapper {


    @Override
    public Aluno toAluno(AlunoRequest alunoRequest) {
        if (alunoRequest == null) return null;

        return Aluno.builder()
                .email(alunoRequest.getEmail())
                .name(alunoRequest.getNome())
                .dataAula(alunoRequest.getDataAula())
                .build();
    }

    @Override
    public AlunoResponse toAlunoResponse(Aluno aluno) {
        if (aluno == null) return null;

        return AlunoResponse.builder()
                .id(aluno.getId())
                .email(aluno.getEmail())
                .nome(aluno.getName())
                .dataAula(aluno.getDataAula())
                .createdAt(aluno.getCreatedAt())
                .updatedAt(aluno.getUpdatedAt())
                .build();
    }
}
