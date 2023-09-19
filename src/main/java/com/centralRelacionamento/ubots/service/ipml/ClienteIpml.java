package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.models.Cliente;
import com.centralRelacionamento.ubots.repository.ClienteRepository;
import com.centralRelacionamento.ubots.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ClienteIpml implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteIpml(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Cliente cadastrar(Cliente cliente) {
        log.info("salvando atendente: " + cliente);
        return clienteRepository.save(cliente);
    }
}
