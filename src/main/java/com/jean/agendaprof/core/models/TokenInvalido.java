package com.jean.agendaprof.core.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens_invalidos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TokenInvalido extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;
    @Column(name = "token")
    private String token;
}
