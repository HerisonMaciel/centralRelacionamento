package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.MensagemDTO;
import com.centralRelacionamento.ubots.mapper.MensagemMapper;
import com.centralRelacionamento.ubots.models.Mensagem;
import com.centralRelacionamento.ubots.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @Operation(summary = "Buscar todas mensagens", description = "Buscar todas mensagens", tags = {"Mensagem"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MensagemDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping
    public ResponseEntity<List<MensagemDTO>> obterTodasMensagens(){
        try{
            List<MensagemDTO> mensagemDTOList = mensagemService.obterTodasMensagens();
            return new ResponseEntity<>(mensagemDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Buscar mensagem", description = "Busca uma mensagem pelo id", tags = {"Mensagem"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MensagemDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<MensagemDTO> obterMensagem(@Parameter(description = "id do mensagem") @PathVariable Long id){
        try {
            MensagemDTO mensagemDTO = this.mensagemService.obterAtendimento(id);
            log.info("Mensagem: " + mensagemDTO.toString());
            return new ResponseEntity<>(mensagemDTO, HttpStatus.OK);
        }catch (Exception e){
            log.info("Mensagem não encontrado para o id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
