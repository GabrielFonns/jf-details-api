package com.jfdetails.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String modeloCarro;

    @Column(nullable = false)
    private String servico; // Ex: "Polimento Técnico", "Lavagem Premium"

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 500)
    private String observacoes;
}