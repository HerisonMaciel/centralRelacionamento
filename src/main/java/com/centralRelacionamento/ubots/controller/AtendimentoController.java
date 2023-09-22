package com.centralRelacionamento.ubots.controller;


import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.mapper.AtendimentoMapper;
import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.service.AtendimentoService;
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

    @Operation(summary = "Cadastrar atendimento", description = "Cadastrar atendimento", tags = {"Atendimento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendimentoDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody AtendimentoDTO atendimentoDTO) {
        Atendimento atendimentoCadastrado = atendimentoService.cadastrar(AtendimentoMapper.toEntity(atendimentoDTO));
        log.info("Atendimento cadastrado: " + atendimentoCadastrado);
        return new ResponseEntity<>(toDto(atendimentoCadastrado), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar todos atendimentos", description = "Buscar todos atendimentos", tags = {"Atendimento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendimentoDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping
    public ResponseEntity<List<AtendimentoDTO>> obterTodosAtendimentos(){
        try{
            List<AtendimentoDTO> atendimentoDTOList = atendimentoService.obterTodosAtendentes();
            return new ResponseEntity<>(atendimentoDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Buscar atendimento", description = "Busca um atendimento pelo id", tags = {"Atendimento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendimentoDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> obterAtendimento(@Parameter(description = "id do atendimento") @PathVariable Long id){
        try {
            AtendimentoDTO atendimentoDTO = this.atendimentoService.obterAtendimento(id);
            log.info("Atendimento: " + atendimentoDTO.toString());
            return new ResponseEntity<>(atendimentoDTO, HttpStatus.OK);
        }catch (Exception e){
            log.info("Atendimento não encontrado para o id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Finalizar atendimento", description = "Finaliza o atendimento", tags = {"Atendimento"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendimentoDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PutMapping(value = "/finalizar/{id}")
    public ResponseEntity<Void> finalizarAtendimento(@Parameter(description = "id do atendimento") @PathVariable Long id){
        this.atendimentoService.finalizarAtendimento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
