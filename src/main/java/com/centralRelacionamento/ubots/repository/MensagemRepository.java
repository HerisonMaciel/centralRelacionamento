package com.centralRelacionamento.ubots.repository;


import com.centralRelacionamento.ubots.models.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
