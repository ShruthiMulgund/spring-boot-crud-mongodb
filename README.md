# Employee Management Application

## Overview
This is a containerized Spring Boot–based Employee Management Application that provides CRUD operations for managing employees.
The project follows a layered architecture with Model, DTO, Controller, Service, and Repository.

- Database: MongoDB
- Configuration: Connection details are provided in application.properties
- Dependencies: Managed via pom.xml
- Testing: Unit tests written using JUnit & Mockito
- Deployment: The application is fully dockerized for containerized execution
- Version Control: Hosted on GitHub

## Project Structure
- Model: Defines the Employee entity
- DTO: Data Transfer Object for API communication
- Controller: REST endpoints for employee operations
- Service: Business logic layer
- Repository: MongoDB repository interface

## Prerequisites
- Java 21
- Maven 3+
- Docker
- MongoDB Compass

## Dockerization

### Running MongoDB Locally (Compass)

- Ensure MongoDB is running on your host machine and accessible via Compass.
- Default URI in application.properties:
  spring.data.mongodb.uri=mongodb://localhost:27017/test

### Running Spring Boot in Docker (connect to local MongoDB)

1. Build the Docker image:
   docker build -t employee-app .

2. Run the application container with environment variable:
   docker run -p 8080:8080 \
   -e SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal:27017/test \
   employee-app

## Accessing the Application
Open in browser:
http://localhost:8080
