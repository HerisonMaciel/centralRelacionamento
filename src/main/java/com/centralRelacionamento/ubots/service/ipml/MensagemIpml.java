package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.models.Mensagem;
import com.centralRelacionamento.ubots.repository.MensagemRepository;
import com.centralRelacionamento.ubots.service.MensagemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component
public class MensagemIpml implements MensagemService {

    private final MensagemRepository mensagemRepository;

    @Autowired
    public MensagemIpml(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    @Override
    public Mensagem cadastrar(Mensagem mensagem) {
        mensagem.setDataEnvio(LocalDateTime.now());
        log.info("Mensagem cadastrada: " + mensagem);
        return this.mensagemRepository.save(mensagem);
    }



}
