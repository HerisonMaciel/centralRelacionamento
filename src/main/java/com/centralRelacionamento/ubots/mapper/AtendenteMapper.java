package com.centralRelacionamento.ubots.mapper;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.models.Atendente;

public class AtendenteMapper {

    public static Atendente toEntity(AtendenteDTO atendenteDTO){
        return Atendente.builder().
                id(atendenteDTO.getId()).
                nome(atendenteDTO.getNome()).
                email(atendenteDTO.getEmail()).
                setor(atendenteDTO.getSetor()).
                build();
    }

    public static AtendenteDTO toDto(Atendente atendente){
        return AtendenteDTO.builder()
                .id(atendente.getId())
                .nome(atendente.getNome())
                .email(atendente.getEmail())
                .setor(atendente.getSetor()).
                build();
    }


}
