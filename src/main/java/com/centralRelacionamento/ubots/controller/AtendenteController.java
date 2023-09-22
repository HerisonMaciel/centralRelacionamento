package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.mapper.AtendenteMapper;
import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.service.AtendenteService;
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
import java.util.UUID;

import static com.centralRelacionamento.ubots.mapper.AtendenteMapper.toDto;
import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RequestMapping(value = "/atendente")
@RestController
public class AtendenteController {


    private final AtendenteService atendenteService;

    @Autowired
    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @Operation(summary = "Criando Atendente", description = "Cria um atendente", tags = {"Atendente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atendente criado com sucesso!",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendenteDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody AtendenteDTO atendenteDTO) {
            Atendente atendenteCadastrado = this.atendenteService.cadastrar(AtendenteMapper.toEntity(atendenteDTO));
            log.info("Atendente cadastrado: " + atendenteCadastrado);
            return new ResponseEntity<>(toDto(atendenteCadastrado), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar todos Atendentes", description = "Buscar todos Atendentes", tags = {"Atendente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendenteDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping
    public ResponseEntity<List<AtendenteDTO>> obterTodosAtendentes(){
        try {
            List<AtendenteDTO> atendenteDTOList = this.atendenteService.obterTodosAtendentes();
            return new ResponseEntity<>(atendenteDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Buscar por atendente", description = "Buscar individual passando id do Atendentes", tags = {"Atendente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AtendenteDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<AtendenteDTO> obterAtendente(@Parameter(description = "id do atendente") @PathVariable Long id) {
        try {
            AtendenteDTO atendenteDTO = this.atendenteService.obterAtendente(id);
            log.info("Atendente: " + atendenteDTO.toString());
            return new ResponseEntity<>(atendenteDTO, HttpStatus.OK);
        }catch (Exception e){
            log.info("Atendente não encontrado para o id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
