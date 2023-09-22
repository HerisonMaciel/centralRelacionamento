package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.dto.AtendenteDTO;
import com.centralRelacionamento.ubots.models.Atendente;
import com.centralRelacionamento.ubots.repository.AtendenteRepository;
import com.centralRelacionamento.ubots.service.AtendenteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        log.info("salvando atendente: " + atedente.toString());
        return this.atendenteRepository.save(atedente);
    }

    @Override
    public List<AtendenteDTO> obterTodosAtendentes() {
        log.info("Buscando todos os atendentes: ");
        Optional<List<Atendente>> atendenteList = Optional.of(this.atendenteRepository.findAll());

        if(atendenteList.isPresent()){
            List<Atendente> atendentes = atendenteList.get();
            return construirLista(atendentes);
        }
        return null;
    }

    @Override
    public AtendenteDTO obterAtendente(Long id) {
        log.info("buscando atendente do id: " + id);
        Atendente atendente = this.atendenteRepository.getReferenceById(id);
        return construirAtendente(atendente);
    }

    @Override
    public Boolean verificarDisponibilidadeDoAtendente(Long id) {
        AtendenteDTO atendenteDTO = obterAtendente(id);
        if(atendenteDTO.getQuantAtendimento()<3){
            alterarQuantAtendimento(id, atendenteDTO.getQuantAtendimento()+1);
            return true;
        }
        return false;
    }

    @Override
    public void alterarQuant(Long id) {
        AtendenteDTO atendenteDTO = obterAtendente(id);
        if(atendenteDTO.getQuantAtendimento()>0){
            alterarQuantAtendimento(id, atendenteDTO.getQuantAtendimento()-1);
        }
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

    private AtendenteDTO construirAtendente(Atendente atendente){
        return AtendenteDTO.builder()
                .id(atendente.getId())
                .nome(atendente.getNome())
                .email(atendente.getEmail())
                .setor(atendente.getSetor())
                .quantAtendimento(atendente.getQuantAtendimento())
                .build();
    }

    private void alterarQuantAtendimento(Long id, int quantAtendimento){
        Atendente atendente = this.atendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado com o ID: " + id));
        atendente.setQuantAtendimento(quantAtendimento);
        this.atendenteRepository.save(atendente);
    }
}
