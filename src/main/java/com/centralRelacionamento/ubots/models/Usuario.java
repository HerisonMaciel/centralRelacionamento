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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USURIO")
    private Long id;

    @Column(name = "NOME_USURIO")
    private String nome;

    @Column(name = "EMAIL_USURIO")
    private String Email;

    @Column(name = "SETOR_USURIO")
    private Enum<Setor> setorEnum;

}
