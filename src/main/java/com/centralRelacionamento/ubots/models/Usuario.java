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
