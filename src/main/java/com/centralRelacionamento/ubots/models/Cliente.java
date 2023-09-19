package com.centralRelacionamento.ubots.models;

import com.centralRelacionamento.ubots.enums.Setor;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "USUARIO")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_CLIENTE")
    private String nome;

    @Column(name = "EMAIL_CLIENTE")
    private String email;

    @Column(name = "SETOR_CLIENTE")
    private Setor setor;

}
