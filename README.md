# Customer Orders Application

## Overview
This application is designed to manage customer orders. It includes functionalities to fetch customer data, concat firstname and lastname, and send information to a target API.

## Project Structure
The project is organized into several packages:

- `com.raket.customerorders.controller`: Contains REST controllers for handling HTTP requests.
- `com.raket.customerorders.dto`: DTO (Data Transfer Object) classes.
- `com.raket.customerorders.entity`: Entity classes representing database entities.
- `com.raket.customerorders.repository`: Spring Data repositories for database access.
- `com.raket.customerorders.scheduler`: Scheduler class for fetching and processing data at regular intervals.
- `com.raket.customerorders.service`: Service classes for business logic.
- `com.raket.customerorders.utils`: Utility classes.

## Running the Application
To run the application, follow these steps:
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run` to start the Spring Boot application.
4. Access the endpoints at `http://localhost:8080`.
