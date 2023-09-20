package com.centralRelacionamento.ubots.dto;

import com.centralRelacionamento.ubots.enums.Setor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AtendenteDTO implements Serializable {

    @JsonProperty("id_atendente")
    private Long id;

    @JsonProperty("nome_atendente")
    private String nome;

    @JsonProperty("email_atendente")
    private String email;

    @JsonProperty("setor_atendente")
    private Setor setor;

    private int quantAtendimento;

}
