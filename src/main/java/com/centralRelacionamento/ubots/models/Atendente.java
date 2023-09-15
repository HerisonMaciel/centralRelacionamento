package com.centralRelacionamento.ubots.models;


import com.centralRelacionamento.ubots.enums.Setor;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "ATENDENTE")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ATENDENTE")
    private Long id;

    @Column(name = "NOME_ATENDENTE")
    private String nome;

    @Column(name = "EMAIL_ATENDENTE", unique = true)
    private String email;

    @Column(name = "SETOR_ATENDENTE")
    private Setor setor;

}
