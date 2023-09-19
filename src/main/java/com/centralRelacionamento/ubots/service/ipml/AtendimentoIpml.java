package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.repository.AtendimentoRepository;
import com.centralRelacionamento.ubots.service.AtendimentoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component
public class AtendimentoIpml implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    @Autowired
    public AtendimentoIpml(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    @Override
    public Atendimento cadastrar(Atendimento atendimento) {
        atendimento.setDataCriacao(LocalDateTime.now());
        log.info("salvando atendimento: " + atendimento);
        return atendimentoRepository.save(atendimento);
    }
}
