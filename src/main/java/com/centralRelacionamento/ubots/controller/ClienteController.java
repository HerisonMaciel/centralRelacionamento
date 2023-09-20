package com.centralRelacionamento.ubots.controller;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.dto.ClienteDTO;
import com.centralRelacionamento.ubots.mapper.ClienteMapper;
import com.centralRelacionamento.ubots.models.Cliente;
import com.centralRelacionamento.ubots.service.ClienteService;
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

    @Operation(summary = "Criando cliente", description = "Cria um cliente", tags = {"Cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity cadastrar(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteCadastrado = clienteService.cadastrar(ClienteMapper.toEntity(clienteDTO));
        log.info("Cliente cadastrado: " + clienteCadastrado);
        return new ResponseEntity<>(toDto(clienteCadastrado), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar todos Clientes", description = "Buscar todos Clientes", tags = {"Cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes(){
        try{
            List<ClienteDTO> atendenteDTOList = clienteService.obterTodosClientes();
            return new ResponseEntity<>(atendenteDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Buscar por cliente", description = "Buscar individual passando id do cleinte", tags = {"Cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> obterCliente(@Parameter(description = "id do cliente") @PathVariable Long id) {
        try {
            ClienteDTO clienteDTO = clienteService.obterCliente(id);
            log.info("Cliente: " + clienteDTO.toString());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }catch (Exception e){
            log.info("Cliente não encontrado para o id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
