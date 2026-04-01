package com.jfdetails.api.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoRequestDTO {

    // @NotBlank garante que não venha nulo e nem vazio ("")
    @NotBlank(message = "O nome do cliente é obrigatório e não pode estar em branco.")
    private String nomeCliente;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "O modelo do carro é obrigatório.")
    private String modeloCarro;

    @NotBlank(message = "O serviço escolhido é obrigatório.")
    private String servico;

    // @Future bloqueia automaticamente qualquer data no passado!
    @NotNull(message = "A data e hora são obrigatórias.")
    @Future(message = "A data do agendamento deve ser no futuro.")
    private LocalDateTime dataHora;

    private String observacoes;


}