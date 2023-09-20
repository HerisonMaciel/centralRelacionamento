package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.enums.StatusAtendimento;
import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.repository.AtendimentoRepository;
import com.centralRelacionamento.ubots.service.AtendenteService;
import com.centralRelacionamento.ubots.service.AtendimentoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Log4j2
@Component
public class AtendimentoIpml implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final AtendenteService atendenteService;

    private static LinkedList<Atendimento> filaCartoes = new LinkedList<>();

    @Autowired
    public AtendimentoIpml(AtendimentoRepository atendimentoRepository, AtendenteService atendenteService) {
        this.atendimentoRepository = atendimentoRepository;
        this.atendenteService = atendenteService;
    }

    @Override
    public Atendimento cadastrar(Atendimento atendimento) {
        atendimento.setDataCriacao(LocalDateTime.now());
        if(verificarDisponibilidadeDoAtendente(atendimento.getAtendente().getId())){
            atendimento.setStatusAtendimento(StatusAtendimento.ABERTO);
            log.info("atendimento iniciado!");
            return atendimentoRepository.save(atendimento);
        }else{
            atendimento.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
            Atendimento atendimentoSave = atendimentoRepository.save(atendimento);
            filaCartoes.add(atendimentoSave);
            log.info("atendimento inserido na fila de espera: " + filaCartoes.toString());
            return atendimentoSave;
        }
    }

    private Boolean verificarDisponibilidadeDoAtendente(Long id){
        return atendenteService.verificarDisponibilidadeDoAtendente(id);
    }
}
