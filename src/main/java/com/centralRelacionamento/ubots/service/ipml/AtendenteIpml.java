package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.repository.AtendenteRepository;
import com.centralRelacionamento.ubots.service.AtendenteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<AtendenteDTO> obterAtendentes() {
        log.info("Buscando todos os atendentes: ");
        Optional<List<Atendente>> atendenteList = Optional.of(atendenteRepository.findAll());

        if(atendenteList.isPresent()){
            List<Atendente> atendentes = atendenteList.get();
            return construirLista(atendentes);
        }
        return null;
    }

    private List<AtendenteDTO> construirLista(List<Atendente> atendenteList){
        List<AtendenteDTO> atendenteDTOList = new ArrayList<>();
        for (Atendente atendente: atendenteList) {
            atendenteDTOList.add(
                    AtendenteDTO.builder()
                            .id(atendente.getId())
                            .nome(atendente.getNome())
                            .email(atendente.getEmail())
                            .setor(atendente.getSetor())
                            .quantAtendimento(atendente.getQuantAtendimento())
                            .build());
        }

        return atendenteDTOList;
    }

}
