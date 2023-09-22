package com.centralRelacionamento.ubots.service.ipml;

import com.centralRelacionamento.ubots.dto.AtendimentoDTO;
import com.centralRelacionamento.ubots.dto.MensagemDTO;
import com.centralRelacionamento.ubots.models.Atendimento;
import com.centralRelacionamento.ubots.models.Mensagem;
import com.centralRelacionamento.ubots.repository.MensagemRepository;
import com.centralRelacionamento.ubots.service.AtendimentoService;
import com.centralRelacionamento.ubots.service.MensagemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.centralRelacionamento.ubots.mapper.AtendimentoMapper.toEntity;

@Log4j2
@Component
public class MensagemIpml implements MensagemService {

    private final MensagemRepository mensagemRepository;
    private final AtendimentoService atendimentoService;

    @Autowired
    public MensagemIpml(MensagemRepository mensagemRepository, AtendimentoService atendimentoService) {
        this.mensagemRepository = mensagemRepository;
        this.atendimentoService = atendimentoService;
    }

    @Override
    public Mensagem cadastrar(Mensagem mensagem) {
        mensagem.setDataEnvio(LocalDateTime.now());
        AtendimentoDTO atendimentoDTO = atendimentoService.obterAtendimento(mensagem.getAtendimento().getId());
        mensagem.setAtendimento(toEntity(atendimentoDTO));
        return this.mensagemRepository.save(mensagem);
    }

    @Override
    public List<MensagemDTO> obterTodasMensagens() {
        log.info("Buscando todos as mensagens: ");
        List<Mensagem> mensagemList = this.mensagemRepository.findAll();
        return construirLista(mensagemList);
    }

    @Override
    public MensagemDTO obterAtendimento(Long id) {
        log.info("Buscando mensagem pelo id: " + id);
        Mensagem mensagem = this.mensagemRepository.getReferenceById(id);
        return construirMensagem(mensagem);
    }

    private MensagemDTO construirMensagem(Mensagem mensagem) {
        return MensagemDTO.builder()
                .id(mensagem.getId())
                .atendimento(mensagem.getAtendimento())
                .conteudo(mensagem.getConteudo())
                .envio(mensagem.getEnvio())
                .dataEnvio(mensagem.getDataEnvio())
                .build();
    }

    private List<MensagemDTO> construirLista(List<Mensagem> mensagemList) {
        List<MensagemDTO> mensagemDTOList = new ArrayList<>();
        for ( Mensagem mensagem: mensagemList){
            mensagemDTOList.add(
                    MensagemDTO.builder()
                    .id(mensagem.getId())
                    .atendimento(mensagem.getAtendimento())
                    .conteudo(mensagem.getConteudo())
                    .envio(mensagem.getEnvio())
                    .dataEnvio(mensagem.getDataEnvio())
                    .build()
            );
        }
        return mensagemDTOList;
    }

}
