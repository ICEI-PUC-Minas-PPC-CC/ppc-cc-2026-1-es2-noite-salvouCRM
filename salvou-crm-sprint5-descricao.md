# Sprint 5 — Descrição do MVP Implementado

**Projeto:** Salvou CRM
**Disciplina:** Engenharia de Software II
**Repositório:** https://github.com/llagogabriel/salvou-crm (branch `Verificações`)

---

## 1. Visão geral do MVP

O **Salvou CRM** é um sistema de gestão de relacionamento com clientes voltado a
profissionais e pequenos negócios de serviços (ex.: manicure, cabeleireiro,
estética). O objetivo do MVP é centralizar, em uma única aplicação web, o
**cadastro de clientes, o registro de vendas/serviços e o controle de uma agenda
de compromissos**, substituindo controles manuais (caderno, planilha) por uma
ferramenta organizada e com dados persistidos.

A aplicação foi construída em **Java 17** com o framework **Spring Boot 3.2**,
seguindo o padrão **MVC (Model–View–Controller)**. A interface é renderizada no
servidor com **Thymeleaf** + **Bootstrap 5**, e os dados são persistidos em um
banco **H2 em arquivo** por meio do **Spring Data JPA / Hibernate**, garantindo
que as informações permaneçam salvas entre as execuções.

---

## 2. Funcionalidades implementadas

### 2.1. Módulo de Clientes
- **Cadastro de cliente** com nome, telefone, CPF, endereço, e-mail e data de
  nascimento.
- **Validação de regra de negócio:** não permite CPF duplicado (verificação via
  `existsByCpf`), exibindo mensagem de erro ao usuário.
- **Listagem de todos os clientes** cadastrados.
- **Perfil do cliente**, incluindo o cálculo do **valor total já gasto** pelo
  cliente (somatório de todas as suas vendas — `getValorTotalVendas()`).
- **Cadastro rápido** de cliente via requisição assíncrona (endpoint REST
  `POST /clientes/rapido`), usado dentro da tela de vendas.

### 2.2. Módulo de Vendas e Serviços
- **Registro de venda** vinculando um **cliente** a um ou mais **serviços**.
- **Cálculo automático do valor total** da venda, a partir dos preços dos
  serviços selecionados (o servidor reconsulta os preços reais no banco, evitando
  manipulação pelo cliente).
- **Data/hora da venda** registrada automaticamente.
- **Cadastro rápido de serviço** (`POST /servicos/rapido`).
- Relacionamentos persistidos: **Cliente 1–N Venda** e **Venda N–N Serviço**.

### 2.3. Módulo de Agenda
- **Cadastro de compromissos** (atendimento, lembrete, pagamento, etc.), com
  descrição, tipo, data, observações e vínculo **opcional** a um cliente.
- **Conclusão** e **exclusão** de compromissos.
- **Consultas filtradas:** itens não concluídos e itens dos **próximos 7 dias**
  (`findByConcluidoFalse`, `findByDataAgendaBetween`).

### 2.4. Tela inicial (Home)
- Página de entrada (`/`) com menu de navegação para os módulos do sistema.

---

## 3. Fluxo completo: entrada → processamento → saída

O sistema entrega o fluxo de ponta a ponta exigido pelo MVP. Exemplo do
**registro de uma venda**:

1. **Entrada:** o usuário acessa `/venda`, seleciona o cliente e os serviços no
   formulário (interface Thymeleaf/Bootstrap).
2. **Processamento:** os dados são enviados ao `VendaController`, que busca o
   cliente e os serviços reais no banco, **calcula o valor total**, cria o
   vínculo entre as entidades e registra a data da venda.
3. **Saída/Persistência:** a venda é salva no banco H2 via `VendaRepository`, e
   o sistema retorna a confirmação. O valor passa a compor o **total gasto** que
   aparece no perfil do cliente.

O mesmo padrão (formulário → controller → repositório → banco → tela) se repete
nos módulos de Clientes e Agenda, garantindo um ciclo completo e coerente.

---

## 4. Tecnologias utilizadas

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.2 |
| Web / Controllers | Spring MVC |
| Visualização (View) | Thymeleaf + Bootstrap 5 |
| Persistência | Spring Data JPA + Hibernate |
| Banco de dados | H2 (modo arquivo, dados mantidos entre execuções) |
| Build / Dependências | Maven |

---

## 5. Como executar (para a demonstração)

```bash
# Na raiz do projeto (branch Verificações)
./mvnw spring-boot:run        # Linux/Mac
mvnw.cmd spring-boot:run      # Windows

# Acessar no navegador:
http://localhost:8080/
```

> O banco H2 fica salvo em `./data/salvouDB`, preservando os dados cadastrados.
> Console do banco disponível em `http://localhost:8080/h2-console` (se necessário
> para evidência).
