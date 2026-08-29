# Employee API

API REST desenvolvida em Java com Spring Boot durante as aulas de Desenvolvimento de Web Services.

O projeto acompanha conceitos apresentados no tutorial da documentação do Spring e implementa operações básicas de gerenciamento de funcionários.

## Tecnologias

* Java 25
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* H2 Database
* Maven

## Funcionalidades

A API permite:

* Listar funcionários
* Buscar funcionário por ID
* Cadastrar funcionário
* Atualizar ou criar funcionário
* Excluir funcionário

## Endpoints

### Listar funcionários

`GET /employees`

Retorna todos os funcionários cadastrados.

### Buscar funcionário

`GET /employees/{id}`

Retorna um funcionário pelo seu ID.

### Cadastrar funcionário

`POST /employees`

Recebe um funcionário no corpo da requisição e salva no banco de dados.

### Atualizar ou criar funcionário

`PUT /employees/{id}`

Atualiza um funcionário existente ou cria um novo registro caso o ID não seja encontrado.

### Excluir funcionário

`DELETE /employee/{id}`

Remove um funcionário pelo ID.

## Banco de dados

O projeto utiliza o H2 Database como banco de dados em ambiente de desenvolvimento.

## Estrutura

```text
src/
├── main/
│   ├── java/
│   │   └── com.example.aula02/
│   │       ├── Employee.java
│   │       ├── EmployeeController.java
│   │       ├── EmployeeRepository.java
│   │       ├── EmployeeNotFoundAdvice.java
│   │       ├── EmployeeNotFoundException.java
│   │       ├── HomeController.java
│   │       └── LoadDatabase.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
```

## Objetivo

Projeto desenvolvido como prática de desenvolvimento de APIs REST utilizando Java e Spring Boot, com foco nos fundamentos de controllers, persistência de dados e tratamento de exceções.
