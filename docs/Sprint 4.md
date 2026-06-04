
# Sprint 4 — Organização, Integração e Planejamento do MVP

**Pré-requisitos:** [Sprint 3 — Definição da Arquitetura do Sistema](https://github.com/ICEI-PUC-Minas-PPC-CC/ppc-cc-2026-1-es2-noite-salvouCRM/blob/main/docs/Sprint%203.md)

---

## 1. Descrição do Fluxo Principal do Sistema

O fluxo principal do **Salvou** começa com o acesso do profissional autônomo e percorre o ciclo central de gestão de clientes e vendas:

```
Acesso → Dashboard → Gestão de Clientes → PDV (Registro de Venda) → Histórico / Resumo
```

### Etapas

**Acesso ao sistema:** O usuário navega até o sistema. A sessão é controlada via Spring MVC; rotas protegidas verificam a autenticidade antes de renderizar as telas.

**Dashboard:** Após o acesso, o usuário visualiza o painel `/resumo` com faturamento consolidado, histórico de vendas e filtros por período, renderizado pelo Thymeleaf com dados agregados no `Controller`.

**Gestão de clientes:** O profissional cadastra, edita ou consulta clientes. O fluxo segue `ClienteController → ClienteRepository`, com validação de CPF duplicado via `repository.existsByCpf()`. Em caso de erro, o sistema redireciona com mensagem via `RedirectAttributes` (ex: `return "redirect:/novo"`).

**Registro de venda (PDV):** Na tela `venda.html`, o usuário seleciona o cliente (incluindo Cliente de Balcão) e o vendedor responsável. O valor total é calculado dinamicamente. A venda é persistida com relacionamento `@ManyToOne` entre `Venda`, `Cliente` e `Usuario` (vendedor).

**Histórico e perfil do cliente:** Em `/clientes/perfil/{id}`, o sistema carrega o histórico de compras do cliente com filtros aplicados via Streams do Java, exibindo o valor total e cronologia de transações.

---

## 2. Definição do Escopo do MVP

O MVP foi definido com base nas funcionalidades já implementadas e nas pendentes para a entrega final.

### Incluído no MVP

| História | Funcionalidade |
|---|---|
| H1.1 | Cadastro, edição e inativação de clientes |
| H1.2 | Perfil do cliente com histórico de compras |
| H2.1 | Registro de vendas no PDV (com Cliente de Balcão e vendedor) |
| H2.3 | Dashboard de resumo da operação com filtros por período |

### Pós-MVP

| História | Funcionalidade | Motivo do adiamento |
|---|---|---|
| H3.1 | Agendamento e gerenciamento de compromissos | Não implementado no ciclo atual |
| H3.2 | Lembretes automáticos | Depende de H3.1 |
| H4.1 | Autenticação real com Spring Security | Sessão atualmente simulada via fragmento de menu |

---

## 3. Planejamento Técnico da Implementação

### Stack

| Camada | Tecnologia |
|---|---|
| Frontend | Thymeleaf + HTML5 + Bootstrap 5 |
| Backend | Spring Boot (Java) |
| Banco de dados | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Autenticação | Sessão HTTP (Spring MVC) |
| Comunicação | Spring MVC (SSR) + endpoints REST pontuais (`@ResponseBody`) |

A aplicação adota arquitetura monolítica com renderização server-side (SSR). A comunicação principal é feita por formulários HTML com redirecionamento, e apenas interações específicas (como o cadastro rápido de cliente) utilizam retorno JSON via `ResponseEntity`.

### Mapeamentos de rota (Controllers reais)

| Método | Rota | Descrição |
|---|---|---|
| GET | `/novo` | Abrir formulário de cadastro de cliente |
| POST | `/salvar` | Salvar cliente (redireciona com Flash Attribute em caso de erro) |
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/perfil/{id}` | Perfil do cliente com histórico de compras filtrado |
| POST | `/clientes/rapido` | Cadastro rápido de cliente (retorna JSON via `ResponseEntity`) |
| GET | `/resumo` | Dashboard financeiro com filtros e gráficos (Chart.js) |
| POST | `/venda` | Registrar nova venda no PDV |

### Regras de negócio implementadas

- CPF duplicado verificado via `repository.existsByCpf()` no `Controller`; erro exibido via `RedirectAttributes` na tela do formulário.
- Valor total de vendas por cliente calculado via Streams do Java no método `getValorTotalVendas()` da entidade `Cliente`.
- Relacionamento `@ManyToOne` entre `Venda`, `Cliente` e `Usuario` (vendedor) garante rastreabilidade de cada transação.
- Cliente de Balcão permite registrar vendas sem vínculo com cliente cadastrado.

### Critérios de conclusão (Definition of Done)

- [ ] Rota implementada e retornando a view ou redirecionamento correto
- [ ] Regra de negócio aplicada antes da persistência
- [ ] Tela Thymeleaf renderizando os dados corretamente
- [ ] Testado com ao menos um cenário de sucesso e um de erro
- [ ] Código integrado ao repositório sem conflitos

---

*Engenharia de Software II — PUC Minas Campus Poços de Caldas, 2026/1.*
