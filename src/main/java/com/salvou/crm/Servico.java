package com.salvou.crm;

import jakarta.persistence.*;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;

    // Construtores
    public Servico() {}

    public Servico(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}