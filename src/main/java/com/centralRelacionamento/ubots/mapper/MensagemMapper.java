package com.centralRelacionamento.ubots.mapper;

import com.centralRelacionamento.ubots.dto.MensagemDTO;
import com.centralRelacionamento.ubots.models.Mensagem;

public class MensagemMapper {

    public static Mensagem toEntity(MensagemDTO mensagemDTO){
        return Mensagem.builder()
                .id(mensagemDTO.getId())
                .atendimento(mensagemDTO.getAtendimento())
                .conteudo(mensagemDTO.getConteudo())
                .envio(mensagemDTO.getEnvio())
                .dataEnvio(mensagemDTO.getDataEnvio())
                .build();
    }

    public static MensagemDTO toDto(Mensagem mensagem){
        return MensagemDTO.builder()
                .id(mensagem.getId())
                .atendimento(mensagem.getAtendimento())
                .conteudo(mensagem.getConteudo())
                .envio(mensagem.getEnvio())
                .dataEnvio(mensagem.getDataEnvio())
                .build();
    }
}
