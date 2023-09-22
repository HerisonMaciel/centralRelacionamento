package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.MensagemDTO;
import com.centralRelacionamento.ubots.mapper.MensagemMapper;
import com.centralRelacionamento.ubots.models.Mensagem;
import com.centralRelacionamento.ubots.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.centralRelacionamento.ubots.mapper.MensagemMapper.toDto;


@Log4j2
@RequestMapping(value = "/mensagem")
@RestController
public class MensagemController {

    private final MensagemService mensagemService;

    @Autowired
    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @Operation(summary = "Cadastrar mensagem", description = "Cadastrar mensagem", tags = {"Mensagem"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MensagemDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody MensagemDTO mensagemDTO) {
        Mensagem mensagemCadastrada = mensagemService.cadastrar(MensagemMapper.toEntity(mensagemDTO));
        log.info("Mensagem cadastrado: " + mensagemCadastrada);
        return new ResponseEntity<>(toDto(mensagemCadastrada), HttpStatus.CREATED);
    }

}
