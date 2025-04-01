package com.jean.agendaprof.core.service.authentication;

import com.jean.agendaprof.core.models.AuthenticatedUser;
import com.jean.agendaprof.core.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final ProfessorRepository professorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var professor = professorRepository.findByEmail(username).orElseThrow(() ->new UsernameNotFoundException("Credenciais invalidas"));
        return new AuthenticatedUser(professor);
    }
}
