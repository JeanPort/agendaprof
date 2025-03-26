package com.jean.agendaprof.core.repositories;

import com.jean.agendaprof.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
