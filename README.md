# Desafio Itaú Backend - API de Transações

Este projeto é uma implementação de uma **API REST desenvolvida com Java e Spring Boot** para o desafio técnico proposto pelo Itaú Unibanco.

A aplicação recebe, remove transações financeiras e calcula estatísticas sobre essas transações ocorridas nos **últimos 60 segundos**, mantendo todos os dados **exclusivamente em memória**.

O objetivo do projeto é demonstrar boas práticas de desenvolvimento backend, organização de código e construção de APIs REST.

---

# Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Maven
- Bean Validation (Jakarta Validation)

---

# Arquitetura do Projeto

A aplicação segue uma estrutura organizada em camadas:

- controllers
- services
- repositories
- dtos
- entities
- errors
- mappers

---

**Descrição das camadas:**

- **Controller** → Responsável por expor os endpoints REST.
- **Service** → Contém a lógica de negócio da aplicação.
- **Repository** → Armazena, remove e calcula as estatísticas das transações em memória.
- **DTO** → Objetos flexíveis utilizados para comunicação da API.
- **Entities** → Modelos internos da aplicação.
- **Errors** → Tratamento centralizado de erros da API.
- **Mappers** → Mapeadores de objetos DTO para entities e vice e versa.

---

# Requisitos do Desafio

A aplicação segue as seguintes restrições:

- Os dados são armazenados **somente em memória**
- Não é utilizado **banco de dados**
- A API aceita e responde **apenas JSON**
- Os endpoints seguem **exatamente o padrão solicitado no desafio**
- As estatísticas consideram apenas **transações dos últimos 60 segundos**

---

# Endpoints da API

## 1. Registrar Transação

### POST `/transacao`

Recebe uma nova transação.

### Payload

```json
{
  "valor": 123.45,
  "dataHora": "2026-01-07T19:04:00.789-03:00"
}
```

### Regras de validação

  A transação será aceita apenas se:

  - valor estiver presente
  - dataHora estiver presente
  - valor >= 0
  - dataHora não estiver no futuro

### Respostas
| Status                       | Significado                       |
| ---------------------------- | --------------------------------- |
| **201 Created**              | Transação registrada com sucesso  |
| **422 Unprocessable Entity** | Transação inválida                |
| **400 Bad Request**          | JSON inválido ou não compreendido |

## 2. Limpar Transações
### DELETE `/transacao`

Remove todas as transações armazenadas em memória.

### Respostas
| Status     | Significado                         |
| ---------- | ----------------------------------- |
| **200 OK** | Todas as transações foram removidas |


## 3. Estatísticas
### GET `/estatistica`

Retorna estatísticas das transações ocorridas nos últimos 60 segundos.

### Resposta

```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.45,
  "min": 12.34,
  "max": 456.78
}
```
| Campo | Descrição                |
| ----- | ------------------------ |
| count | Quantidade de transações |
| sum   | Soma total dos valores   |
| avg   | Média das transações     |
| min   | Menor valor              |
| max   | Maior valor              |

---

# Regras Importantes da Implementação

- Transações são armazenadas em memória usando uma estrutura de dados interna.

- O cálculo das estatísticas utiliza Java Streams e DoubleSummaryStatistics.

- Apenas transações ocorridas nos últimos 60 segundos são consideradas.

- Campos extras no JSON são rejeitados para manter um contrato de API rígido.

---

# Tratamento de Erros

A aplicação possui tratamento centralizado de exceções utilizando ControllerAdvice.

Erros tratados incluem:

  - JSON inválido
  - Campos obrigatórios ausentes
  - Campos extras no payload
  - Regras de validação da transação

---

## Extras (Implementações Futuras)

Algumas melhorias foram consideradas durante o desenvolvimento e podem ser implementadas futuramente:

### Testes Automatizados

  - Testes unitários com JUnit e Mockito
  - Testes de integração da API

### Containerização

  - Criação de Dockerfile
  - Execução da aplicação via Docker

### Observabilidade

  - Endpoint de healthcheck
  - Métricas da aplicação

### Documentação da API

  - Documentação utilizando OpenAPI / Swagger

### Configuração Dinâmica

  - Permitir configuração do tempo de cálculo das estatísticas:

    60 segundos (padrão);
    120 segundos;
    180 segundos

### Logs Estruturados

  - Implementação de logs para facilitar o monitoramento e diagnóstico da aplicação.
