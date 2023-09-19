package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.models.Atendimento;
import org.springframework.stereotype.Service;

@Service
public interface AtendimentoService {
    Atendimento cadastrar(Atendimento toEntity);
}
