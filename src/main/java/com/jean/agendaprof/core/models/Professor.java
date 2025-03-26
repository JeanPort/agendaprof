package com.jean.agendaprof.core.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "professores")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Professor extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "idade")
    private String idade;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valor_hora")
    private BigDecimal valorHora;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @Column(name = "password")
    private String password;

}
