package com.jean.agendaprof.api.professores.services;

import com.jean.agendaprof.api.professores.dtos.ProfessorRequest;
import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.api.professores.mappers.ProfessorMapper;
import com.jean.agendaprof.core.exceptions.EmailAlreadyInUseException;
import com.jean.agendaprof.core.exceptions.PasswordsDoNotMatchException;
import com.jean.agendaprof.core.exceptions.ProfessorNotFoundException;
import com.jean.agendaprof.core.models.AuthenticatedUser;
import com.jean.agendaprof.core.models.Professor;
import com.jean.agendaprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final PasswordEncoder encoder;

    @Override
    public List<ProfessorResponse> findAll(String q) {
        var professores = professorRepository.findAllByDescricaoContaining(q);
        return professores.stream().map(professorMapper::toProfessorResponse).toList();
    }

    @Override
    public ProfessorResponse findById(Long id) {
        var professor = professorRepository.findById(id).orElseThrow(ProfessorNotFoundException::new);
        return professorMapper.toProfessorResponse(professor);
    }

    @Override
    public ProfessorResponse insert(ProfessorRequest professorRequest) {
        passwordIsEquals(professorRequest);
        existsEmail(professorRequest);
        var professor = professorMapper.toProfessor(professorRequest);
        professor.setSenha(encoder.encode(professor.getSenha()));
        professor = professorRepository.save(professor);
        return professorMapper.toProfessorResponse(professor);
    }

    @Override
    public ProfessorResponse update(ProfessorRequest professorRequest) {
        passwordIsEquals(professorRequest);
        var professor = getProfessor();
        existsEmail(professorRequest, professor.getId());
        professor = professorMapper.toProfessorByProfessor(professorRequest, professor);
        professor.setSenha(encoder.encode(professor.getSenha()));
        professor = professorRepository.save(professor);

        return professorMapper.toProfessorResponse(professor);
    }

    private static Professor getProfessor() {
        var authenticated = SecurityContextHolder.getContext().getAuthentication();
        return ((AuthenticatedUser) authenticated.getPrincipal()).getProfessor();
    }

    private void existsEmail(ProfessorRequest professorRequest) {
        if (professorRepository.existsByEmail(professorRequest.getEmail())){
            throw new EmailAlreadyInUseException();
        }
    }

    private void existsEmail(ProfessorRequest professorRequest, Long id) {
        if (professorRepository.existsByEmailAndIdNot(professorRequest.getEmail(), id)){
            throw new EmailAlreadyInUseException();
        }
    }

    private void passwordIsEquals(ProfessorRequest professorRequest) {
        if (!professorRequest.getPassword().equals(professorRequest.getPasswordConfirmation())){
            throw new PasswordsDoNotMatchException();
        }
    }
}
