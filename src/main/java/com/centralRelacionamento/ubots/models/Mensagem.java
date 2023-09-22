package com.centralRelacionamento.ubots.models;

import com.centralRelacionamento.ubots.enums.Envio;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENSAGEM")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ATENDIMENTO")
    private Atendimento atendimento;

    @Column(name = "CONTEUDO")
    private String conteudo;

    @Column(name = "ENVIO")
    private Envio envio;

    @Column(name = "DATA_ENVIO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEnvio;

}
