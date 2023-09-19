package com.centralRelacionamento.ubots.dto;

import com.centralRelacionamento.ubots.enums.Setor;
import com.centralRelacionamento.ubots.enums.StatusAtendimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtendimentoDTO implements Serializable {

    @JsonProperty("id_atendimento")
    private Long id;

    @JsonProperty("id_atendente")
    private Long idAtendente;

    @JsonProperty("id_usuario")
    private Long idUsuario;

    @JsonProperty("setor_atendimento")
    private Setor setorAtendimento;

    @JsonProperty("status_atendimento")
    private StatusAtendimento statusAtendimento;

}
