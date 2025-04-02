package com.jean.agendaprof.core.repositories;

import com.jean.agendaprof.core.models.TokenInvalido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenInvalidoRepository extends JpaRepository<TokenInvalido, Long> {

    boolean existsByToken(String token);
}
