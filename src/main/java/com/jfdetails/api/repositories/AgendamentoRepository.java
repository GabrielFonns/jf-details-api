package com.jfdetails.api.repositories;

import com.jfdetails.api.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Sim, só isso! O Spring Data JPA já cria automaticamente todos os comandos
    // de Salvar, Deletar, Buscar por ID e Listar Todos por trás dos panos.
}