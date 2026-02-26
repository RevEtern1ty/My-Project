# Task Manager

A Spring Boot application for managing Employees, Tasks, Task Logs and Workstations.  
The project demonstrates a classic **Controller → Service → Repository** architecture with DTO-based API, validation, error handling and unit tests.

---

## Table of Contents

1. [Description](#1-description)
2. [Tech Stack](#2-tech-stack)
3. [Prerequisites](#3-prerequisites)
4. [Getting Started](#4-getting-started)
5. [Database Initialization](#5-database-initialization)
6. [Application Architecture](#6-application-architecture)
7. [Error Handling](#7-error-handling)
8. [Testing](#8-testing)

![ER Diagram](docs/ER-diagramm.jpg)

---

## 1. Description

This application implements basic CRUD operations for a simple task management system.

Main domain objects:

- **Employee** – company employees
- **Workstation** – workplaces where tasks are executed
- **Task** – work items assigned to employees and workstations
- **TaskLog** – history of task-related events 

The main goals of the project are:

- demonstrate clean backend architecture
- separate API layer from persistence
- use DTOs instead of exposing entities
- handle errors in a centralized way
- cover business logic with unit tests

---

## 2. Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- HSQLDB 
- MapStruct 
- Lombok
- JUnit 5
- Mockito
- Gradle

---

## 3. Prerequisites

To run the project you need:

- Java JDK 21 installed
- Gradle 
- IntelliJ IDEA is recommended but not required

---

## 4. Getting Started

### Clone the repository

- Open the IDE
- On the Welcome screen (or via **File → New → Project from Version Control**), choose **Clone Repository**
- Select **Repository URL**
- Make sure **Version control** is set to `Git`
- Paste the repository URL into the **URL** field

```bash
git clone https://github.com/RevEtern1ty/My-Project.git
cd task-manager
```
Run the application:
Using Gradle:

```bash
./gradlew bootRun
```

Or from IntelliJ IDEA:
Open the project
Run MyprojectApplication

- The application will start on:
 http://localhost:8080
-Swagger UI:
 http://localhost:8080/swagger-ui/index.html

---

## 5. Database Initialization

The project uses HSQLDB as an in-memory database.
Database initialization is handled automatically on startup using:

- schema.sql – creates database tables
- data.sql – inserts initial test data


### Database Model (ER Diagram)

The ER diagram was designed in Vertabelo.

![ER Diagram](docs/ER-diagramm.jpg)

- Both files are located in:

- src/main/resources/
- The database is reset every time the application restarts.

---

## 6. Application Architecture

The project follows a layered architecture:

### Controller layer

Responsibilities:
- Handles HTTP requests
- Performs input validation using @Valid
- Works only with DTOs
- Delegates all business logic to Services

### Service layer

Responsibilities:
- Contains all business logic
- Validates domain rules
- Resolves entity relations
- Throws domain-specific exceptions

### Repository layer

Responsibilities:
- Uses Spring Data JPA
- Works only with entities
- No business logic inside repositories

### DTO and Mapping

Responsibilities:
- API uses DTOs for request/response contracts
- MapStruct is used for entity ↔ DTO mapping
- Patch updates ignore null values
- Relations are resolved explicitly in services

---

## 7. Error Handling

Centralized error handling is implemented using @ControllerAdvice.
Typical handled cases:

- 404 Not Found – entity does not exist
- 400 Bad Request – validation or business rule violations
- 403 Forbidden – forbidden operations

Typical handled cases:
- Error responses are returned in a unified JSON format using ApiError.
- To avoid duplicated error messages across services, the project uses a centralized ErrorCode enum with message templates.

---

## 8. Testing

Unit tests are implemented for the Service layer.

### Covered scenarios:

- Entity not found cases
- Successful create operations
- Partial updates 
- Relation resolution 
- Delete operations
- Validation of business rules 

### Testing tools:

- JUnit 5,Mockito
- Repositories and mappers are mocked to focus tests on business logic only.

Run tests:
```bash
 ./gradlew test
```

On Windows PowerShell you can also run:

```bash
.\gradlew.bat test
```