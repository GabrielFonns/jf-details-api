package com.jfdetails.api.controllers;

import com.jfdetails.api.dtos.AgendamentoRequestDTO;
import com.jfdetails.api.models.Agendamento;
import com.jfdetails.api.services.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    // 1. O Escudo: Usamos @Valid para ativar as validações e recebemos o DTO no lugar da Entidade
    public ResponseEntity<Agendamento> criarAgendamento(@Valid @RequestBody AgendamentoRequestDTO dto) {

        // 2. O Mapeamento: Transferindo os dados da classe segura para a classe do banco
        Agendamento agendamento = new Agendamento();
        agendamento.setNomeCliente(dto.getNomeCliente());
        agendamento.setTelefone(dto.getTelefone());
        agendamento.setModeloCarro(dto.getModeloCarro());
        agendamento.setServico(dto.getServico());
        agendamento.setDataHora(dto.getDataHora());
        agendamento.setObservacoes(dto.getObservacoes());

        // 3. O Cérebro: Enviamos a entidade montada para o Service salvar
        Agendamento novo = agendamentoService.solicitarAgendamento(agendamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }
    @GetMapping("/ocupados")
    public ResponseEntity<List<String>> buscarOcupados(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<String> horariosOcupados = agendamentoService.buscarHorariosOcupados(data);
        return ResponseEntity.ok(horariosOcupados);
    }
}