package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.enums.StatusAtendimento;
import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.repository.AtendimentoRepository;
import com.centralRelacionamento.ubots.service.AtendenteService;
import com.centralRelacionamento.ubots.service.AtendimentoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.centralRelacionamento.ubots.enums.Setor.*;

@Log4j2
@Component
public class AtendimentoIpml implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final AtendenteService atendenteService;

    private static LinkedList<Atendimento> filaCartoes = new LinkedList<>();
    private static LinkedList<Atendimento> filaEmprestimos = new LinkedList<>();
    private static LinkedList<Atendimento> filaOutros = new LinkedList<>();

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
            atendimento.setDataCriacao(LocalDateTime.now());
            atendimento.setStatusAtendimento(StatusAtendimento.AGUARDANDO);
            Atendimento atendimentoSave = atendimentoRepository.save(atendimento);
            addFila(atendimentoSave);
            log.info("atendimento inserido na fila de espera: " + filaCartoes.toString());
            return atendimentoSave;
        }
    }

    private void addFila(Atendimento atendimentoSave) {
        if(atendimentoSave.getSetorAtendimento().equals(CARTOES)){
            filaCartoes.add(atendimentoSave);
        }else if(atendimentoSave.getSetorAtendimento().equals(EMPRESTIMOS)){
            filaEmprestimos.add(atendimentoSave);
        }else if(atendimentoSave.getSetorAtendimento().equals(OUTROS)){
            filaOutros.add(atendimentoSave);
        }
    }

    @Override
    public void finalizarAtendimento(Long id) {
        Atendimento atendimento = this.atendimentoRepository.getReferenceById(id);
        atendimento.setStatusAtendimento(StatusAtendimento.FECHADO);
        atendimento.setDataEncerramento(LocalDateTime.now());
        log.info("Atendimento: " + atendimento.toString());
        this.atendimentoRepository.save(atendimento);
        this.atendenteService.alterarQuant(atendimento.getAtendente().getId());
        verificarFila(construirAtendimento(atendimento));
    }

    @Override
    public AtendimentoDTO obterAtendimento(Long id) {
        log.info("Buscando Atendimento pelo id: " + id);
        Atendimento atendimento = this.atendimentoRepository.getReferenceById(id);
        return construirAtendimento(atendimento);
    }

    @Override
    public List<AtendimentoDTO> obterTodosAtendentes() {
        log.info("Buscando todos os atendentes: ");
        List<Atendimento> atendimentoList = this.atendimentoRepository.findAll();
        return construirLista(atendimentoList);
    }

    private List<AtendimentoDTO> construirLista(List<Atendimento> atendimentoList) {
        List<AtendimentoDTO> atendimentoDTOList = new ArrayList<>();
        for ( Atendimento atendimento: atendimentoList){
            atendimentoDTOList.add(
                    AtendimentoDTO.builder()
                    .id(atendimento.getId())
                    .atendente(atendimento.getAtendente())
                    .cliente(atendimento.getCliente())
                    .setorAtendimento(atendimento.getSetorAtendimento())
                    .statusAtendimento(atendimento.getStatusAtendimento())
                    .dataCriacao(atendimento.getDataCriacao())
                    .dataEncerramento(atendimento.getDataEncerramento())
                            .build()
            );
        }
        return atendimentoDTOList;
    }

    private void verificarFila(AtendimentoDTO atendimentoDTO){
        switch (atendimentoDTO.getSetorAtendimento()){
            case CARTOES:
                if(filaCartoes.size()>0){
                    if(verificarDisponibilidadeDoAtendente(atendimentoDTO.getAtendente().getId())){
                        Atendimento atendimento = filaCartoes.poll();
                        atendimento.setStatusAtendimento(StatusAtendimento.ABERTO);
                        atendimentoRepository.save(atendimento);
                    }
                }
                break;

            case EMPRESTIMOS:
                if(filaEmprestimos.size()>0){
                    if(verificarDisponibilidadeDoAtendente(atendimentoDTO.getAtendente().getId())){
                        Atendimento atendimento = filaEmprestimos.poll();
                        atendimento.setStatusAtendimento(StatusAtendimento.ABERTO);
                        atendimentoRepository.save(atendimento);
                    }
                }
                break;

            case OUTROS:
                if(filaOutros.size()>0){
                    if(verificarDisponibilidadeDoAtendente(atendimentoDTO.getAtendente().getId())){
                        Atendimento atendimento = filaOutros.poll();
                        atendimento.setStatusAtendimento(StatusAtendimento.ABERTO);
                        atendimentoRepository.save(atendimento);
                    }
                }
                break;
        }
    }

    private AtendimentoDTO construirAtendimento(Atendimento atendimento){
        return AtendimentoDTO.builder()
                .id(atendimento.getId())
                .cliente(atendimento.getCliente())
                .atendente(atendimento.getAtendente())
                .setorAtendimento(atendimento.getSetorAtendimento())
                .statusAtendimento(atendimento.getStatusAtendimento())
                .dataCriacao(atendimento.getDataCriacao())
                .dataEncerramento(atendimento.getDataEncerramento())
                .build();
    }

    private Boolean verificarDisponibilidadeDoAtendente(Long id){
        return atendenteService.verificarDisponibilidadeDoAtendente(id);
    }

}
