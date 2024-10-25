# Aplicação de Adoção de Animais
Esta é uma aplicação full-stack para gerenciar a adoção de animais. O projeto inclui um frontend (React + TypeScript), um backend (Spring Boot) e um banco de dados PostgreSQL, todos orquestrados usando Docker Compose.

## Funcionalidades
- Listar, criar, atualizar e deletar animais.
- Frontend desenvolvido com React e TypeScript.
- Backend com API RESTful em Spring Boot.
- Banco de dados PostgreSQL para armazenar dados dos animais.
- Documentação da API utilizando OpenAPI/Swagger.

## Tecnologias Utilizadas
- Frontend: React, TypeScript, Axios, React Router.
- Backend: Spring Boot, Java, JPA, API REST, OpenAPI.
- Banco de Dados: PostgreSQL.
- Containerização: Docker, Docker Compose.

## Pré-requisitos
Antes de rodar este projeto, certifique-se de ter as seguintes ferramentas instaladas:

- Docker
- Docker Compose

## Como Começar
### 1. Clonar o Repositório

```
git clone https://github.com/seuusuario/animal-adoption-app.git
cd animal-adoption-app
```

### 2. Configuração do Docker Compose

O projeto está configurado para rodar usando Docker Compose. O arquivo docker-compose.yml define os serviços do frontend, backend e o banco de dados PostgreSQL.

### 3. Executar a Aplicação

Para iniciar a aplicação (frontend, backend e banco de dados), execute o seguinte comando:

```
docker-compose up --build
```

### 4. Acessar a Aplicação

Após a execução do comando anterior, você pode acessar os seguintes serviços no navegador:

- Frontend: http://localhost:3000
- Backend API: http://localhost:8080/api
- Documentação da API (Swagger): http://localhost:8082/swagger-ui.html

### 5. Parar a Aplicação

Para parar a aplicação, pressione CTRL + C no terminal e, em seguida, execute o comando abaixo para parar e remover os containers:

```
docker-compose down
```

### 6. Acessar o Banco de Dados
O PostgreSQL estará rodando na porta 5432. Você pode acessar o banco de dados usando qualquer cliente PostgreSQL com as seguintes credenciais (definidas no arquivo docker-compose.yml):

```
   Host: localhost
   Porta: 5432
   Usuário: animaluser
   Senha: animalpass
   Banco de Dados: animaldb 
```

Caso necessário, você pode alterar essas configurações no arquivo docker-compose.yml.

## Endpoints da API
Aqui estão alguns dos principais endpoints da API:

- GET /animals: Recupera todos os animais.
- POST /animals: Cria um novo animal.
- PUT /animals/{id}: Atualiza um animal existente.
- DELETE /animals/{id}: Deleta um animal.
- GET /animals/{id}: Recupera um animal específico pelo ID.
- GET /animals/filter: Recupera uma página especificada de animais

## Executando Localmente (sem Docker)
Se preferir rodar a aplicação localmente sem usar Docker, siga os passos abaixo:

### Backend

1. Entre no diretório backend:
```
cd backend
```

2. Compile e execute a aplicação Spring Boot:
```
./mvnw clean install
./mvnw spring-boot:run
```

3. O backend estará disponível em http://localhost:8082.

### Frontend

1. Entre no diretório frontend:
```
cd frontend
```
2. Instale as dependências e inicie o app React:
```
npm install
npm start
```
3. O frontend estará disponível em http://localhost:3000.

## Estrutura do Projeto

```
animal-adoption-app/
├── backend/                 # Backend em Spring Boot
│   ├── src/main/java/...
│   ├── Dockerfile
│   └── pom.xml
├── frontend/                # Frontend em React e TypeScript
│   ├── src/
│   ├── public/
│   ├── Dockerfile
│   └── package.json
├── docker-compose.yml       # Configuração do Docker Compose
└── README.md                # Documentação do projeto
```