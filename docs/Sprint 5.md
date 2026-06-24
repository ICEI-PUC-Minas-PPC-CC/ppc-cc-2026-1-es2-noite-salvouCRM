# Sprint 5 - Implementação do MVP

<span style="color:red">Pré-requisitos: <a href="Sprint 4.md"> Sprint 4 - Organização, Integração e Planejamento do MVP</a></span>


# Sprint 5 — MVP, Arquitetura e Modelagem

**Projeto:** Salvou CRM
**Disciplina:** Engenharia de Software II
**Repositório de código:** https://github.com/llagogabriel/salvou-crm (branch `Verificações`)
**Vídeo de demonstração:** https://youtu.be/eiR7Tdm-rd4

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
- **Cadastro de cliente** com nome, telefone, CPF, endereço, e-mail e data de nascimento.
- **Validação de regra de negócio:** não permite CPF duplicado (verificação via
  `existsByCpf`), exibindo mensagem de erro ao usuário.
- **Listagem de todos os clientes** cadastrados.
- **Perfil do cliente**, incluindo o cálculo do **valor total já gasto** pelo cliente
  (somatório de todas as suas vendas — `getValorTotalVendas()`).
- **Cadastro rápido** de cliente via requisição assíncrona (`POST /clientes/rapido`),
  usado dentro da tela de vendas.

### 2.2. Módulo de Vendas e Serviços
- **Registro de venda** vinculando um **cliente** a um ou mais **serviços**.
- **Cálculo automático do valor total** da venda, a partir dos preços dos serviços
  selecionados (o servidor reconsulta os preços reais no banco, evitando manipulação
  pelo cliente).
- **Data/hora da venda** registrada automaticamente.
- **Cadastro rápido de serviço** (`POST /servicos/rapido`).
- Relacionamentos persistidos: **Cliente 1–N Venda** e **Venda N–N Serviço**.

### 2.3. Módulo de Agenda
- **Cadastro de compromissos** (atendimento, lembrete, pagamento, etc.), com descrição,
  tipo, data, observações e vínculo **opcional** a um cliente.
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
2. **Processamento:** os dados são enviados ao `VendaController`, que busca o cliente
   e os serviços reais no banco, **calcula o valor total**, cria o vínculo entre as
   entidades e registra a data da venda.
3. **Saída/Persistência:** a venda é salva no banco H2 via `VendaRepository`, e o
   sistema retorna a confirmação. O valor passa a compor o **total gasto** que aparece
   no perfil do cliente.

O mesmo padrão (formulário → controller → repositório → banco → tela) se repete nos
módulos de Clientes e Agenda, garantindo um ciclo completo e coerente.

---

## 4. Arquitetura adotada

O Salvou CRM segue uma **arquitetura em camadas** baseada no padrão **MVC
(Model–View–Controller)**, implementada com o **Spring Boot**.

| Camada | Responsabilidade | Onde está no código |
|---|---|---|
| **Apresentação (View)** | Telas que o usuário vê e onde digita os dados | Templates Thymeleaf em `resources/templates/` (`home.html`, `lista-clientes.html`, `cadastro-cliente.html`, `perfil-cliente.html`, `venda.html`, `agenda.html`) + Bootstrap/CSS |
| **Controle (Controller)** | Recebe as requisições HTTP, orquestra a lógica e devolve a resposta/tela | `HomeController`, `ClienteController`, `VendaController`, `AgendaController` |
| **Modelo / Domínio (Model)** | Representa as entidades do negócio | `Cliente`, `Venda`, `Servico`, `Agenda` |
| **Persistência (Repository)** | Acesso e gravação no banco de dados | `ClienteRepository`, `VendaRepository`, `ServicoRepository`, `AgendaRepository` |
| **Banco de dados** | Armazenamento físico dos dados | Banco **H2** em arquivo (`data/salvouDB`) |

