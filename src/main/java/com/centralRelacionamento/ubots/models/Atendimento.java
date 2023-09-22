package com.centralRelacionamento.ubots.models;

import com.centralRelacionamento.ubots.enums.Setor;
import com.centralRelacionamento.ubots.enums.StatusAtendimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "ATENDIMENTO")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATENDIMENTO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ATENDENTE")
    private Atendente atendente;

    @ManyToOne
    @JoinColumn(name = "CLIENTE")
    private Cliente cliente;

    @Column(name = "SETOR_ATENDIMENTO")
    private Setor setorAtendimento;

    @Column(name = "STATUS_ATENDIMENTO")
    private StatusAtendimento statusAtendimento;

    @Column(name = "DATA_CRIACAO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ENCERRAMENTO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataEncerramento;

}
