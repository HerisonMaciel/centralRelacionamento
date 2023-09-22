package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.models.Mensagem;
import org.springframework.stereotype.Service;

@Service
public interface MensagemService {
    Mensagem cadastrar(Mensagem toEntity);
}
