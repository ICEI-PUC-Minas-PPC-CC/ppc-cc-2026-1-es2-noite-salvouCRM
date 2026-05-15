package com.salvou.crm;

import java.util.ArrayList;

public class GestorClientes {


    private ArrayList<Cliente> lista;


    public GestorClientes() {
        this.lista = new ArrayList<>();

    }

    // Metodo para adicionar cliente (Segurança: não aceita cliente nulo)
    public void adicionar(Cliente novoCliente) {
        if (novoCliente != null) {
            this.lista.add(novoCliente);
            System.out.println("Sistema: com.salvou.crm.Cliente " + novoCliente.getNome() + " salvo com sucesso!");
        }
    }

    // Método para listar (Base para o Dashboard do projeto)
    public void exibirTodos() {
        System.out.println("\n=== DASHBOARD: TODOS OS CLIENTES ===");
        for (Cliente c : lista) {
            // Aqui usamos o método que você já criou na classe com.salvou.crm.Cliente
            c.exibirResumo();
        }
    }

}

