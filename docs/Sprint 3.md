
# Sprint 3 - Definição da Arquitetura do Sistema

<span style="color:red">Pré-requisitos: <a href="Sprint 2.md"> Sprint 2 - Modelagem de Classes e Relacionamentos</a></span>


## Descrição da Arquitetura
1.1
Descrição e Justificativa da Arquitetura
O sistema Salvou adota a Arquitetura em Camadas (Layered Architecture) aco-
plada ao padrão de inversão de controle fornecido pelo ecossistema Spring Boot. A se-
paração clara entre as regras de representação visual, lógica de negócios e persistência de
dados garante que o sistema seja altamente coeso e fracamente acoplado.
1.1.1
Justificativas Técnicas
• Abstração da Camada de Dados (JPA/Hibernate): Toda a comunicação com
o banco de dados relacional é feita via mapeamento objeto-relacional (ORM). Isso
significa que as regras de persistência se apoiam em interfaces orientadas a objetos,
isolando a aplicação do dialeto SQL nativo.
• Independência de Camadas: Alterações na estrutura visual dos arquivos HTML
localizados na pasta templates não impactam as entidades de negócio (Cliente)
ou as consultas de persistência (ClienteRepository).

## Diagrama ou Representação da Estrutura

1.2
Organização do Sistema e Responsabilidades
Tabela 1.1: Responsabilidade Primária por Componente
Camada / Componente
Responsabilidade Primária
Model / Entity (Cliente.java)
Mapear os atributos relacionais e regras intrı́nsecas ao do
Repository (ClienteRepository.java) Abstrair operações de persistência e CRUD via JPA.
Controller (ClienteController.java) Interceptar rotas HTTP e gerenciar o fluxo de dados do

## Explicação da Comunicação Entre os Componentes

1.3 Comunicação entre Componentes e Padrão REST
1.3.1
Comunicação Interna (Injeção de Dependência)
As camadas se comunicam através de desacoplamento gerenciado pelo Spring Contai-
ner. Utilizando a anotação @Autowired, os controladores obtêm instâncias ativas dos
repositórios sem a necessidade de acoplamento rı́gido via operador new.
1.3.2
Comunicação Externa (Hı́brida: MVC e REST)
O sistema opera utilizando requisições sı́ncronas para carregar as telas através do Thyme-
leaf e, concorrentemente, expõe rotas assı́ncronas baseadas nas anotações @ResponseBody
e @RequestBody para comunicação direta via objetos textuais estruturados em JSON
(padrão REST).

