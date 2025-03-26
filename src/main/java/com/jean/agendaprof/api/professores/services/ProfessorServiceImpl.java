package com.jean.agendaprof.api.professores.services;

import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.api.professores.mappers.ProfessorMapper;
import com.jean.agendaprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    @Override
    public List<ProfessorResponse> findAll(String q) {
        var professores = professorRepository.findAllByDescriptionContaining(q);
        return professores.stream().map(professorMapper::toProfessorResponse).toList();
    }
}
