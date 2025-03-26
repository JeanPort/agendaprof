package com.jean.agendaprof.api.professores.mappers;

import com.jean.agendaprof.api.professores.dtos.ProfessorResponse;
import com.jean.agendaprof.core.models.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapperImpl implements ProfessorMapper{


    @Override
    public ProfessorResponse toProfessorResponse(Professor professor) {
        if (professor == null)return null;

        return ProfessorResponse.builder()
                .id(professor.getId())
                .fotoPerfil(professor.getFotoPerfil())
                .valorHora(professor.getValorHora())
                .idade(professor.getIdade())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .descricao(professor.getDescricao())
                .createdAt(professor.getCreatedAt())
                .updatedAt(professor.getUpdatedAt())
                .build();
    }
}
