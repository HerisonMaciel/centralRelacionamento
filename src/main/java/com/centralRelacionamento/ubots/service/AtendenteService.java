package com.centralRelacionamento.ubots.service;

import com.centralRelacionamento.ubots.models.Atendente;
import org.springframework.stereotype.Service;


@Service
public interface AtendenteService {

    Atendente cadastrar(Atendente toEntity);

}
