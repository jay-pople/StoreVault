# 🛒 StoreApp – Spring Boot E-commerce Backend

A secure and modular backend system built using **Java** and **Spring Boot**. This project offers REST APIs for user registration and login with **JWT-based authentication**, role-based access control, and clean DTO-based request/response structure.

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Postman (for API testing)

---

## ✅ Features

- 🔐 Secure user registration at `/signup`
- 🔑 Login at `/signin` returning a JWT token
- ⚙️ Stateless authentication using JWT
- 👥 Role-based access control (`OWNER` / `USER`)
- 📦 DTO usage for clean request/response handling
- 🚦 Centralized exception handling with proper HTTP status codes
- 📁 MVC layered architecture for maintainability

---

## 🚀 How to Run

### 1. Download the project
```bash
git clone https://github.com/jay-pople/StoreVault.git
cd StoreVault


2. Set up MySQL
Create a database:


CREATE DATABASE storedb;
Update src/main/resources/application.properties:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/storedb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=3600000
3. Run the application

mvn spring-boot:run
4. Test the APIs
Use Postman with the included collection (see below).

🔐 Authentication Flow
🔸 User Registration
http
Copy
Edit
POST /signup
Content-Type: application/json
json
Copy
Edit
{
  "username": "example",
  "password": "password"
}
🔸 User Login
POST /signin
Content-Type: application/json
json
Copy
Edit
{
  "username": "example",
  "password": "password"
}
🔁 Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
Use the token in protected routes:

Authorization: Bearer <token>
📫 Postman Collection
Include a file named:

StoreVault.postman_collection.json
This collection contains all API requests and testing steps.

📚 What I Learned
Implementing JWT token-based authentication with Spring Security

Designing secure REST APIs with layered architecture

Using DTOs for clean data transfer

Implementing role-based access control

Handling exceptions and formatting consistent responses

Debugging APIs using Postman

🛠️ Planned Features
This project is under active development. Upcoming features include:

📊 Real-time dashboard showing store updates for the owner at intervals

💰 Order transactions with actual flow

🔔 Live event notifications (e.g., new orders, stock alerts)

🙋‍♂️ Author
Jay Pople

💻 GitHub: jay-pople

🔗 LinkedIn: jay-pople-7b3095301

📄 License
This project is open-source and available under the MIT License.


