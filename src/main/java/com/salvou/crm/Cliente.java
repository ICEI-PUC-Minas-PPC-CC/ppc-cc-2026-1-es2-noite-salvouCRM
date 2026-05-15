package com.salvou.crm;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nome;
    private String telefone;
    @Column(unique = true, length = 14) // Garante que não existam CPFs iguais
    private String cpf;
    private String endereco;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Venda> vendas;
    private String email;
    private LocalDate dataNascimento; // Importe java.time.LocalDate



    // Mantemos esses para o seu método registrarCompra original
    private Double valorUltimaCompra = 0.0;
    private String ultimoServico;

    // Construtor vazio (obrigatório para o Spring)
    public Cliente() {}

    // Construtor para facilitar novos cadastros
    public Cliente(String nome) {
        this.nome = nome;
    }

    // --- GETTERS E SETTERS (Essenciais para a automação) ---
    // Dica: No IntelliJ, use Alt+Insert -> Getter and Setter para gerar todos de uma vez

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    //bloco dos opcionais
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }




    // --- SEUS MÉTODOS ORIGINAIS ---

    public void exibirResumo(){
        System.out.println(" ----- RESUMO DO CLIENTE -----");
        System.out.println("Nome: " + nome);
        System.out.println("Último serviço: " + ultimoServico);
        System.out.println("Valor última compra: " + valorUltimaCompra);
    }


    public void registrarCompra(double valor, String servico){
        if(valor > 0){
            this.valorUltimaCompra = valor;
            this.ultimoServico = servico;
            // Remova a linha "this.totalCompras += valor;" daqui
            System.out.println("Sucesso: Compra de R$" + valor + " registrada para " + nome);
        }
    }


    public double getValorTotalVendas() {
        // Se a lista de vendas for nula, retorna 0 imediatamente para evitar o erro
        if (this.vendas == null || this.vendas.isEmpty()) {
            return 0.0;
        }

        return this.vendas.stream()
                .mapToDouble(v -> v.getValorTotal() != null ? v.getValorTotal() : 0.0)
                .sum();
    }





}