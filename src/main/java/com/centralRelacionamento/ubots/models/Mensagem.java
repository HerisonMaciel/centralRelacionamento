package com.centralRelacionamento.ubots.models;

import com.centralRelacionamento.ubots.enums.Envio;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENSAGEM")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ATENDIMENTO")
    private Atendimento atendimento;

    @OneToMany
    @JoinColumn(name = "ATENDENTE")
    private Atendente atendente;

    @OneToMany
    @JoinColumn(name = "CLIENTE")
    private Cliente cliente;

    @Column(name = "CONTEUDO")
    private String conteudo;

    @Column(name = "ENVIO")
    private Envio envio;

    @Column(name = "DATA_ENVIO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEnvio;

}
