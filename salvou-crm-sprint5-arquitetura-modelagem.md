# Sprint 5 — Relação entre Implementação, Arquitetura e Modelagem

**Projeto:** Salvou CRM
**Disciplina:** Engenharia de Software II
**Repositório:** https://github.com/llagogabriel/salvou-crm (branch `Verificações`)

---

## 1. Arquitetura adotada

O Salvou CRM segue uma **arquitetura em camadas** baseada no padrão **MVC
(Model–View–Controller)**, implementada com o **Spring Boot**. A separação de
responsabilidades em camadas é o que mantém o código organizado e coerente com a
arquitetura planejada nas sprints anteriores.

| Camada | Responsabilidade | Onde está no código |
|---|---|---|
| **Apresentação (View)** | Telas que o usuário vê e onde digita os dados | Templates Thymeleaf em `resources/templates/` (`home.html`, `lista-clientes.html`, `cadastro-cliente.html`, `perfil-cliente.html`, `venda.html`, `agenda.html`) + Bootstrap/CSS |
| **Controle (Controller)** | Recebe as requisições HTTP, orquestra a lógica e devolve a resposta/tela | `HomeController`, `ClienteController`, `VendaController`, `AgendaController` |
| **Modelo / Domínio (Model)** | Representa as entidades do negócio | `Cliente`, `Venda`, `Servico`, `Agenda` |
| **Persistência (Repository)** | Acesso e gravação no banco de dados | `ClienteRepository`, `VendaRepository`, `ServicoRepository`, `AgendaRepository` |
| **Banco de dados** | Armazenamento físico dos dados | Banco **H2** em arquivo (`data/salvouDB`) |

### Princípios da arquitetura aplicados no código
- **Injeção de dependência / Inversão de controle:** os controllers não criam os
  repositórios manualmente; o Spring injeta as dependências via `@Autowired`. Isso
  reduz o acoplamento entre as camadas.
- **Camada de persistência abstraída:** os repositórios são interfaces que estendem
  `JpaRepository`. Não escrevemos SQL manual — o Spring Data gera as operações de
  CRUD e as consultas a partir do nome dos métodos (ex.: `existsByCpf`,
  `findByConcluidoFalse`, `findByDataAgendaBetween`).
- **Separação View/Controller:** os controllers nunca montam HTML; eles preparam os
  dados (via `Model`) e delegam a renderização ao Thymeleaf. Assim, mudar a tela não
  afeta a lógica e vice-versa.

---

## 2. Modelagem de classes e como foi implementada

As classes de domínio definidas na modelagem (diagrama de classes) foram
implementadas como **entidades JPA** (anotação `@Entity`), em que **cada classe vira
uma tabela** e **cada atributo vira uma coluna**. Os relacionamentos do diagrama
foram traduzidos em anotações de mapeamento.

### 2.1. Entidades
- **Cliente** — `id`, `nome`, `telefone`, `cpf` (único), `endereco`, `email`,
  `dataNascimento` e a lista de `vendas`.
- **Venda** — `id`, `cliente`, lista de `servicos`, `valorTotal`, `dataVenda`.
- **Servico** — `id`, `nome`, `preco`.
- **Agenda** — `id`, `descricao`, `tipo`, `dataAgenda`, `concluido`, `observacoes`
  e um `cliente` (opcional).

### 2.2. Relacionamentos (do diagrama → código)

| Relacionamento na modelagem | Cardinalidade | Como foi implementado |
|---|---|---|
| Cliente possui Vendas | 1 — N | `@OneToMany` em `Cliente.vendas` / `@ManyToOne` em `Venda.cliente` |
| Venda contém Serviços | N — N | `@ManyToMany` em `Venda.servicos` com tabela de junção `venda_servicos` |
| Agenda referencia Cliente | N — 1 (opcional) | `@ManyToOne` em `Agenda.cliente` |

Esses relacionamentos são exatamente os que o sistema usa em tempo de execução: ao
registrar uma venda, o vínculo `Venda → Cliente` e `Venda → Serviços` é criado e
gravado; ao abrir o perfil, a relação `Cliente → Vendas` é percorrida para somar o
total gasto (`getValorTotalVendas()`).

---

## 3. Rastreabilidade: Requisito → Classe → Arquitetura → Implementação

A tabela abaixo mostra que a implementação mantém a relação entre **o que foi pedido**
(histórias de usuário), **a modelagem** (classes) e **a arquitetura** (camadas):

| Requisito / História de usuário | Classe(s) de modelo | Camada de controle | Persistência |
|---|---|---|---|
| Cadastrar e listar clientes; impedir CPF repetido | `Cliente` | `ClienteController` | `ClienteRepository.existsByCpf` |
| Registrar venda de serviços e calcular o total | `Venda`, `Servico` | `VendaController` | `VendaRepository`, `ServicoRepository` |
| Ver o total gasto por um cliente | `Cliente`, `Venda` | `ClienteController` (perfil) | `ClienteRepository` |
| Organizar compromissos/agenda | `Agenda` | `AgendaController` | `AgendaRepository` |

---

## 4. Coerência entre arquitetura, modelagem e implementação

- **A arquitetura foi respeitada:** cada requisição segue o caminho previsto
  *View → Controller → Repository → Banco → View*, sem "atalhos" que misturem
  responsabilidades (ex.: a tela não acessa o banco diretamente; quem faz isso é o
  repositório, chamado pelo controller).
- **A modelagem foi seguida fielmente:** as classes, atributos e relacionamentos do
  diagrama de classes correspondem 1:1 às entidades JPA implementadas.
- **O escopo do MVP foi atendido:** as funcionalidades centrais (clientes, vendas e
  agenda) estão implementadas e funcionando de ponta a ponta, com persistência real.

Em resumo, a implementação não é um código solto: ela é a **materialização direta**
da modelagem de classes dentro da arquitetura MVC em camadas que o grupo definiu nas
sprints anteriores.
