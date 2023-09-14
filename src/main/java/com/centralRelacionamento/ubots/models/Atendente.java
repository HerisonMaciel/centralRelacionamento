package com.centralRelacionamento.ubots.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ATENDENTE")
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
