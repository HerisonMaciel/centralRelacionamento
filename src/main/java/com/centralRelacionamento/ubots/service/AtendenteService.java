package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.models.Atendente;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AtendenteService {

    Atendente cadastrar(Atendente toEntity);

    List<AtendenteDTO> obterTodosAtendentes();

    AtendenteDTO obterAtendente(Long id);

    Boolean verificarDisponibilidadeDoAtendente(Long id);
}
