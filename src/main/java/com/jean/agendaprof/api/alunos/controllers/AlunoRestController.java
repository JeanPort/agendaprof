package com.jean.agendaprof.api.alunos.controllers;

import com.jean.agendaprof.api.alunos.dtos.AlunoRequest;
import com.jean.agendaprof.api.alunos.dtos.AlunoResponse;
import com.jean.agendaprof.api.alunos.services.AlunoService;
import com.jean.agendaprof.api.common.routes.ApiRoutes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlunoRestController {

    private final AlunoService alunoService;

    @PostMapping(ApiRoutes.CADASTRAR_ALUNO)
    public ResponseEntity<AlunoResponse> insert(@Valid @RequestBody AlunoRequest alunoRequest, @PathVariable Long professorId){
        var res = alunoService.insert(alunoRequest, professorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
