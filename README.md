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

## 📚 Documentação da API (Swagger)

Após iniciar o projeto, acesse o Swagger em: http://localhost:8080/swagger-ui/index.html

Lá é possível testar todos os endpoints, enviar requisições e visualizar respostas.

---

## 🔗 Endpoints Principais
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

## 💪 Treinos

Base: /api/v1/treinos
| Método   | Endpoint               | Descrição         |
| -------- | ---------------------- | ----------------- |
| `POST`   | `/api/v1/treinos`      | Criar novo treino |
| `GET`    | `/api/v1/treinos`      | Listar treinos    |
| `PUT`    | `/api/v1/treinos/{id}` | Atualizar treino  |
| `DELETE` | `/api/v1/treinos/{id}` | Remover treino    |


## 📋 Planos

Base: /api/v1/planos
| Método   | Endpoint              | Descrição       |
| -------- | --------------------- | --------------- |
| `POST`   | `/api/v1/planos`      | Criar plano     |
| `GET`    | `/api/v1/planos`      | Listar planos   |
| `PUT`    | `/api/v1/planos/{id}` | Atualizar plano |
| `DELETE` | `/api/v1/planos/{id}` | Excluir plano   |

## 💰 Pagamentos

Base: /api/v1/pagamentos
| Método | Endpoint                             | Descrição                      |
| ------ | ------------------------------------ | ------------------------------ |
| `POST` | `/api/v1/pagamentos/{alunoId}`                 | Gerar pagamento                |
| `GET`  | `/api/v1/pagamentos/aluno/{alunoId}` | Listar pagamentos de um aluno  |
| `POST` | `/api/v1/pagamentos/atraso/{id}`     | Marcar pagamento como atrasado |

---

## 🧠 Estrutura de Relacionamentos

Um Aluno está vinculado a um Plano e um Treino.

Um Plano pode estar associado a vários Alunos.

Um Treino pode ter vários Alunos vinculados.

Cada Aluno pode gerar Pagamentos com base no plano ativo.

---

## 📦 Retornos HTTP

O sistema segue boas práticas de status HTTP:
| Código            | Situação                                   |
| ----------------- | ------------------------------------------ |
| `201 Created`     | Recurso criado com sucesso                 |
| `200 OK`          | Requisição bem-sucedida                    |
| `204 No Content`  | Recurso excluído ou atualizado sem retorno |
| `400 Bad Request` | Dados inválidos                            |
| `404 Not Found`   | Recurso não encontrado                     |
| `409 Conflict`    | Conflito (ex: CPF já cadastrado)           |

---

## 👨‍💻 Autor

Desenvolvido por Gabriel Gomes Rodrigues Cheim
📧 (gabrielgrcheim2@gmail.com)
💻 Projeto feito para estudos e prática com Spring Boot e H2 Database.
