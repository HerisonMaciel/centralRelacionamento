package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.ClienteDTO;
import com.centralRelacionamento.ubots.mapper.ClienteMapper;
import com.centralRelacionamento.ubots.models.Cliente;
import com.centralRelacionamento.ubots.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.centralRelacionamento.ubots.mapper.ClienteMapper.toDto;


@Log4j2
@RequestMapping(value = "/cliente")
@RestController
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteCadastrado = clienteService.cadastrar(ClienteMapper.toEntity(clienteDTO));
        log.info("Cliente cadastrado: " + clienteCadastrado);
        return new ResponseEntity<>(toDto(clienteCadastrado), HttpStatus.CREATED);
    }


}
