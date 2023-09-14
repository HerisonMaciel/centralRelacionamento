package com.centralRelacionamento.ubots.models;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ATENDENTE")
    private Long id;

    @Column(name = "NOME_ATENDENTE")
    private String nome;

    @Column(name = "EMAIL_ATENDENTE")
    private String Email;

    @Column(name = "SETOR_ATENDENTE")
    private Enum<Setor> setorEnum;


}
