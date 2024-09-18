# Flight Management Application

## Overview

This project is a flight management application developed using Java and Spring Boot. It provides functionality for managing ticket availability, performing baggage check-ins, applying discount coupons, and implementing in-memory caching.

## Features

### 1. Ticket Availability Check
- **Endpoint:** `GET /tickets/{ticketId}`
- **Description:** Check if a ticket is available. Returns a boolean value indicating if the ticket is free based on hardcoded data.

### 2. Baggage Check-in
- **Endpoint:** `POST /baggage/checkin`
- **Description:** Users provide a `destinationId` and `baggageId`. Returns a boolean value indicating the success of the check-in.

### 3. Coupon Application
- **Endpoint:** `POST /tickets/coupon_discount`
- **Description:** Users submit a `couponId` and the ticket price. If the coupon is valid, returns the new price after applying a randomly selected discount (10%, 50%, or 60%). If the coupon is invalid, returns an error message.

## Getting Started


### Installation
1. **Clone the Repository:**
    ```bash
    git clone https://github.com/teract10s/flight-control-app.git
    ```

2. **Navigate to the Project Directory:**
    ```bash
    cd flight-management-app
    ```

3. **Build and Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```
   for Windows
    ```bash
    mvnw.cmd spring-boot:run
    ```

### Running Tests
To ensure that your application is functioning correctly, you can run the unit tests using Maven or Gradle.

1. **Run Tests with Maven:**
    ```bash
    ./mvnw test
    ```
   for Windows
    ```bash
    mvnw.cmd test
    ```

### API Documentation
Access the API documentation at `http://localhost:8080/swagger-ui.html` if Swagger is configured.