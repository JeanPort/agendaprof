package com.jean.agendaprof.api.professores.controllers;

import com.jean.agendaprof.api.common.routes.ApiRoutes;
import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.api.professores.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfessorRestController {

    private final ProfessorService professorService;

    @GetMapping(ApiRoutes.BUSCAR_PROFESSORES)
    public ResponseEntity<List<ProfessorResponse>> findAll(@RequestParam(name = "q", defaultValue = "", required = false) String q){
        var list = professorService.findAll(q);
        return ResponseEntity.ok(list);
    }

    @GetMapping(ApiRoutes.BUSCAR_PROFESSORES_POR_ID)
    public ResponseEntity<ProfessorResponse> findAll(@PathVariable Long professorId){
        var res = professorService.findById(professorId);
        return ResponseEntity.ok(res);
    }

}
