package com.centralRelacionamento.ubots.dto;

import com.centralRelacionamento.ubots.enums.Envio;
import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.models.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MensagemDTO implements Serializable {

    @JsonProperty("id_mensagem")
    private Long id;

    @JsonProperty("atendimento")
    private Atendimento atendimento;

    @JsonProperty("conteudo")
    private String conteudo;

    @JsonProperty("emissor_envio")
    private Envio envio;

    @JsonProperty("data_envio")
    private LocalDateTime dataEnvio;
}
