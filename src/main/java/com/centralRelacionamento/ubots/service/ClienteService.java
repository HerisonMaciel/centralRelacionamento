package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.models.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    Cliente cadastrar(Cliente toEntity);
}
