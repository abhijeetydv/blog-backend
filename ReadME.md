# Blog Platform Backend

A RESTful API built with Spring Boot for a blog platform featuring JWT authentication and MySQL database.

## Features
- User authentication with JWT
- CRUD operations for blog posts
- Role-based access control
- MySQL database integration
- CORS configuration for frontend integration

## Technologies
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- MySQL
- Maven

## Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Setup Instructions

1. Clone the repository
```bash
git clone https://github.com/yourusername/blog-backend.git
cd blog-backend
```

2. Configure MySQL database

- Create a database named `blogdb`
- Update `src/main/resources/application.properties` with your MySQL credentials

3. Run the application
```bash
./mvnw spring-boot:run
```
The API will be available at `http://localhost:8080`

## API Endpoints

### Authentication

- `POST /api/auth/signin` - Login
- `POST /api/auth/signup` - Register new user

### Blog Posts

- `GET /api/posts` - Get all posts (public)
- `GET /api/posts/{id}` - Get post by ID (public)
- `POST /api/posts` - Create new post (requires authentication)
- `PUT /api/posts/{id}` - Update post (requires authentication)
- `DELETE /api/posts/{id}` - Delete post (requires authentication)

### Default Configuration
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.jpa.hibernate.ddl-auto=update
```

