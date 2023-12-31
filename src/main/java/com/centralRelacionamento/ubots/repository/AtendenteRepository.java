package com.centralRelacionamento.ubots.repository;

import com.centralRelacionamento.ubots.models.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
}
