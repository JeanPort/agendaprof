package com.jean.agendaprof.api.professores.controllers;

import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.api.professores.services.ProfessorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/professores")
public class ProfessorRestController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> findAll(@RequestParam(name = "q", defaultValue = "", required = false) String q){
        var list = professorService.findAll(q);
        return ResponseEntity.ok(list);
    }

}
