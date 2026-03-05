# 🛒 Supermarket Management API

RESTful API developed with **Spring Boot** for managing supermarket operations.  
The application handles products, branches and sales while following a **clean layered backend architecture** and modern backend development practices.

This project demonstrates backend best practices including **REST API design, JPA entity relationships, Docker integration and environment-based configuration**.

---

# 🚀 Technologies

- Java 8
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Docker
- Postman

---

# 📋 Requirements

To run the project you will need:

- Java 8+
- Maven
- MySQL
- Docker (optional but recommended)

---

# 🏗 Architecture

The project follows a layered backend architecture:

Controller → Handles HTTP requests and exposes REST endpoints  
Service → Contains business logic  
Repository → Handles database persistence using Spring Data JPA  
Entity → Domain models mapped with JPA annotations  
DTO → Data transfer objects to decouple internal entities  
Exception Handling → Centralized error management

---

# 📦 Main Features

- Product management (CRUD operations)
- Branch management
- Sales registration
- Entity relationship mapping with JPA
- Environment-based configuration
- Docker integration
- Structured API responses

---

# 🔎 Example Endpoints

| Method | Endpoint | Description |
|------|------|------|
| GET | /products | Get all products |
| POST | /products | Create a new product |
| PUT | /products/{id} | Update product |
| DELETE | /products/{id} | Delete product |

---

# 📂 Project Structure
src
├── controller
├── service
├── repository
├── model
├── dto
└── SupermarketApiApplication


---

# 🐳 Run with Docker (Recommended)

## 1️⃣ Clone the repository

```bash
git clone https://github.com/JLeonelMartins/supermarket-management-api.git
cd supermarket-management-api

## 2️⃣ Run the application

docker-compose up --build

## 3️⃣ Access the API
http://localhost:8080

---

# 💻 Run Locally (Without Docker)

## 1️⃣ Create MySQL database

CREATE DATABASE supermarketapi;

## 2️⃣ Configure environment variables

DB_URL=jdbc:mysql://localhost:3306/supermarketapi?useSSL=false&serverTimezone=UTC
DB_USER_NAME=root
DB_PASSWORD=your_password

## 3️⃣ Run the application
http://localhost:8080

---

# 🧪 API Testing
A Postman collection is included in the repository to test the API endpoints.
postman/supermarket-api.postman_collection.json
Import this collection into Postman to quickly test the endpoints.

---

🎯 Project Purpose

This project was developed to practice and demonstrate backend architecture concepts using Spring Boot, REST API design, entity relationships and clean code structure.

---

# 👨‍💻 Author

Jonathan Leonel Martins

docker-compose up --build

---

## 📬 Postman Collection

A Postman collection is included inside the /postman folder
to easily test the available endpoints.

## 🐳 Run with Docker (Recommended)

1. Install Docker
2. Run:
   docker-compose up --build
3. Access the API at:
   http://localhost:8080

## 💻 Run Locally without Docker

1. Create MySQL database:
   CREATE DATABASE supermarketapi;
2. Set environment variables:
   DB_URL=...
   DB_USER_NAME=...
   DB_PASSWORD=...
3. Run:
   mvn spring-boot:run

## 👨‍💻 Author

Jonathan Martins  
Java Backend Developer  
Buenos Aires, Argentina

LinkedIn https://www.linkedin.com/in/jonathan-leonel-martins-530309193/