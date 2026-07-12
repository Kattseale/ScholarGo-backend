# ScholarGo-backend

## Overview

ScholarGo is a Spring Boot REST API that connects parents with trusted school transport providers across South Africa.

This backend currently provides the Authentication and User Management module, allowing users to register, log in securely using JWT authentication, and manage their profiles.

---

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- MySQL
- JWT Authentication
- BCrypt Password Encryption
- Maven
- Lombok

---

## Features Implemented

### Authentication

- User Registration
- User Login
- JWT Token Generation
- JWT Authentication Filter
- Spring Security Configuration
- BCrypt Password Encryption

### User Management

- Get Current Logged-in User
- Update User Profile

### Validation & Exception Handling

- Bean Validation
- Global Exception Handling
- Standard API Response Wrapper

---

## API Endpoints

### Authentication

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| POST | `/api/auth/register` | Register a new user | Public |
| POST | `/api/auth/login` | Login and receive JWT token | Public |

### User Management

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| GET | `/api/users/me` | Get currently logged-in user | Bearer Token |
| PUT | `/api/users/{id}` | Update user profile | Bearer Token |

---

## Authentication

All protected endpoints require a JWT token.

Example Authorization header:

```text
Authorization: Bearer <your_jwt_token>
```

---

## Sample Register Request

```json
{
  "firstName": "Sandile",
  "lastName": "Zulu",
  "email": "sandile@gmail.com",
  "phoneNumber": "0688284599",
  "password": "Password123",
  "role": "PARENT"
}
```

---

## Sample Login Request

```json
{
  "email": "sandile@gmail.com",
  "password": "Password123"
}
```

---

## Sample Login Response

```json
{
  "success": true,
  "message": "Login successful.",
  "data": {
    "token": "JWT_TOKEN_HERE"
  }
}
```

---

## Project Structure

```
src
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── security
├── service
│   └── impl
└── wrapper
```

---

## Security

- Spring Security
- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authentication
  - ADMIN
  - PARENT
  - DRIVER

---

## Current Status

### Completed

- User Registration
- User Login
- JWT Authentication
- JWT Validation
- Spring Security
- Current User Endpoint
- Update User Endpoint
- Global Exception Handling
- Request Validation
- Standard API Response Wrapper

### Upcoming Features

- Change Password
- Method-Level Role Authorization
- Admin User Management

---

## Author

**Sthabiso Sibisi**

Backend Developer – Authentication & User Management

ScholarGo Capstone Project
