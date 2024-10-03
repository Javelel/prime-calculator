# Prime Numbers Calculator Web Application

This is a web application that allows users to calculate prime numbers up to a specified upper limit using the **Sieve of Eratosthenes** algorithm.
It provides a user interface to input the upper bound and returns a list of prime numbers within that range.

## Technologies Used

- **Java 11**: Programming language used for the backend.
- **Spring Boot 2.x**: Framework used for building the web application.
- **Thymeleaf**: Template engine used for rendering HTML pages.
- **Maven**: Dependency management and build automation tool.
- **JUnit 5**: Framework for writing and running tests.
- **MockMvc**: Part of Spring for testing web layer.
  
## Features

- Calculates prime numbers using the Sieve of Eratosthenes.
- Simple web interface for entering the upper limit.
- Error handling for invalid inputs.
- Unit tests for both service and controller layers.

## Prerequisites

Make sure you have the following installed:

- **Java 11** or later
- **Maven**

## How to Run the Application

Follow these steps to run the application locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Javelel/prime-calculator.git
   cd prime-calculator
   ```
2. **Build the project using maven**:
  ```bash
  mvn clean package
```
3. **Run the application:**:
  ```bash
  mvn spring-boot:run
```
4. **Access the application: Once the application is running, open your browser and go to**:
  ```
http://localhost:8080
```
## Running Tests
**To run all tests, use the following Maven command**:
```bash
mvn test
```
## Usage

- On the main page, enter an upper bound (integer greater than or equal to 2) and click **Calculate**.
- The application will display a list of prime numbers up to the entered number.
