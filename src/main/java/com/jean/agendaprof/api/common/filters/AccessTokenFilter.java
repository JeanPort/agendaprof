package com.jean.agendaprof.api.common.filters;

import com.jean.agendaprof.api.common.utils.JwtBeareDefalt;
import com.jean.agendaprof.core.service.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = "";
        var email = "";
        var authorization = request.getHeader("Authorization");

        if (isTokenPresent(authorization)) {
            token = authorization.substring(JwtBeareDefalt.TOKEN_FINAL.length());
            email = tokenService.getSubjectdoToken(token);
        }

        if (isEmailNotInContext(email)){
            setAuthentication(request, email);
        }
        filterChain.doFilter(request,response);
    }

    private void setAuthentication(HttpServletRequest request, String email) {
        var userDetail = userDetailsService.loadUserByUsername(email);
        var authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static boolean isEmailNotInContext(String email) {
        return email != null && !email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private static boolean isTokenPresent(String authorization) {
        return authorization != null && authorization.startsWith(JwtBeareDefalt.TOKEN_FINAL);
    }
}
