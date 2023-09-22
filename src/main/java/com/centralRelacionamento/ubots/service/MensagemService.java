package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.dto.MensagemDTO;
import com.centralRelacionamento.ubots.models.Mensagem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MensagemService {
    Mensagem cadastrar(Mensagem toEntity);

    List<MensagemDTO> obterTodasMensagens();

    MensagemDTO obterAtendimento(Long id);
}
