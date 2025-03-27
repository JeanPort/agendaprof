package com.jean.agendaprof.api.alunos.services;

import com.jean.agendaprof.api.alunos.dtos.AlunoRequest;
import com.jean.agendaprof.api.alunos.dtos.AlunoResponse;
import com.jean.agendaprof.api.alunos.mappers.AlunoMapper;
import com.jean.agendaprof.core.exceptions.ProfessorNotFoundException;
import com.jean.agendaprof.core.repositories.AlunoRepository;
import com.jean.agendaprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService{

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoMapper alunoMapper;

    @Override
    public AlunoResponse insert(AlunoRequest alunoRequest, Long professorId) {

        var professor = professorRepository.findById(professorId).orElseThrow(ProfessorNotFoundException::new);
        var aluno = alunoMapper.toAluno(alunoRequest);
        aluno.setProfessor(professor);

        var alunoCadastrado = alunoRepository.save(aluno);

        return alunoMapper.toAlunoResponse(alunoCadastrado);
    }
}
