# E-Library | backend
This application serves as the secure and scalable backend for an library management system. 
The goal of the E-Library project was to digitize core operations and resources into an easy to use web application for both staff and clients. 
The backend focuses on centralizing the management of books, users and transactions.

## Table of Contents
- [Features](#features)
- [Technological Description](#technological-description)
- [Deployment](#deployment)

## Features <a name="features"/>
 * Library Administration 
   > Administrators can efficiently manage the library's resources with functionalities like:
   - add new clients
   - add and update books
   - borrow books and confirm the returns
   - check the history of borrowed and reservved books
   - confirm the reservations made by clients
     
 * Library Client 
   > Clients can easily perform operations like:
     - check library resources
     - make and cancel reservations
     - check the history of reserved and borrowed books 
     - check new clubs and activities offered by library

## Technological description <a name="technological-description"/>

 ### Language and Framework 
   * Backend is written with Java 17 with modules:
      - Spring Boot
      - Spring Actuator
      - Spring Security
      - Spring MVC
      - Spring Data JPA

 ### Security
  > Security is managed through the integration of:
  - Auth0
  - JWT (JSON Web Tokens)

 ### Automation
   > The task efficiently identifies and cancels expired reservations while marking associated items as returned, ensuring streamlined data management and operational efficiency.
   - Utilized with __`@Scheduled`__ 

 ### Caching
   > __`Caffeine's`__ integration of in-memory caching empowers the project with rapid responses, reducing data duplication, and propelling application performance.

 ### Database and Data Versioning
   - PostgreSQL
   - Liquibase : used for populating database with sample data

 ### Other libraries and tools in the project:
   - Mapstruct
   - Lombok
   - Hibernate
   - Swagger
   - OkHttps
   - Docker

 ## Deployment <a name="deployment"/>
   * Service is run in container from project's Dockerfile on Render platform. 
   * Database is run as separate instance on the same platform.
