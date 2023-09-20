package com.centralRelacionamento.ubots.models;


import com.centralRelacionamento.ubots.enums.Setor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATENDENTE")
    private Long id;

    @Column(name = "NOME_ATENDENTE")
    private String nome;

    @Email(message = "O campo deve conter um endereço de e-mail válido.")
    @Column(name = "EMAIL_ATENDENTE", unique = true)
    private String email;

    @Column(name = "SETOR_ATENDENTE")
    private Setor setor;

    @Column(name = "QUANT_ATENDIMENTO")
    private int quantAtendimento;


}
