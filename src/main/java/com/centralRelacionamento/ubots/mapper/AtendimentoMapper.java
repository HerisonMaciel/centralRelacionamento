package com.centralRelacionamento.ubots.mapper;

import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.models.Atendimento;

public class AtendimentoMapper {

    public static Atendimento toEntity(AtendimentoDTO atendimentoDTO){
        return Atendimento.builder()
                .id(atendimentoDTO.getId())
                .cliente(atendimentoDTO.getCliente())
                .atendente(atendimentoDTO.getAtendente())
                .setorAtendimento(atendimentoDTO.getSetorAtendimento())
                .statusAtendimento(atendimentoDTO.getStatusAtendimento())
                .dataCriacao(atendimentoDTO.getDataCriacao())
                .dataEncerramento(atendimentoDTO.getDataEncerramento())
                .build();
    }

    public static AtendimentoDTO toDto(Atendimento atendimento){
        return AtendimentoDTO.builder()
                .id(atendimento.getId())
                .atendente(atendimento.getAtendente())
                .cliente(atendimento.getCliente())
                .setorAtendimento(atendimento.getSetorAtendimento())
                .statusAtendimento(atendimento.getStatusAtendimento())
                .build();
    }

}