### Princípios da arquitetura aplicados no código
- **Injeção de dependência / Inversão de controle:** os controllers não criam os
  repositórios manualmente; o Spring injeta as dependências via `@Autowired`,
  reduzindo o acoplamento entre as camadas.
- **Camada de persistência abstraída:** os repositórios são interfaces que estendem
  `JpaRepository`. Não há SQL manual — o Spring Data gera o CRUD e as consultas a
  partir do nome dos métodos (ex.: `existsByCpf`, `findByConcluidoFalse`,
  `findByDataAgendaBetween`).
- **Separação View/Controller:** os controllers nunca montam HTML; preparam os dados
  (via `Model`) e delegam a renderização ao Thymeleaf.

---

## 5. Modelagem de classes e como foi implementada

As classes de domínio definidas na modelagem (diagrama de classes) foram
implementadas como **entidades JPA** (anotação `@Entity`), em que **cada classe vira
uma tabela** e **cada atributo vira uma coluna**.

### 5.1. Entidades
- **Cliente** — `id`, `nome`, `telefone`, `cpf` (único), `endereco`, `email`,
  `dataNascimento` e a lista de `vendas`.
- **Venda** — `id`, `cliente`, lista de `servicos`, `valorTotal`, `dataVenda`.
- **Servico** — `id`, `nome`, `preco`.
- **Agenda** — `id`, `descricao`, `tipo`, `dataAgenda`, `concluido`, `observacoes` e
  um `cliente` (opcional).

### 5.2. Relacionamentos (do diagrama → código)

| Relacionamento na modelagem | Cardinalidade | Como foi implementado |
|---|---|---|
| Cliente possui Vendas | 1 — N | `@OneToMany` em `Cliente.vendas` / `@ManyToOne` em `Venda.cliente` |
| Venda contém Serviços | N — N | `@ManyToMany` em `Venda.servicos` com tabela de junção `venda_servicos` |
| Agenda referencia Cliente | N — 1 (opcional) | `@ManyToOne` em `Agenda.cliente` |

---

## 6. Rastreabilidade: Requisito → Classe → Arquitetura → Implementação

| Requisito / História de usuário | Classe(s) de modelo | Camada de controle | Persistência |
|---|---|---|---|
| Cadastrar e listar clientes; impedir CPF repetido | `Cliente` | `ClienteController` | `ClienteRepository.existsByCpf` |
| Registrar venda de serviços e calcular o total | `Venda`, `Servico` | `VendaController` | `VendaRepository`, `ServicoRepository` |
| Ver o total gasto por um cliente | `Cliente`, `Venda` | `ClienteController` (perfil) | `ClienteRepository` |
| Organizar compromissos/agenda | `Agenda` | `AgendaController` | `AgendaRepository` |

---

## 7. Coerência entre arquitetura, modelagem e implementação

- **A arquitetura foi respeitada:** cada requisição segue o caminho previsto
  *View → Controller → Repository → Banco → View*, sem misturar responsabilidades.
- **A modelagem foi seguida fielmente:** classes, atributos e relacionamentos do
  diagrama correspondem 1:1 às entidades JPA implementadas.
- **O escopo do MVP foi atendido:** as funcionalidades centrais (clientes, vendas e
  agenda) estão implementadas e funcionando de ponta a ponta, com persistência real.

---

## 8. Tecnologias utilizadas

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

## 9. Como executar

**Forma recomendada (Windows, sem IDE):** na raiz do projeto, dê dois cliques em
`iniciar-app.cmd`. Ele compila e sobe o servidor.

**Pela IDE (IntelliJ ou VS Code):** abra `SalvouApplication.java` e clique em ▶ Run.

Em qualquer forma, aguarde as mensagens `Started SalvouApplication in X seconds` e
`Tomcat started on port 8080`. Depois acesse:

```
http://localhost:8080/
```

> O banco H2 fica salvo em `./data/salvouDB`, preservando os dados entre execuções.
> Console do banco disponível em `http://localhost:8080/h2-console`.
