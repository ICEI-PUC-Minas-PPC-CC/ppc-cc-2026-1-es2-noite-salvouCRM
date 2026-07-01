package com.salvou.crm;

public class Main {
  public static void main(String[] args) {
    // 1. Criamos o "Cérebro" do sistema
    GestorClientes salvouApp = new GestorClientes();

    // 2. Criamos os clientes
    Cliente c1 = new Cliente("Joyce Manicure");
    c1.registrarCompra(150.0, "Manicure");

    Cliente c2 = new Cliente("Marcos Pintor");
    c2.registrarCompra(300.0, "Cabelo");

    // 3. Guardamos no Gestor (Escalabilidade)
    salvouApp.adicionar(c1);
    salvouApp.adicionar(c2);

    // 4. Mostramos o resultado final
    salvouApp.exibirTodos();
  }
}