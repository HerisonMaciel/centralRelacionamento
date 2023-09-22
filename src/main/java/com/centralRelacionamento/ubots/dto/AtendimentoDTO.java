package com.centralRelacionamento.ubots.dto;

import com.centralRelacionamento.ubots.enums.Setor;
import com.centralRelacionamento.ubots.enums.StatusAtendimento;
import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.models.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AtendimentoDTO implements Serializable {

    @JsonProperty("id_atendimento")
    private Long id;

    @JsonProperty("atendente")
    private Atendente atendente;

    @JsonProperty("cliente")
    private Cliente cliente;

    @JsonProperty("setor_atendimento")
    private Setor setorAtendimento;

    @JsonProperty("status_atendimento")
    private StatusAtendimento statusAtendimento;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("data_encerramento")
    private LocalDateTime dataEncerramento;

}
