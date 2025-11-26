# 🚀 Nome do Projeto: MatriXpress

O MatriXpress é uma aplicação para resolver sistemas lineares usando eliminação de Gauss.

---

## 🛠️ Tecnologias Utilizadas

Este projeto é uma aplicação Full-Stack composta pelos seguintes elementos:

### **Backend (API)**
* **Java Spring Boot:** Framework para o desenvolvimento do servidor de API REST.

### **Frontend (Interface)**
* **React:** Biblioteca JavaScript para construção da interface de usuário.

---

## 💻 Como Executar o Projeto Localmente (Desenvolvimento)

Para rodar o projeto em sua máquina local, você deve iniciar o servidor backend e o aplicativo frontend separadamente.

### **Pré-requisitos**

* **JDK (Java Development Kit)**: Versão 25.
* **Node.js e npm/yarn**: Para executar o frontend React.

---

### **Passo 1: Iniciar o Backend (Spring Boot)**

O backend deve ser iniciado primeiro, pois o frontend tentará se conectar a ele.

1.  **Navegue** para a pasta do backend:
    ```bash
    cd backend
    ```
2.  **Compile e Execute** o projeto usando seu gerenciador de dependências:

    * **Com Maven:**
        ```bash
        ./mvnw clean install
        ./mvnw spring-boot:run
        ```
    * **Apenas executar o arquivo MatrixApplication.java:**

    O backend será iniciado e estará acessível em **`http://localhost:8080`**.

---

### **Passo 2: Iniciar o Frontend (React)**

1.  **Navegue** para a pasta do frontend:
    ```bash
    cd ../frontend
    ```
2.  **Instale as dependências** do Node:
    ```bash
    npm install  # ou yarn install
    ```
3.  **Inicie** o servidor de desenvolvimento:
    ```bash
    npm start  # ou yarn start
    ```

O frontend será aberto automaticamente no seu navegador, geralmente em **`http://localhost:3000`**.

---

## ⚙️ Configuração de Comunicação

Verifique se o seu aplicativo React está configurado para fazer requisições HTTP para o endereço correto da API:

* **URL da API:** A URL base para as chamadas do React deve ser configurada para **`http://localhost:8080`**.
    * *Dica:* Se você estiver usando um proxy no `package.json` do React, garanta que ele aponte para `http://localhost:8080`.

---

## 📂 Estrutura do Projeto

O repositório está organizado em duas subpastas principais:

* **`/backend`**: Contém o código da API em Java Spring Boot.
* **`/frontend`**: Contém o código da aplicação cliente em React.

---

## 🤝 Contribuição

Arthur Andrade Silva
Eduardo de Andrade do Bomfim Júnior 
Valentin Eduardo Carvalho Bispo dos Santos