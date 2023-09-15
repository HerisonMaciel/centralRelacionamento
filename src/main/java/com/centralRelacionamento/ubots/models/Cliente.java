package com.centralRelacionamento.ubots.models;

import com.centralRelacionamento.ubots.enums.Setor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USUARIO")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_CLIENTE")
    private String nome;

    @Column(name = "EMAIL_CLIENTE")
    private String Email;

    @Column(name = "SETOR_CLIENTE")
    private Enum<Setor> setorEnum;

}
