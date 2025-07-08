# Task Management System

## Introduction

This is a project I'm using to relearn Java programming, and it's also my first hands-on experience with JWT token authentication. Instead of saving time by having AI generate code for me, I'm now using AI to help me learn and code myself.

## Technologies Used

- **Java 23**
- **Spring Boot 3**
- **Spring Security**
- **JWT Authentication**
- **Spring Data JPA**
- **MySQL**
- **Lombok**

## Project Description

This project is a simple Task Management system with the following features:

- User registration/login with JWT authentication
- Create, view, edit, and delete tasks
- Authorization: each user can only view and manage their own tasks
- Track task status: Pending, In Progress, Completed

## Learning Journey

Throughout the development of this project, I've learned several important concepts:

1. **JWT Authentication**: Understanding how JWT works, how to create and validate tokens
2. **Spring Security**: Configuring security for Spring Boot applications
3. **REST API**: Designing and implementing APIs according to REST standards
4. **JPA/Hibernate**: Working with databases through ORM
5. **Layered Architecture**: Organizing code using the Controller-Service-Repository pattern

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/TaskManagement/
│   │       ├── config/         # JWT and Security configuration
│   │       ├── controller/     # REST controllers
│   │       ├── dto/            # Data Transfer Objects
│   │       ├── entity/         # JPA entities
│   │       ├── enums/          # Enumerations
│   │       ├── repository/     # Spring Data repositories
│   │       └── service/        # Business logic
│   └── resources/
│       └── application.properties
```

## Conclusion

This project marks a transformation in my approach to learning programming. Rather than relying on AI to generate code quickly, I've focused on truly understanding the code and writing each line myself. AI has become a guide rather than a replacement.

I believe this learning approach will help me become a better developer with solid knowledge and effective problem-solving skills.
