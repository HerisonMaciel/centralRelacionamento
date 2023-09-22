package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.models.Atendimento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AtendimentoService {

    Atendimento cadastrar(Atendimento toEntity);

    void finalizarAtendimento(Long id);

    AtendimentoDTO obterAtendimento(Long id);

    List<AtendimentoDTO> obterTodosAtendentes();
}
