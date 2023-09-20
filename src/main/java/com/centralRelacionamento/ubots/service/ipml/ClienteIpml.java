package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.dto.ClienteDTO;
import com.centralRelacionamento.ubots.models.Cliente;
import com.centralRelacionamento.ubots.repository.ClienteRepository;
import com.centralRelacionamento.ubots.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ClienteDTO> obterTodosClientes() {
        log.info("Buscando todos os clientes: ");
        List<Cliente> clienteList = clienteRepository.findAll();
        return construirLista(clienteList);
    }

    @Override
    public ClienteDTO obterCliente(Long id) {
        log.info("Buscando cliente pelo id: " + id);
        Cliente cliente = clienteRepository.getReferenceById(id);
        return construirCliente(cliente);
    }

    private ClienteDTO construirCliente(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .setor(cliente.getSetor())
                .build();
    }

    private List<ClienteDTO> construirLista(List<Cliente> clienteList){
        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        for ( Cliente cliente: clienteList) {
            clienteDTOList.add(
                    ClienteDTO.builder()
                    .id(cliente.getId())
                    .nome(cliente.getNome())
                    .email(cliente.getEmail())
                    .setor(cliente.getSetor())
                    .build());
        }

        return clienteDTOList;
    }
}
