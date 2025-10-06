# Prova-Api-Gestao-De-Academia

# 🏋️‍♂️ Projeto Gestao de Academia

## 📖 Descrição

O **Projeto Gestao de Academia** é um sistema desenvolvido em **Spring Boot** que permite o **cadastro e gerenciamento de alunos, treinos, planos e pagamentos**.  
O sistema realiza o relacionamento entre essas entidades.

---

## ⚙️ Tecnologias Utilizadas

- ☕ **Java 21**  
- 🌱 **Spring Boot 3.5.6**  
- 🧩 **Spring Data JPA**  
- 🗄️ **H2 Database (modo arquivo)**  
- 🧰 **Maven**  
- 📘 **Swagger / Springdoc OpenAPI**  
- 💾 **JPA / Hibernate**

---

## 🚀 Como Executar o Projeto

### ▶️ Executar via VSCode
1. Certifique-se de ter o **Java 21** e o **Maven** instalados.  
2. Abra o projeto no **VSCode**.  
3. Vá até a classe principal ( ProjetoAcademiaApplication.java).  
4. Clique em **“Run”** para iniciar o servidor.  
5. O projeto será iniciado em: http://localhost:8080

---

## 🗃️ Banco de Dados H2
Uso do H2 em modo de arquivo:
local do arquivo: file:./data/meubanco

Acesso ao console H2:
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/meubanco
User: sa
Password: (vazio)

---

##📚 Documentação da API (Swagger)

Após iniciar o projeto, acesse o Swagger em: http://localhost:8080/swagger-ui/index.html

Lá é possível testar todos os endpoints, enviar requisições e visualizar respostas.

---

##🔗 Endpoints Principais
👨‍🎓 Alunos

Base: /api/v1/alunos
| Método  | Endpoint                         | Descrição                  |
| ------- | -------------------------------- | -------------------------- |
| `POST`  | `/api/v1/alunos`                 | Cadastrar um novo aluno    |
| `GET`   | `/api/v1/alunos`                 | Listar todos os alunos     |
| `GET`   | `/api/v1/alunos/{id}`            | Buscar aluno por ID        |
| `PATCH` | `/api/v1/alunos/{id}/inativar`   | Inativar aluno             |
| `PATCH` | `/api/v1/alunos/{id}/ativar`     | Reativar aluno             |
| `POST`  | `/api/v1/alunos/vincular-treino` | Vincular aluno a um treino |
