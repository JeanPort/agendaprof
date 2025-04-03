package com.jean.agendaprof.api.professores.controllers;

import com.jean.agendaprof.api.common.routes.ApiRoutes;
import com.jean.agendaprof.api.professores.dtos.ProfessorRequest;
import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.api.professores.services.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(ApiRoutes.CADASTRO_PROFESSOR)
    public ResponseEntity<ProfessorResponse> insert(@Valid @RequestBody ProfessorRequest professorRequest){
        var result = professorService.insert(professorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping(ApiRoutes.ATUALIZAR_PROFESSORES)
    public ResponseEntity<ProfessorResponse> update(@RequestBody @Valid ProfessorRequest professorRequest){
        var professor = professorService.update(professorRequest);
        return ResponseEntity.ok(professor);
    }

}
