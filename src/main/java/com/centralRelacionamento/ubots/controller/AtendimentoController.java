package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.mapper.AtendimentoMapper;
import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.service.AtendimentoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.centralRelacionamento.ubots.mapper.AtendimentoMapper.toDto;

@Log4j2
@RequestMapping(value = "/atendimento")
@RestController
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody AtendimentoDTO atendimentoDTO) {
        Atendimento atendimentoCadastrado = atendimentoService.cadastrar(AtendimentoMapper.toEntity(atendimentoDTO));
        log.info("Atendimento cadastrado: " + atendimentoCadastrado);
        return new ResponseEntity<>(toDto(atendimentoCadastrado), HttpStatus.CREATED);
    }
}
