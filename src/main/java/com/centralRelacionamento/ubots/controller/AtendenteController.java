package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.mapper.AtendenteMapper;
import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.service.AtendenteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.centralRelacionamento.ubots.mapper.AtendenteMapper.toDto;

@Log4j2
@RequestMapping(value = "/atendente")
@RestController
public class AtendenteController {


    private final AtendenteService atendenteService;

    @Autowired
    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody AtendenteDTO AtendenteDto) {
        Atendente atendenteCadastrado = atendenteService.cadastrar(AtendenteMapper.toEntity(AtendenteDto));
        log.info("Atendente cadastrado: " + atendenteCadastrado);
        return new ResponseEntity<>(toDto(atendenteCadastrado), HttpStatus.CREATED);
    }


}
