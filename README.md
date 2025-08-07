# Gestão Bancária

Projeto desenvolvido como parte de um desafio técnico para vaga de desenvolvedor Java Backend.

## ✅ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.4
- PostgreSQL
- Maven
- Docker Compose

---

## 📋 Pré-requisitos

- Java 17+
- Maven 3.9+
- Docker e Docker Compose instalados

---

## 🚀 Como rodar o projeto localmente

### 1. Clonar o repositório

```bash
  git clone https://github.com/ArianaRusso/Desafio-ngbilling-gestao-bancaria.git
  cd gestao-bancaria
```

### 2. Subir o banco de dados com Docker Compose

```bash
  docker-compose up -d
```

> O `docker-compose.yml` cria um container PostgreSQL (`gestao-bancaria-db`) com:
> - Banco: `gestao_bancaria`
> - Usuário: `postgres`
> - Senha: `postgres`
> - Porta: `5432` (local) → `5432` (container)


### 3. Rodar a aplicação

Use sua IDE ou terminal:

```bash
  ./mvnw spring-boot:run
```

ou

```bash
  mvn spring-boot:run
```

---
## 📖 Documentação da API
A documentação da API está disponível no Swagger, acessível em:
```
http://localhost:8080/swagger-ui/index.html
```
## 📂 Estrutura do Projeto

```
gestao-bancaria/
├── src/
├── pom.xml
├── application.yml
└── docker-compose.yml
```

---

## 🛠️ Próximas melhorias

- [ ] Implementar versionamento do banco com Flyway ou Liquibase
- [ ] Adicionar testes de integração e automatizados
- [ ] Configurar aplicação para rodar no Docker junto com o banco

---


