# Task-Manager

A full-stack task management application built with **Spring Boot** (backend) and **Angular** (frontend), enabling users to efficiently create, organize, and manage their tasks with secure authentication and a responsive web interface.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Development Workflow](#development-workflow)
- [Testing](#testing)
- [Building for Production](#building-for-production)

## ✨ Features

- **User Authentication & Authorization**: Secure JWT-based authentication system
- **Task Management**: Create, read, update, and delete tasks
- **User Registration & Login**: Easy-to-use authentication system
- **Responsive UI**: Modern, responsive Angular frontend
- **Data Persistence**: PostgreSQL database for reliable data storage
- **Security**: Spring Security integration with validation
- **RESTful API**: Clean, well-structured REST API endpoints

## 🛠️ Tech Stack

### Backend
- **Framework**: Spring Boot 4.1.0
- **Language**: Java 21
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Tokens) with JJWT 0.12.6
- **ORM**: Spring Data JPA
- **Security**: Spring Security
- **Validation**: Spring Validation

### Frontend
- **Framework**: Angular 21.2.0
- **Language**: TypeScript 5.9.2
- **Build Tool**: Angular CLI 21.2.19
- **Package Manager**: npm 10.9.3
- **Testing**: Vitest 4.0.8
- **Code Formatting**: Prettier 3.8.1
- **Routing**: Angular Router


## 📁 Project Structure

```
Task-Manager/
├── backend/                              # Spring Boot backend
│   ├── src/main/java/com/project/TaskManager/
│   │   ├── controller/                   # REST API controllers
│   │   ├── service/                      # Business logic layer
│   │   ├── entity/                       # JPA entity models
│   │   ├── dto/                          # Data Transfer Objects
│   │   ├── repo/                         # Repository interfaces (Data Access Layer)
│   │   ├── security/                     # JWT and security configuration
│   │   ├── enums/                        # Enumeration types
│   │   ├── exceptions/                   # Custom exception classes
│   │   └── TaskManagerApplication.java   # Spring Boot entry point
│   ├── src/main/resources/
│   │   └── application.properties        # Backend configuration
│   ├── src/test/                         # Unit and integration tests
│   ├── pom.xml                           # Maven dependencies
│   └── mvnw / mvnw.cmd                   # Maven wrapper
│
├── frontend/                             # Angular frontend
│   ├── src/app/
│   │   ├── login/                        # Login component
│   │   ├── register/                     # User registration component
│   │   ├── tasks/                        # Tasks display component
│   │   ├── taskform/                     # Task creation/edit form component
│   │   ├── navbar/                       # Navigation bar component
│   │   ├── footer/                       # Footer component
│   │   ├── service/                      # Angular services for API calls
│   │   ├── app.routes.ts                 # Application routing configuration
│   │   ├── app.ts                        # Root component
│   │   ├── app.config.ts                 # Angular configuration
│   │   ├── app.spec.ts                   # Component tests
│   │   ├── app.html                      # Root template
│   │   └── app.css                       # Root styles
│   ├── src/
│   │   ├── index.html                    # HTML entry point
│   │   ├── main.ts                       # TypeScript entry point
│   │   └── styles.css                    # Global styles
│   ├── angular.json                      # Angular CLI configuration
│   ├── package.json                      # npm dependencies
│   ├── tsconfig.json                     # TypeScript configuration
│   └── karma.conf.js                     # Test runner configuration
│
└── README.md                             # This file
```

### Architecture Overview

The application follows a **client-server architecture**:

1. **Frontend (Angular)**: Users interact with the responsive web interface to register, login, view, create, edit, and delete tasks. The Angular application communicates with the backend via REST API calls through dedicated services.

2. **Backend (Spring Boot)**: Receives HTTP requests from the frontend, processes business logic, validates input data, manages authentication/authorization via JWT tokens, and persists data to PostgreSQL.

3. **Database (PostgreSQL)**: Stores user credentials, task data, and application state with full ACID compliance.

4. **Routing**: Angular Router handles client-side navigation between login, register, and task management pages. The backend provides RESTful endpoints for all data operations.

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** (or use the included Maven wrapper)
- **Node.js 18+** and **npm 10.9.3+** ([Download](https://nodejs.org/))
- **PostgreSQL 12+** ([Download](https://www.postgresql.org/download/)) - database server must be running
- **Angular CLI**: `npm install -g @angular/cli`

## 🚀 Getting Started

### Backend Setup

1. **Navigate to the backend directory**:
   ```bash
   cd backend
   ```

2. **Create PostgreSQL Database**:
   ```sql
   CREATE DATABASE taskmanager;
   ```

3. **Configure the database connection** in `backend/src/main/resources/application.properties`:
   ```properties
   ## PostgreSQL Connection Configuration
    spring.datasource.url=jdbc:postgresql://localhost:<db_port>/<insert_db_name_here>
    spring.datasource.username=<username>
    spring.datasource.password=<password>

    spring.jpa.hibernate.ddl-auto=update

    ## JWT Secret String
    secretJwtString=<minumum_32_characters_string_input_here>
   ```

4. **Build the project**:
   ```bash
   ./mvnw clean build
   ```
   
   Or on Windows:
   ```bash
   mvnw.cmd clean build
   ```

### Frontend Setup

1. **Navigate to the frontend directory**:
   ```bash
   cd frontend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Configure API endpoint** (if needed) in your Angular service to point to your backend. Update the service file with:
   ```typescript
   // Example: src/app/service/api.ts
   private API_URL = "http://localhost:8080/api";
   ```

## ▶️ Running the Application

### Running the Backend

From the `backend` directory:

```bash
./mvnw spring-boot:run
```

Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

The backend will start on `http://localhost:8080`

### Running the Frontend

From the `frontend` directory:

```bash
npm start
```

Or using Angular CLI directly:
```bash
ng serve
```

The frontend development server will start on `http://localhost:4200`

### Access the Application

Open your browser and navigate to:
```
http://localhost:4200
```

You'll be redirected to the tasks page (or login page if not authenticated). Create an account or log in to get started!

## 🔌 API Endpoints

The backend provides the following REST API endpoints:

### Authentication Endpoints
```
POST   /api/auth/register          - Register a new user
POST   /api/auth/login             - Login user (returns JWT token)
POST   /api/auth/logout            - Logout user
```

### Task Management Endpoints
```
GET    /api/tasks                  - Get all tasks for authenticated user
POST   /api/tasks                  - Create a new task
GET    /api/tasks/{id}             - Get a specific task by ID
PUT    /api/tasks/{id}             - Update an existing task
DELETE /api/tasks/{id}             - Delete a task
```

### Authentication

All protected endpoints require a valid JWT token in the request header:

```
Authorization: Bearer <your_jwt_token>
```

**Example Request**:
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
     http://localhost:8080/api/tasks
```

### Request/Response Examples

**User Registration**:
```json
POST /api/auth/register
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePassword123"
}
```

**User Login**:
```json
POST /api/auth/login
{
  "username": "john_doe",
  "password": "SecurePassword123"
}

Response (200 OK):
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "john_doe"
}
```

**Create Task**:
```json
POST /api/tasks
{
  "title": "Complete project documentation",
  "description": "Write comprehensive documentation for the Task-Manager project",
  "dueDate": "2024-08-31",
  "priority": "HIGH",
  "status": "IN_PROGRESS"
}
```

**Get All Tasks**:
```json
GET /api/tasks

Response (200 OK):
[
  {
    "id": 1,
    "title": "Complete project documentation",
    "description": "Write comprehensive documentation for the Task-Manager project",
    "dueDate": "2024-08-31",
    "priority": "HIGH",
    "status": "IN_PROGRESS",
    "createdAt": "2024-07-18T10:30:00",
    "updatedAt": "2024-07-18T14:45:00"
  }
]
```

## 🔨 Development Workflow

### Backend Development

- **Modify Java files** in `backend/src/main/java/com/project/TaskManager/`
- **Key directories**:
  - `controller/` - REST endpoints
  - `service/` - Business logic
  - `entity/` - Database models
  - `dto/` - Data transfer objects
  - `repo/` - JPA repositories
  - `security/` - JWT and security configs
  - `exceptions/` - Custom exceptions

- **Changes**: Modify your Java files and rebuild with `./mvnw clean build`
- **Run tests**:
  ```bash
  cd backend
  ./mvnw test
  ```

### Frontend Development

- **Modify TypeScript/HTML/CSS** in `frontend/src/app/`
- **Component structure**:
  - `login/` - Authentication component
  - `register/` - User registration component
  - `tasks/` - Display all tasks
  - `taskform/` - Create/edit tasks
  - `navbar/` - Navigation component
  - `service/` - API communication services

- **Live reload**: The development server automatically reloads on file changes
- **Code formatting**: Run Prettier to format your code:
  ```bash
  cd frontend
  npx prettier --write "src/**/*.{ts,html,css}"
  ```

### IDE Recommendations

- **Backend**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions
- **Frontend**: Visual Studio Code with Angular extensions
- **Both**: Install Prettier and ESLint extensions for automatic code formatting

## 🧪 Testing

### Backend Tests

Run all unit and integration tests:
```bash
cd backend
./mvnw test
```

Run tests with coverage report:
```bash
cd backend
./mvnw test jacoco:report
```

### Frontend Tests

Run unit tests with Vitest:
```bash
cd frontend
npm test
```

Run tests with coverage:
```bash
cd frontend
npm test -- --coverage
```

Watch mode for test development:
```bash
cd frontend
npm test -- --watch
```

## 📦 Building for Production

### Backend

Build the backend JAR artifact:
```bash
cd backend
./mvnw clean package
```

This generates a JAR file in `target/TaskManager-0.0.1-SNAPSHOT.jar` that can be deployed to any environment with Java 21 installed.

Run the JAR:
```bash
java -jar target/TaskManager-0.0.1-SNAPSHOT.jar
```

### Frontend

Build the frontend for production:
```bash
cd frontend
npm run build
```

This generates optimized production files in the `dist/` directory.

### Docker (Optional)

You can containerize both applications using Docker:

**Backend Dockerfile**:
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/TaskManager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**Frontend Dockerfile**:
```dockerfile
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```