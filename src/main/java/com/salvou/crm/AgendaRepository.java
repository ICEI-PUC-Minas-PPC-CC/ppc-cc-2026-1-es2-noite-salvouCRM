package com.salvou.crm;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    
    // Buscar itens de agenda não concluídos
    List<Agenda> findByConcluidoFalse();
    
    // Buscar itens entre duas datas
    List<Agenda> findByDataAgendaBetween(LocalDate inicio, LocalDate fim);
    
    // Buscar itens de uma data específica
    List<Agenda> findByDataAgenda(LocalDate data);
    
    // Buscar itens não concluídos de um cliente
    List<Agenda> findByClienteIdAndConcluidoFalse(Long clienteId);
}
