package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.repository.AtendenteRepository;
import com.centralRelacionamento.ubots.service.AtendenteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AtendenteIpml implements AtendenteService {

    private final AtendenteRepository atendenteRepository;

    @Autowired
    public AtendenteIpml(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }

    @Override
    public Atendente cadastrar(Atendente atedente) {
        log.info("salvando atendente: " + atedente);
        return atendenteRepository.save(atedente);
    }

}
