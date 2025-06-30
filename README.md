# 🌐 State Machine API

## Tópicos

1. [📖 Descrição](#-descrição)
2. [🚀 Funcionalidades](#-funcionalidades)
    - [🔄 Events](#-events)
    - [🔁 Transitions](#-transitions)
    - [📋 Statuses](#-statuses)
    - [🤖 State Machines](#️-state-machines)
3. [🛠️ Servidores](#️-servidores)
4. [📚 Componentes (Schemas)](#📚-componentes-schemas)

---

## 📖 Descrição

Esta é a **State Machine API**, definida em OpenAPI 3.0.1, que permite gerenciar máquinas de estado, seus status,
transições e eventos relacionados. Através dos endpoints desta API, você pode criar, consultar e alterar definições de
máquinas de estado de forma programática.

---

## 🚀 Funcionalidades

### 🔄 Events

- **GET** `/api/state-machines/{stateMachineId}/events`  
  Lista todos os eventos de uma máquina de estado.
    -
    Respostas: `200 OK (EventResponseDTO[])`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`, `500 Internal Server Error`

- **POST** `/api/state-machines/{stateMachineId}/events`  
  Cria um novo evento para a máquina de estado.
    -
    Respostas: `201 Created (EventResponseDTO)`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`, `500 Internal Server Error`

- **GET** `/api/state-machines/{stateMachineId}/events/{id}`  
  Obtém detalhes de um evento pelo seu ID.
    -
    Respostas: `200 OK (EventResponseDTO)`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`, `500 Internal Server Error`

- **PATCH** `/api/state-machines/{stateMachineId}/events/{id}`  
  Atualiza o status de um evento existente.
    - Body: `UpdateEventRequestDTO`
    -
    Respostas: `200 OK (EventResponseDTO)`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`, `500 Internal Server Error`

---

### 🔁 Transitions

- **GET** `/api/state-machines/{stateMachineId}/transitions`  
  Lista todas as transições configuradas para uma máquina de estado.
    -
    Respostas: `200 OK (TransitionResponseDTO[])`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`, `500 Internal Server Error`

---

### 📋 Statuses

- **GET** `/api/state-machines/{stateMachineId}/statuses`  
  Recupera todos os status disponíveis em uma máquina de estado.
    -
    Respostas: `200 OK (StatusResponseDTO[])`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`, `500 Internal Server Error`

---

### 🤖 State Machines

- **GET** `/api/state-machines`  
  Lista todas as máquinas de estado.
    -
    Respostas: `200 OK (StateMachineResponseDTO[])`, `400 Bad Request`, `401 Unauthorized`, `500 Internal Server Error`

- **GET** `/api/state-machines/{id}`  
  Obtém detalhes de uma máquina de estado pelo seu ID.
    -
    Respostas: `200 OK (StateMachineResponseDTO)`, `400 Bad Request`, `401 Unauthorized`, `500 Internal Server Error`, `401 Unauthorized`

- **DELETE** `/api/state-machines/{id}`  
  Desativa (disable) uma máquina de estado.
    - Respostas: `200 OK (StateMachineResponseDTO)`, `400 Bad Request`, `401 Unauthorized`, `500 Internal Server Error`

---

## 🖼️ Diagrama de Classes

![Diagrama de Classes](http://www.plantuml.com/plantuml/png/fPJ1JiCm38RlVGghbmtO1bmdD6qJ72h1XR4728qr8cNfLAUL4E9vU0AUO2z6sgHfEjQ1uD8sjcD__kDqJfhbkjZA2600AyqqmWDBNuH2Oqab8x9MPuGtOmQuMA-JMn3SBbOw5se3YcsnO-18QIvsMcJAsfTP9f4f4BH8jIXTzBqWVLCLAMW6LB-H0jTJpXI9AiiCjF-cWyqDuGmsg1TI3iyQbe3Uk9Ql2HywV1TIPe835yIU9OQkzyYhKb17Ze9wX0XgKR3eqQdL8r6cAIQF2EAwgUk4APCGiPN8OxQclw7MJL9Mv2bw0Lg7cHe35Zg-7TMlmEzALDeprwl_u0OyDbUQeudjWsYmwzYVY7tEpX1ruqPmPBAA7UzXyE4ZrpfMJoVF01vHilt7_jFSXax_yNKCup74LvF9UTpCuxIzEYU2WwcOTYwJo-B2UZROSRYqXt-gZErcQlkXA9gZujLFvWi0)

<!--
@startuml
  ' State Machine
  class StateMachine {
    +UUID id
    +String name
    +String description
    +Boolean isActive
    +List<Status> statuses
    +List<Transition> transitions
    +List<StateMachine> getAll()
    +StateMachine getById(UUID id)
    +void disable(UUID id)
  }

  ' Status
  class Status {
    +UUID id
    +StateMachine stateMachine
    +String name
    +Boolean isInitial
    +List<Status> getAll(UUID stateMachineId)
  }

  ' Transition
  class Transition {
    +UUID id
    +StateMachine stateMachine
    +Status sourceStatus
    +Status targetStatus
    +List<Transition> getAll(UUID stateMachineId)
  }

  ' Event
  class Event {
    +UUID id
    +StateMachine stateMachine
    +Status status
    +Event create(UUID stateMachineId)
    +List<Event> getAll(UUID stateMachineId)
    +Event getById(UUID stateMachineId, UUID id)
    +void update(UUID stateMachineId, UUID id, Event event)
  }

' Relações
StateMachine "1" -- "0..*" Status : statuses
StateMachine "1" -- "0..*" Transition : transitions
Status "0..*" -- "0..*" Transition : source/target
StateMachine "1" -- "0..*" Event : events

@enduml

-->

## 🛠️ Servidores

- **URL:** `http://localhost:8080`
- **Descrição:** Generated server url

---

## 📚 Componentes (Schemas)

- **ErrorDTO**
  ```json
  {
    "statusCode": "string",
    "errors": [ { "$ref": "#/components/schemas/ErrorResponseDTO" } ]
  }
  ```

- **ErrorResponseDTO**
  ```json
  {
    "code": "string",
    "message": "string"
  }
  ```

- **EventResponseDTO**
  ```json
  {
    "id": "uuid",
    "status": { "$ref": "#/components/schemas/StatusResponseDTO" }
  }
  ```

- **UpdateEventRequestDTO**
  ```json
  {
    "statusId": "uuid"
  }
  ```

- **StatusResponseDTO**
  ```json
  {
    "id": "uuid",
    "name": "string",
    "isInitial": true|false
  }
  ```

- **StateMachineResponseDTO**
  ```json
  {
    "id": "uuid",
    "name": "string",
    "description": "string",
    "isActive": true|false
  }
  ```

- **TransitionResponseDTO**
  ```json
  {
    "id": "uuid",
    "sourceStatus": { "$ref": "#/components/schemas/StatusResponseDTO" },
    "targetStatus": { "$ref": "#/components/schemas/StatusResponseDTO" }
  }
  ```

## 📚 Documentação do Swagger da API

Para acessar a documentação completa de como consumir a API RESTFull, inicie o projeto local e abra o seguinte link no
seu navegador:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Como Rodar Localmente com o IntelliJ

1. Abra o IntelliJ e importe o projeto.
2. Certifique-se de ter configurado um ambiente Java compatível (Java 17).
3. Execute a classe principal `StateMachineApiApplication`.
4. Aguarde até que o aplicativo seja iniciado e esteja pronto para aceitar solicitações.
5. Abra o seguinte link no seu
   navegador: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
