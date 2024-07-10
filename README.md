# Design Patterns Spring

Este projeto demonstra a implementação de padrões de design em uma API REST usando Spring Boot.

## Descrição

A aplicação exemplifica o uso dos seguintes padrões de design:

- **Singleton**: Injeção de componentes Spring com `@Autowired`.
- **Strategy**: Implementação de métodos definidos na interface.
- **Facade**: Abstração da complexidade de integrações com subsistemas em uma interface REST simples e coesa.

A aplicação integra um banco de dados H2 e a API do ViaCEP para busca de endereços.


## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring Cloud OpenFeign
- Springdoc OpenAPI
- Banco de Dados H2

## Configuração do Projeto

### Requisitos

- Java 17
- Maven

### Dependências Principais

- `spring-boot-starter-data-jpa`
- `spring-boot-starter-web`
- `spring-cloud-starter-openfeign`
- `springdoc-openapi-starter-webmvc-ui`
- `com.h2database:h2`

## Estrutura do Projeto

### Controllers

- `ClientRestController`: Implementa endpoints para operações CRUD de clientes, utilizando a fachada `ClientService`.

### Models

- `Client`: Entidade que representa um cliente.
- `Address`: Entidade que representa um endereço.

### Repositories

- `ClientRepository`: Interface para operações CRUD na entidade `Client`.
- `AddressRepository`: Interface para operações CRUD na entidade `Address`.

### Services

- `ClientService`: Interface que define as operações de negócio para clientes.
- `ClientServiceImpl`: Implementação da `ClientService`, utilizando os repositórios e a API do ViaCEP.
- `ViaCepService`: Interface para consumo da API ViaCEP utilizando OpenFeign.

## Como Executar

1. Clone o repositório:
   ```sh
   git clone https://github.com/HeloisaFelizardo/design-patterns-spring.git
   ```

2. Navegue até o diretório do projeto:
   ```sh
   cd design-patterns-spring
   ```

3. Execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

4. Acesse a aplicação:
    - API REST: `http://localhost:8080/clients`
    - Documentação Swagger: `http://localhost:8080/swagger-ui.html`

## Endpoints Principais

- `GET /clients`: Retorna todos os clientes.
- `GET /clients/{id}`: Retorna um cliente pelo ID.
- `POST /clients`: Adiciona um novo cliente.
- `PUT /clients/{id}`: Atualiza um cliente existente.
- `DELETE /clients/{id}`: Deleta um cliente pelo ID.

