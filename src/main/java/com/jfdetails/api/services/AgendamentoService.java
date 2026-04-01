package com.jfdetails.api.services;

import com.jfdetails.api.models.Agendamento;
import com.jfdetails.api.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    // O "final" e a anotação @RequiredArgsConstructor fazem o Spring injetar
    // o repositório aqui automaticamente. É o jeito mais seguro e moderno de fazer isso.
    private final AgendamentoRepository agendamentoRepository;

    public Agendamento solicitarAgendamento(Agendamento novoAgendamento) {

        // 1. Regra de Negócio: O agendamento precisa ter uma data válida
        if (novoAgendamento.getDataHora() == null) {
            throw new IllegalArgumentException("A data e hora do agendamento são obrigatórias.");
        }

        // 2. Regra de Negócio: Não pode agendar no passado
        if (novoAgendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível realizar um agendamento no passado.");
        }

        // Se passou pelas regras, mandamos o repositório salvar no banco de dados
        return agendamentoRepository.save(novoAgendamento);
    }

    // Um método bônus para podermos listar os agendamentos depois
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }
    public List<String> buscarHorariosOcupados(LocalDate data) {
        // Define o início do dia (00:00:00) e o fim (23:59:59)
        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);

        // Busca no banco
        List<Agendamento> agendamentosDoDia = agendamentoRepository.findAllByDataHoraBetween(inicioDoDia, fimDoDia);

        // Transforma a lista de Agendamentos em uma lista de horas: ["08:00", "14:00"]
        return agendamentosDoDia.stream()
                .map(agendamento -> agendamento.getDataHora().toLocalTime().toString())
                .collect(Collectors.toList());
    }
}