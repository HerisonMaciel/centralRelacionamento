package com.centralRelacionamento.ubots.models;

import com.centralRelacionamento.ubots.enums.Setor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_CLIENTE")
    private String nome;

    @Email(message = "O campo deve conter um endereço de e-mail válido.")
    @Column(name = "EMAIL_CLIENTE", unique = true)
    private String email;

    @Column(name = "SETOR_CLIENTE")
    private Setor setor;

}
