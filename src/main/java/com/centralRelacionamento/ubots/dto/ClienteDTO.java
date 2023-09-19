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
public class ClienteDTO implements Serializable {

    @JsonProperty("id_cliente")
    private Long id;

    @JsonProperty("nome_cliente")
    private String nome;

    @JsonProperty("email_cliente")
    private String email;

    @JsonProperty("setor_cliente")
    private Setor setor;
}
