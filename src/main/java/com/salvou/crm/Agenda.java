package com.salvou.crm;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false)
    private String tipo; // "atendimento", "lembrete", "pagamento", etc

    @Column(nullable = false)
    private LocalDate dataAgenda;

    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Boolean concluido = false;

    @Column(length = 500)
    private String observacoes;

    // Relacionamento com Cliente (opcional - nem todo item da agenda precisa estar ligado a um cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Construtores
    public Agenda() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Agenda(String descricao, String tipo, LocalDate dataAgenda) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataAgenda = dataAgenda;
        this.dataCriacao = LocalDateTime.now();
        this.concluido = false;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(LocalDate dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
