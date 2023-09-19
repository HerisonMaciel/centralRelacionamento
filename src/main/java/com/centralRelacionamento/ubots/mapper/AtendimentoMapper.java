package com.centralRelacionamento.ubots.mapper;

import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.models.Atendimento;

public class AtendimentoMapper {

    public static Atendimento toEntity(AtendimentoDTO atendimentoDTO){
        return Atendimento.builder()
                .id(atendimentoDTO.getId())
                .idAtendente(atendimentoDTO.getIdAtendente())
                .idUsuario(atendimentoDTO.getIdUsuario())
                .setorAtendimento(atendimentoDTO.getSetorAtendimento())
                .statusAtendimento(atendimentoDTO.getStatusAtendimento())
                .build();
    }

    public static AtendimentoDTO toDto(Atendimento atendimento){
        return AtendimentoDTO.builder()
                .id(atendimento.getId())
                .idAtendente(atendimento.getIdAtendente())
                .idUsuario(atendimento.getIdUsuario())
                .setorAtendimento(atendimento.getSetorAtendimento())
                .statusAtendimento(atendimento.getStatusAtendimento())
                .build();
    }

}
