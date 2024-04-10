# Appointment Management System

## Description:
This is a simple appointment management system that allows users to create, read, update and delete appointments. The system is built using Springboot and Vite js.

## Features:
- Create an appointment
- Read an appointment
- Update an appointment
- Delete an appointment
- View all appointments
- View appointments by date

## Technologies:
- Springboot
- MongoDB
- Ubuntu

## Tools:
- Postman
- IntelliJ IDEA
- Atlas MongoDB
- Git
- Maven

## Dependencies:
- Spring Web
- Spring Data MongoDB
- Lombok


## Installation:
1. Clone the repository
2. Run `mvn clean install` to install dependencies
3. Run `mvn spring-boot:run` to start the application
4. Open the api tester and test the endpoints
5. Open the frontend and test the application

## Apis:
- POST /api/v1//user/register
- POST /api/v1/user/login
- POST /api/v1/user/logout
- GET /api/v1/user/profile
- PUT /api/v1/user/profile/{id}

- GET /api/v1/appointments/get_all_appointments
- POST /api/v1/appointments/add_appointment
- PUT /api/v1/appointments/update_appointment/{id}
- DELETE /api/v1/appointments/delete_appointment/{id}
- GET /api/v1/appointments/get_appointment_by_date/{date}
- GET /api/v1/appointments/get_appointment_by_week/{week}
- GET /api/v1/appointments/get_appointment_by_month/{month}
- POST /api/v1/admin/super_login
- POST /api/v1/admin/super_logout
- GET /api/v1/admin/super_profile
- PUT /api/v1/admin/super_profile/{id}
- GET /api/v1/admin/get_all_users
- GET /api/v1/admin/get_all_appointments

