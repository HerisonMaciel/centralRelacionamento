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
@Entity(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MENSAGEM")
    private Long id;

    @Column(name = "ID_ATENDIMENTO")
    private Long idAtendimento;

    @Column(name = "ID_ATENDENTE")
    private Long idAtendente;

    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "CONTEUDO")
    private String conteudo;

    @Column(name = "DATA_ENVIO")
    private Date dataEnvio;

}
