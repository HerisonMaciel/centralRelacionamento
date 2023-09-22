package com.centralRelacionamento.ubots.mapper;

import com.centralRelacionamento.ubots.dto.ClienteDTO;
import com.centralRelacionamento.ubots.models.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteDTO clienteDTO){
        return Cliente.builder().
                id(clienteDTO.getId()).
                nome(clienteDTO.getNome()).
                email(clienteDTO.getEmail()).
                setor(clienteDTO.getSetor()).
                build();
    }

    public static ClienteDTO toDto(Cliente cliente){
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .setor(cliente.getSetor()).
                 build();
    }

}
