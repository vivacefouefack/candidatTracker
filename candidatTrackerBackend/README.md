# Application for Job Application Tracking

This project is a backend application developed with **Spring Boot** that allows users to track their job applications.  
It provides management of applications and reminders.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok

---

## Project Structure
```bash
src/
├── main/
│   ├── java/manage/candidatTrackerBackend/
│   │   ├── model/                # JPA entities
│   │   ├── dto/                  # Data Transfer Objects 
│   │   ├── controller/           # REST controllers
│   │   ├── repository/           # Repository interfaces (Spring Data JPA)
│   │   ├── security/             # Security configuration (JWT, filters)
│   │   ├── services/             # Business logic 
│   │   └── AppTrackerApplication.java  # Main application entry point
│   └── resources/
│       └── application.properties # Application configuration file

├── test/
│   └── java/manage/candidatTrackerBackend/
│       ├── model/               # Unit tests for entities
│       ├── controller/          # Unit/integration tests for controllers

```

---

## Main Features

- User authentication
- Create, update, and delete applications
- Add reminders for each application
- Centralized management of application statuses (sent, followed-up, rejected)
- Filtering applications by status

## Running the project localy

1. Clone the project :
   ```bash
   git clone https://github.com/vivacefouefack/candidatTracker.git
   cd candidatTracker/candidatTrackerBackend
   ```

2. Run the application with Maven :
   ```bash
   ./mvnw spring-boot
   ```

3. Access the API :
   ```
   http://localhost:8484/api/auth/login
   ```

---

## Author

Developed by **vivace fouefack**  
Contact: [vivacefouefack@yahoo.com]

---

## License

This project is open source.
