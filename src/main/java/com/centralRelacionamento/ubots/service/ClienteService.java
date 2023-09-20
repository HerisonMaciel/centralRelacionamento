package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.dto.ClienteDTO;
import com.centralRelacionamento.ubots.models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    Cliente cadastrar(Cliente toEntity);

    List<ClienteDTO> obterClientes();
}
