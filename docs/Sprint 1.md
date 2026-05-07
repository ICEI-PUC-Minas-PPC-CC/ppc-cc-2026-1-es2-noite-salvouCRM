# Sprint 1 - Análise dos Requisitos e Identificação das Classes

<span style="color:red">Pré-requisitos: <a href="1-Documentação de Contexto.md"> Documentação de Contexto</a></span>

## Documentação Engenharia de Software I 

<li><a href="/docs/EngSoftware-I/Salvou%20-%20Historias%20Detalhadas.pdf"> Histórias de Usuários Detalhadas</a></li>
<li><a href="/docs/EngSoftware-I/Salvou%20-%20Modelo%20de%20Classes.pdf"> Modelo de Classes</a></li>

## Histórias de Usuários
| ID   | Especificação da História |
|------|----------------------------|
| H1.1 | Como um profissional autônomo, eu posso cadastrar, editar e inativar clientes para manter meus dados organizados e atualizados.         |
| H1.2 | Como um profissional autônomo, eu posso visualizar o histórico dos clientes e registrar observações para acompanhar o relacionamento.   |
| H2.1 | Como um profissional autônomo, eu posso registrar vendas selecionando produtos/serviços e vinculando-os a um cliente. *(Nova)*          |
| H2.2 | Como um profissional autônomo, eu posso cadastrar novos serviços ou produtos com seus respectivos preços. *(Nova)*                      |
| H3.1 | Como um profissional autônomo, eu posso gerenciar compromissos (agendar, concluir e reagendar) para controlar minhas tarefas.           |
| H3.2 | Como um profissional autônomo, eu posso visualizar tarefas pendentes e receber lembretes automáticos para não perder prazos importantes |
| H3.3 | Como um profissional autônomo, eu posso filtrar clientes por diferentes critérios para planejar ações de relacionamento.                |
| H4.1 |  Como um usuário eu posso realizar login e manter minha sessão ativa para acessar o sistema com segurança.                              |

## Modelagem de Classes

Lista das classes identificadas e responsabilidades de cada classe:

| Classe: Cliente   |
| Responsabilidades | Colaboradores |
|-------------------|---------------|
| Armazenar dados cadastrais (nome, CPF, telefone, endereço e status). | HistoricoCliente, Venda |
| Permitir criação, edição e atualização dos dados. |  |
| Armazenar e exibir histórico de interações. | HistoricoCliente |
| Registrar observações sobre o cliente. | Observacao |
| Alterar status (ativo/inativo). |  |
| Fornecer dados para filtragens e segmentações. |  |

---

| Classe: Venda   |
| Responsabilidades | Colaboradores |
|---|---|
| Registrar a transação financeira vinculada a um cliente. | Cliente, Servico |
| Calcular o valor total da venda com base nos itens selecionados. | Servico |
| Alimentar o histórico do cliente após a finalização. | HistoricoCliente |

---

| Classe: Servico  |
| Responsabilidades | Colaboradores |
|---|---|
| Armazenar a descrição e o preço unitário do item/serviço. | Venda |
| Permitir a atualização de valores para futuras vendas. | Venda |

---

| Classe: Observacao  |
| Responsabilidades | Colaboradores |
|---|---|
| Registrar texto com anotações referentes ao cliente. | Cliente |
| Exibir observações no histórico. | HistoricoCliente  |

---

| Classe: Compromisso |
| Responsabilidades | Colaboradores |
|---|---|
| Agendar tarefas com o cliente. | Cliente |
| Reagendar tarefas e controlar prazos. | Cliente, Lembrete |
| Informar status (pendente, em andamento, concluído, reagendado). | HistoricoCliente |
| Gerar lembretes automáticos. | Lembrete  |
| Alimentar o histórico do cliente quando concluído. | HistoricoCliente |

---

| Classe: Lembrete |
| Responsabilidades | Colaboradores |
|---|---|
| Gerar notificações automáticas para compromissos ou tarefas. | Compromisso |
| Calcular prazos e horários para alertas. |  |
| Enviar notificações ao usuário. |  |

---

| Classe: HistoricoCliente |
| Responsabilidades | Colaboradores |
|---|---|
| Registrar vendas, compromissos realizados, anotações e atividades com o cliente. | Venda, Compromisso, Observacao |
| Exibir cronologia de interações para tomada de decisão. |  |
| Enviar notificações ao usuário. |  |

---

| Classe: Usuario |
| Responsabilidades | Colaboradores |
|---|---|
| Permitir login e validação de senha. |  |
| Manter sessão ativa. |  |
| Verificar regras de segurança. |  |

## Como as histórias dos usuários se conectam com as classes?


