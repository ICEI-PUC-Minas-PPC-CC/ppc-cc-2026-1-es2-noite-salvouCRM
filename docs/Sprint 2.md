# Sprint 2 - Modelagem de Classes e Relacionamentos

<span style="color:red">Pré-requisitos: <a href="Sprint 1.md"> Sprint 1 - Análise de Requisistos e Identificação das Classes</a></span>

## Diagramas de Classes

Explicação dos relacionamentos do diagrama

O diagrama foi organizado com a classe Cliente como elemento central, pois a maioria das funcionalidades do sistema depende do cadastro e do acompanhamento dos clientes. A partir dela, são conectadas as classes responsáveis por vendas, observações, compromissos e histórico.

Cliente e Venda

Um Cliente pode possuir várias Vendas, mas cada Venda pertence a apenas um Cliente.

Essa decisão é adequada porque uma venda sempre precisa estar vinculada a alguém, enquanto um cliente pode realizar nenhuma, uma ou várias compras ao longo do tempo.

Multiplicidade:

Cliente 1 -------- 0..* Venda
Venda e Servico

A relação entre Venda e Servico foi modelada por meio da classe intermediária VendaServico.

Isso é importante porque uma venda pode conter vários serviços, e um mesmo serviço pode aparecer em várias vendas diferentes. Além disso, VendaServico permite armazenar dados específicos daquela venda, como quantidade e valor unitário no momento da transação.

Multiplicidade:

Venda 1 -------- 0..* VendaServico
Servico 1 -------- 0..* VendaServico

Essa escolha evita duplicação de dados e representa corretamente uma relação muitos-para-muitos.

Cliente e Observacao

Um Cliente pode possuir várias Observacoes, enquanto cada Observacao pertence a apenas um cliente.

Essa relação permite registrar anotações específicas sobre o relacionamento com o cliente, como preferências, comentários, pendências ou informações úteis para atendimentos futuros.

Multiplicidade:

Cliente 1 -------- 0..* Observacao
Cliente e Compromisso

Um Cliente pode estar associado a vários Compromissos, mas cada compromisso pertence a um único cliente.

Essa decisão faz sentido porque o sistema precisa controlar tarefas, atendimentos ou agendamentos relacionados a cada cliente individualmente.

Multiplicidade:

Cliente 1 -------- 0..* Compromisso
Compromisso e Lembrete

Um Compromisso pode possuir um Lembrete, e o lembrete está associado a um compromisso específico.

Essa modelagem permite que o sistema gere alertas automáticos para evitar o esquecimento de prazos ou tarefas importantes.

Multiplicidade:

Compromisso 1 -------- 1 Lembrete

Uma alternativa possível seria permitir 0..* Lembretes por compromisso, caso o sistema aceite mais de um alerta para a mesma tarefa.

Cliente e HistoricoCliente

Um Cliente pode possuir vários registros em HistoricoCliente, enquanto cada registro de histórico pertence a apenas um cliente.

A classe HistoricoCliente foi adicionada para centralizar a cronologia das interações realizadas com o cliente, como vendas, compromissos concluídos e observações registradas.

Multiplicidade:

Cliente 1 -------- 0..* HistoricoCliente

Essa decisão melhora a organização do sistema, pois o histórico não fica espalhado apenas nas classes Venda, Observacao e Compromisso.

HistoricoCliente com Venda, Compromisso e Observacao

O HistoricoCliente se relaciona com essas classes porque ele registra eventos relevantes ocorridos com o cliente.

Uma venda finalizada, uma observação criada ou um compromisso concluído podem gerar uma entrada no histórico.

Essa decisão foi tomada para permitir uma visão consolidada da relação com o cliente.

Usuario

A classe Usuario representa quem acessa o sistema. Ela possui dados de autenticação, como email, senha e sessão ativa.

Ela foi mantida separada das classes de negócio porque sua função principal é controlar login, logout e segurança de acesso.

A decisão de não misturar Usuario com Cliente é correta, pois o cliente é uma pessoa atendida pelo profissional, enquanto o usuário é quem utiliza o sistema.

Justificativa geral das decisões

O diagrama separa bem as responsabilidades de cada classe. Cliente concentra os dados cadastrais, Venda registra transações, Servico representa os itens vendidos, Compromisso controla agendamentos, Lembrete gera alertas, Observacao registra anotações e HistoricoCliente consolida as interações.

Essa estrutura reduz duplicação, melhora a manutenção do sistema e facilita futuras expansões, como relatórios, filtros, notificações e acompanhamento do relacionamento com cada cliente.

<li><a href="/docs/EngSoftware-I/Salvou%20-%20Modelo%20de%20Classes.pdf"> Diagrama de Classes</a></li>

## Explicação dos Relacionamentos e Justificativa das Decisões


