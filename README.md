# Fees Management Backend

A Spring Boot backend project for managing students, courses, and fees with authentication.

This project was built as a practice project to understand how real backend applications are developed, secured, and deployed.

# **FEATURES:**

User registration and login (JWT authentication)

Create, update, and delete students

Create and view courses

Fee summary per student

MySQL database integration

Deployed live on Railway

# **Tech Used**

Java 21

Spring Boot

Spring Security (JWT)

MySQL

Maven

Railway (for deployment)

Live API

## Base URL:

https://fees-management-production.up.railway.app


### Example endpoints:

POST /auth/register  
POST /auth/login  
GET  /students  
GET  /courses


#### Most endpoints require a JWT token after login.

Run Locally

Make sure MySQL is running

### Set environment variables:

DB_URL=jdbc:mysql://localhost:3306/feesdb  
DB_USERNAME=root  
DB_PASSWORD=yourpassword


Run the project:

mvn spring-boot:run

## What I Learned

Building REST APIs with Spring Boot

Using JWT for authentication

Working with databases using JPA

Using environment variables for secrets

Deploying a backend to the cloud

Debugging real deployment issues