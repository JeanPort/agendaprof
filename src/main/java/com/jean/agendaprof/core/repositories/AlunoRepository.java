package com.jean.agendaprof.core.repositories;

import com.jean.agendaprof.core.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByEmail(String email);
}
