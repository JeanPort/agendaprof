package com.jean.agendaprof.core.repositories;

import com.jean.agendaprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findAllByDescricaoContaining(String q);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    Optional<Professor> findByEmail(String email);
}
