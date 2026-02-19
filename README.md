# 🛒 Supermarket Management API

RESTful API developed with Spring Boot for managing a supermarket system.
The application handles products, branches and sales operations, following a clean layered architecture and backend best practices.

---

## 🚀 Technologies

- Java 8
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## 🏗 Architecture

The project follows a layered architecture:

- Controller → REST endpoints exposure
- Service → Business logic
- Repository → Data persistence layer
- Entity → JPA domain models
- DTO → Data transfer objects to decouple internal entities
- Exception Handling → Centralized error management

---

## 📦 Main Features

- Product management (CRUD operations)
- Branch management
- Sales registration
- Relationship mapping between entities
- Data persistence using JPA / Hibernate
- Structured API responses

---

## 🔎 Example Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /products | Get all products |
| POST   | /products | Create a new product |
| PUT    | /products/{id} | Update product |
| DELETE | /products/{id} | Delete product |

---

## ⚙️ How to Run

1. Clone the repository  
   `git clone https://github.com/your-user/supermarket-management-api.git`

2. Configure database credentials in `application.properties`

3. Run the application  
   `mvn spring-boot:run`

4. Access the API  
   `http://localhost:8080`

---

## 🎯 Project Purpose

This project was developed to practice and demonstrate backend architecture concepts using Spring Boot, REST API design, entity relationships and clean code structure.

---

## 🐳 Docker

Run the application using Docker:

docker-compose up --build

---

## 📬 Postman Collection

A Postman collection is included inside the /postman folder
to easily test the available endpoints.

## 👨‍💻 Author

Jonathan Martins  
Java Backend Developer  
Buenos Aires, Argentina
