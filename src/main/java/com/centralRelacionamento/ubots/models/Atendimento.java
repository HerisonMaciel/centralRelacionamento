package com.centralRelacionamento.ubots.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ATENDIMENTO")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ATENDIMENTO")
    private Long id;

    @Column(name = "ID_ATENDENTE")
    private Long atendente;

    @Column(name = "ID_USUARIO")
    private Long usuario;

    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;

    @Column(name = "DATA_ENCERRAMENTO")
    private Date dataEncerramento;

}
