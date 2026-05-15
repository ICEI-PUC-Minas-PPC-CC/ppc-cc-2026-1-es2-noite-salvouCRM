package com.salvou.crm;

import jakarta.persistence.*;
        import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muitas vendas podem ser do mesmo cliente
    @JoinColumn(name = "cliente_id") // Isso cria a coluna de ligação no banco
    private Cliente cliente;

    @ManyToMany // Uma venda pode ter vários serviços, e um serviço pode estar em várias vendas
    @JoinTable(
            name = "venda_servicos",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;

    private Double valorTotal;
    private LocalDateTime dataVenda = LocalDateTime.now();

    // Getters e Setters
    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Servico> getServicos() { return servicos; }
    public void setServicos(List<Servico> servicos) { this.servicos = servicos; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}