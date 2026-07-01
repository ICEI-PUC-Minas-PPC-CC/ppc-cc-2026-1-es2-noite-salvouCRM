# Sprint 5 — Roteiro do Vídeo de Demonstração (Evidência de Funcionamento)

**Projeto:** Salvou CRM
**Duração sugerida:** 4 a 6 minutos
**Objetivo:** comprovar que o MVP funciona de ponta a ponta (entrada → processamento → saída),
mostrando os módulos de Clientes, Vendas/Serviços e Agenda.

---

## 0. Antes de gravar (preparação)

1. **Feche** qualquer execução anterior da aplicação (para não travar a porta 8080 nem o banco).
2. Abra o projeto no IntelliJ **ou** rode pela linha de comando.
3. Tenha o navegador aberto em uma aba limpa.
4. (Opcional, mas recomendado) Comece a gravação já mostrando o **código no editor** por alguns
   segundos, para o professor ver que é seu projeto.

### Como iniciar a aplicação
**Forma recomendada (dois cliques):** na pasta do projeto, dê dois cliques em
`iniciar-app.cmd`. Abre uma janela que compila e sobe o servidor.

**Pelo VS Code / IntelliJ:** abra `SalvouApplication.java` e clique em ▶ Run.

Em qualquer uma das formas, aguarde as mensagens:
`Started SalvouApplication in X seconds` e `Tomcat started on port 8080`.

Depois acesse no navegador: **http://localhost:8080/**

> Para **parar** a aplicação, feche a janela preta do `iniciar-app.cmd`
> (ou pressione `Ctrl+C` nela). Faça isso antes de iniciar de novo, para não
> travar a porta 8080.

---

## 1. Roteiro — passo a passo (com narração)

### Cena 1 — Apresentação (15–20s)
- Mostre rapidamente o **código no editor** (estrutura `src/main/java/com/salvou/crm`).
- **Fale:** "Este é o Salvou CRM, um sistema de gestão de clientes para pequenos negócios de
  serviços. Foi desenvolvido em Java com Spring Boot, seguindo o padrão MVC, com banco de dados H2."

### Cena 2 — Tela inicial (15s)
- Acesse **http://localhost:8080/**.
- **Fale:** "Esta é a tela inicial, com o menu de navegação para os módulos do sistema:
  clientes, vendas e agenda."

### Cena 3 — Listar clientes (20s)
- Clique em **Clientes** (ou acesse `/clientes`).
- **Fale:** "Aqui estão os clientes já cadastrados, carregados do banco de dados."
- Mostre que a lista tem vários clientes.

### Cena 4 — Cadastrar um novo cliente (entrada de dados) (40s)
- Clique em **Novo cliente** (`/novo`).
- Preencha: nome, telefone, CPF, e-mail, etc.
- **Fale:** "Vou cadastrar um novo cliente. Os dados são enviados ao controller, que salva no banco."
- Clique em **Salvar** → a lista atualiza mostrando o novo cliente.
- **(Diferencial — regra de negócio)** Tente cadastrar **outro cliente com o mesmo CPF**.
  - **Fale:** "O sistema valida e não permite CPF duplicado, exibindo uma mensagem de erro."
  - Mostre a mensagem de erro aparecendo.

### Cena 5 — Registrar uma venda (processamento + cálculo) (60s)
- Acesse **Vendas** (`/venda`).
- Selecione o **cliente** e um ou mais **serviços**.
- **Fale:** "Ao registrar uma venda, eu seleciono o cliente e os serviços. O sistema calcula
  automaticamente o valor total a partir dos preços dos serviços."
- Finalize a venda → mostre a confirmação de sucesso.

### Cena 6 — Perfil do cliente (saída / resultado) (25s)
- Volte para **Clientes** e abra o **perfil** do cliente da venda (`/clientes/perfil/{id}`).
- **Fale:** "No perfil do cliente, o sistema mostra o valor total já gasto por ele, somando
  todas as suas vendas. Esse é o fluxo completo: entrada, processamento e saída."

### Cena 7 — Agenda (40s)
- Acesse **Agenda** (`/agenda`).
- Adicione um compromisso (descrição, tipo, data, e opcionalmente vincule a um cliente).
- **Fale:** "Na agenda eu cadastro compromissos, como atendimentos e lembretes."
- Marque um item como **concluído** e/ou **exclua** um item, mostrando que funcionam.

### Cena 8 — (Opcional) Persistência no banco (20s)
- Acesse **http://localhost:8080/h2-console**
  - JDBC URL: `jdbc:h2:file:./data/salvouDB` · Usuário: `sa` · Senha: (em branco)
- Rode `SELECT * FROM CLIENTE;` e `SELECT * FROM VENDA;`
- **Fale:** "Os dados ficam persistidos no banco H2, então permanecem salvos entre as execuções."

### Cena 9 — Encerramento (10s)
- **Fale:** "Esse é o MVP do Salvou CRM, funcional, com os módulos de clientes, vendas e agenda,
  seguindo a arquitetura e a modelagem definidas nas sprints anteriores."

---

## 2. Checklist do que o vídeo precisa comprovar

- [ ] Aplicação iniciando / rodando no navegador
- [ ] Cadastro de cliente (entrada de dados)
- [ ] Validação de CPF duplicado (regra de negócio)
- [ ] Registro de venda com cálculo automático do total (processamento)
- [ ] Perfil do cliente com valor total gasto (saída)
- [ ] Agenda: criar / concluir / excluir compromisso
- [ ] (Opcional) Dados persistidos no banco H2

---

## 3. Dicas de gravação
- Use um gravador de tela simples (OBS Studio, Xbox Game Bar com `Win+G`, ou o gravador do Loom).
- Fale com calma; não precisa ser perfeito, precisa **mostrar funcionando**.
- Se errar, não precisa recomeçar tudo — pode pausar e continuar.
- Mantenha a janela do navegador grande e legível.
- Se for entregar por demonstração ao vivo em aula, use este mesmo roteiro como guia.
