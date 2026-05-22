# Sistema Universitário - Painel de Controle (Full Stack)

Este é um projeto prático de um sistema de cadastro e gerenciamento de alunos e professores. O objetivo principal foi construir uma aplicação ponta a ponta (Full Stack), aplicando conceitos de herança de entidades no backend para reaproveitamento de código e integrando tudo a uma interface visual para o usuário.

## 🛠️ O que foi implementado:
- **No Backend:** Criação de uma API REST que resolve o desafio de salvar dados compartilhados (usando uma classe base `Person` herdada por `Student` e `Professor`), persistindo tudo via Spring Data JPA.
- **No Frontend:** Uma SPA (Single Page Application) responsiva que consome essa API em tempo real, lidando com o estado das tabelas e formulários de cadastro.

---

## 🚀 Como Rodar o Aplicativo

### 1. Inicializar o Backend (IntelliJ)
- Abra o projeto no IntelliJ.
- Certifique-se de vincular o arquivo `pom.xml` como projeto Maven.
- Abra a classe `SistemaCrudApplication.java` e clique no botão de **Play verde** para iniciar o Spring Boot.
> O servidor backend ficará disponível em: http://localhost:8080

### 2. Inicializar o Frontend (Terminal)
Abra o terminal na raiz do projeto, acesse a pasta do front com o comando `cd sistema-frontend`, instale os pacotes com `npm install` e inicie o painel com `npm run dev`.
> A interface visual abrirá em: http://localhost:5173

---

## 🛠️ Detalhes Técnicos do Ecossistema

- **Backend (Java & Spring Boot):** Arquitetura aplicando herança de entidades (classe base `Person` compartilhada por `Student` e `Professor`), persistência com Spring Data JPA e banco de dados H2 rodando em memória para o desenvolvimento.
- **Frontend (Vue.js 3 & Vite):** Interface SPA (Single Page Application) integrada à API via Axios, com gerenciamento de estados para atualização das tabelas em tempo real e estilização responsiva usando Bootstrap.

---

## 👥 Desenvolvedor
- **Matheus Henrique Gonçalves** - Engenharia de Software / Análise e Desenvolvimento de Sistemas (ADS).