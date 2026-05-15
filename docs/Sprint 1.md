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

**Classe: Cliente**
   
| Responsabilidades | Colaboradores |
|-------------------|---------------|
| Armazenar dados cadastrais (nome, CPF, telefone, endereço e status). | HistoricoCliente, Venda |
| Permitir criação, edição e atualização dos dados. |  |
| Armazenar e exibir histórico de interações. | HistoricoCliente |
| Associar observações ao cliente. | Observacao |
| Alterar status (ativo/inativo). |  |
| Fornecer dados para filtragens e segmentações. |  |

---

**Classe: Venda *(Nova)***

| Responsabilidades | Colaboradores |
|---|---|
| Registrar a transação financeira vinculada a um cliente. | Cliente, Servico |
| Calcular o valor total da venda com base nos itens selecionados. | Servico |
| Alimentar o histórico do cliente após a finalização. | HistoricoCliente |

---

**Classe: Servico *(Nova)***
| Responsabilidades | Colaboradores |
|---|---|
| Armazenar a descrição e o preço unitário do item/serviço. | Venda |
| Permitir a atualização de valores para futuras vendas. | Venda |

---

**Classe: Observacao**
| Responsabilidades | Colaboradores |
|---|---|
| Registrar texto com anotações referentes ao cliente. | Cliente |
| Exibir observações no histórico. | HistoricoCliente  |

---

**Classe: Compromisso**
| Responsabilidades | Colaboradores |
|---|---|
| Agendar tarefas com o cliente. | Cliente |
| Reagendar tarefas e controlar prazos. | Cliente, Lembrete |
| Informar status (pendente, em andamento, concluído, reagendado). | HistoricoCliente |
| Associar lembretes automáticos ao compromisso. | Lembrete |
| Alimentar o histórico do cliente quando concluído. | HistoricoCliente |

---

**Classe: Lembrete**
| Responsabilidades | Colaboradores |
|---|---|
| Gerar notificações automáticas para compromissos ou tarefas. | Compromisso |
| Calcular prazos e horários para alertas. |  |
| Enviar notificações ao usuário. |  |

---

**Classe: HistoricoCliente**
| Responsabilidades | Colaboradores |
|---|---|
| Registrar vendas, compromissos realizados, anotações e atividades com o cliente. | Cliente, Venda, Compromisso, Observacao |
| Exibir cronologia de interações para tomada de decisão. |  |

---

**Classe: Usuario**
| Responsabilidades | Colaboradores |
|---|---|
| Permitir login e validação de senha. |  |
| Manter sessão ativa. |  |
| Verificar regras de segurança. |  |

## Como as histórias dos usuários se conectam com as classes?

As histórias de usuários estão diretamente conectadas às classes do sistema, pois cada funcionalidade descrita nas histórias é implementada pelas responsabilidades atribuídas às classes na modelagem.

A história H1.1, que descreve o cadastro, edição e inativação de clientes, relaciona-se principalmente à classe Cliente. Essa classe é responsável por armazenar os dados cadastrais do cliente, permitir a criação e atualização dessas informações e controlar o status ativo ou inativo.

A história H1.2, referente à visualização do histórico e ao registro de observações, conecta-se às classes HistoricoCliente, Observacao e Cliente. A classe HistoricoCliente mantém a cronologia de interações realizadas com o cliente, enquanto a classe Observacao registra anotações importantes. A classe Cliente faz a ligação dessas informações ao cliente correspondente.

A história H2.1, relacionada ao registro de vendas, envolve as classes Venda, Cliente, Servico e HistoricoCliente. A classe Venda registra a transação financeira e associa os serviços escolhidos a um cliente específico. A classe Servico fornece os produtos ou serviços e seus respectivos preços, enquanto o HistoricoCliente registra a venda realizada como parte do histórico de relacionamento.

A história H2.2, que trata do cadastro de serviços ou produtos, está associada à classe Servico. Essa classe é responsável por armazenar a descrição e o preço dos serviços, além de permitir futuras atualizações de valores.

A história H3.1, voltada ao gerenciamento de compromissos, conecta-se principalmente à classe Compromisso. Essa classe permite agendar, reagendar e concluir tarefas, além de controlar o status das atividades. A classe Lembrete complementa essa funcionalidade gerando notificações automáticas, enquanto a classe HistoricoCliente registra os compromissos concluídos.

A história H3.2, relacionada à visualização de tarefas pendentes e lembretes automáticos, está ligada às classes Compromisso e Lembrete. A classe Compromisso controla os estados das tarefas, e a classe Lembrete calcula prazos e envia notificações ao usuário para evitar atrasos.

A história H3.3, sobre filtragem de clientes, relaciona-se à classe Cliente, pois ela disponibiliza informações cadastrais e status que podem ser utilizados em segmentações e filtros.

Por fim, a história H4.1, referente ao login e à manutenção da sessão ativa, conecta-se à classe Usuario. Essa classe é responsável pela autenticação do sistema, validação de senha, controle da sessão ativa e aplicação das regras de segurança necessárias para acesso ao sistema.
