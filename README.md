# Online Education Platform

A simple online education platform with a Java Spring Boot backend and a Vue 3 frontend.

## Features

- Course catalog and course content management
- User registration and authentication
- Course enrollment and lesson progress tracking
- Comments and rating support
- Cart management for course checkout flows
- REST API backend with MyBatis Plus
- Vue 3 frontend using Axios and Element Plus UI components

## Tech Stack

- Backend: Java 11, Spring Boot, Spring Security, Spring AOP, MyBatis Plus
- Frontend: Vue 3, Vue Router, Axios, Element Plus
- Database: MySQL

## Repository Structure

- `backend/` — Spring Boot application, REST API, database entities, services, controllers
- `frontend/` — Vue.js application, page views, API client, components

## Prerequisites

- Java 11+
- Maven
- Node.js 16+ and npm
- MySQL

## Database Setup

1. Create the database:

```sql
CREATE DATABASE online_education DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Update backend database credentials in `backend/src/main/resources/application.properties` if needed.
3. Apply schema from `backend/src/main/resources/schema.sql`.

## Running the Backend

From the root of the repository:

```bash
cd backend
mvn clean spring-boot:run
```

The backend listens on `http://localhost:8080` by default.

## Running the Frontend

From the root of the repository:

```bash
cd frontend
npm install
npm run serve
```

The frontend runs on `http://localhost:8080` if using the default Vue CLI port, or if port 8080 is occupied it will choose the next available port. The backend CORS configuration allows requests from `http://localhost:5173`.

## Notes

- Default backend basic auth credentials are set in `backend/src/main/resources/application.properties`.
- The project uses `spring-security` for basic authentication and `BCryptPasswordEncoder` for password hashing.
- Mapper interfaces use MyBatis Plus and are annotated with `@Mapper`.

