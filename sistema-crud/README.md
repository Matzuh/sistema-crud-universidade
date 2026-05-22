# Sistema CRUD - Gestão de Universidade

Este é um projeto de API básica para o gerenciamento de alunos e professores de uma universidade. O objetivo principal foi criar um sistema funcional de cadastro, consulta, atualização e exclusão de dados (CRUD).

O projeto foi dividido em camadas simples (Controller, Service e Repository) para manter o código organizado e fácil de mexer.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 4.0.6
* **Banco de Dados:** H2
* **Ferramentas:** JPA / Hibernate e Maven
* **Testes:** JUnit 5 e Mockito
* **Container:** Docker

---

## 🚀 Como Rodar o Projeto no Computador

### Pré-requisitos
* Ter o Java 17 instalado.

### Passo a passo:
1. Baixe ou clone este repositório.
2. Abra o terminal na pasta do projeto e use o comando abaixo para compilar:
   ```bash
   ./mvnw clean package
Para ligar o sistema, execute:

Bash
java -jar target/*.jar
A API vai rodar no endereço: http://localhost:8080

🐳 Como Rodar com Docker
Se você tiver o Docker instalado e quiser testar o projeto no container:

Na pasta do projeto, crie a imagem:

Bash
docker build -t sistema-crud .
Rode o container com o comando:

Bash
docker run -p 8080:8080 sistema-crud
🧪 Como Rodar os Testes Unitários
Para rodar os testes de salvar e buscar alunos que foram criados para validar o código:

Bash
./mvnw test
🗄️ Como Olhar o Banco de Dados (H2 Console)
Com o sistema rodando, você pode ver as tabelas e os dados direto no navegador:

Link: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: (deixar em branco)

📝 Rotas da API (Endpoints)
Alunos
POST /api/students - Cadastra um aluno.

GET /api/students - Lista os alunos (traz de 5 em 5 por ordem alfabética).

PUT /api/students/{id} - Atualiza os dados de um aluno.

DELETE /api/students/{id} - Apaga um aluno (e apaga o endereço junto).

Professores
POST /api/professors - Cadastra um professor.