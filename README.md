# Expense Tracker App

## Overview

The **Football Match Management Application** is a project that allows users to:
- **Register and Login** with roles: Player, Organizer, or Admin.
- **Create and Manage Matches** (only available to Organizers).
- **Assign Participants to Matches** and monitor attendance.
- **Admin Features** to monitor users, matches, and participants.
- **Notification System:**
  + Notify organizers when participants join their matches.
  + Notify organizers when matches are full.
The application includes essential features for user management, match organization, and participant assignment.

## Features

- **Authentication & Authorization:**
  + Registration and login for Players, Organizers, and Admins.
  + Role-based access control for creating and managing matches.
- **Match Management:**
  + Organizers can create matches.
  + Participants can join matches.
  + Admins can monitor all matches.
- **Participant Management:**
  + Add new participants.
  + Assign participants to matches.
  + Delete participants from matches.
- **Notification System:**
  + Notify organizers when participants join their matches.
  + Notify organizers when matches are full.
- **Admin Panel:**
  + Monitor users, matches, and participants.

## Technologies Used

- **Backend:**
  + Spring Boot (Java)
  + Hibernate (JPA) for database interaction
  + MySQL for data storage
- **Frontend:**
  + HTML, CSS for basic UI
  + Thymeleaf for server-side rendering
- **Other Tools:**
  + Lombok for reducing boilerplate code
  + Spring Mail (optional) for notifications
  + JavaMailSender for email notifications (optional)

## How to Run the Project

**Prerequisites**
1. Install Java 17+.
2. Install Maven.
3. Install and configure MySQL.

**Steps to Run**
1. Clone the Repository:
  ```bash
  git clone https://github.com/yourusername/football-match-management.git
  cd football-match-management
  ```

2. Configure Database:
  - Create a database in MySQL:
  ```bash
  CREATE DATABASE football_app_db;
  ```

  - Create a database in MySQL:
  ```bash
  spring.datasource.url=jdbc:mysql://localhost:3306/football_app_db
  spring.datasource.username=your_mysql_username
  spring.datasource.password=your_mysql_password
  spring.jpa.hibernate.ddl-auto=update
  ```

3. Build and Run the Project:
  - Use Maven to build and run the project:
  ```bash
  mvn spring-boot:run
  ```

4. Access the Application:
  - Open your browser and navigate to:
    + Login Page: http://localhost:8080/login
    + Register Page: http://localhost:8080/register
    + Admin Panel: http://localhost:8080/admin (for Admin role)
    + Matches Page: http://localhost:8080/matches/page
    + Participants Page: http://localhost:8080/participants

## Future Enhancements

- Add a dynamic notification system using WebSockets for real-time updates.
- Improve UI with a modern front-end framework (e.g., React, Angular).
- Add reporting features for match statistics and participation.

## App Preview:
