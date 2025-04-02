package com.jean.agendaprof.api.professores.controllers;

import com.jean.agendaprof.api.common.routes.ApiRoutes;
import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.api.professores.services.MeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeRestController {

    private final MeService meService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(ApiRoutes.BUSCAR_LOGADO)
    public ResponseEntity<ProfessorResponse> buscarProfessorLogado() {
        var professor = meService.buscarProfessorLogado();
        return ResponseEntity.ok(professor);
    }
}
