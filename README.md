# CandiTrack - full stack application

This is a full stack application for managing job applications.  
The project is split into two main parts:

- `candidatTrackerBackend/`: Java Spring Boot REST API
- `candiTrack/`: Angular frontend application

---

## Project structure

```
/
├── candidatTrackerBackend/   # Spring Boot backend (Java)
├── candiTrack/               # Angular frontend (TypeScript)
├── .gitignore                # Global ignore file
└── README.md                 # This file
```

---

## Getting started

### 1. Clone the repository

```bash
git clone https://github.com/vivacefouefack/candidatTracker.git
cd candidatTracker
```

---

## Backend setup (Spring Boot)

Navigate to the backend directory:

```bash
cd candidatTrackerBackend
```

### Prerequisites

- Java 17+
- Maven

### Run the backend

```bash
./mvnw spring-boot:run
```

The backend will start on: `http://localhost:8484`

---

## Frontend setup (Angular)

Navigate to the frontend directory:

```bash
cd candiTrack
```

### Prerequisites

- Node.js
- Angular CLI (`npm install -g @angular/cli`)

### Install dependencies

```bash
npm install
```

### Run the frontend

```bash
ng serve
```

The frontend will be available at: `http://localhost:4200`


---

## Environment Variables

Each project has its own environment configuration:

- **Backend**: `application.properties`
- **Frontend**: `********************`

---

## Testing

- **Backend**: use `******`
- **Frontend**: use `ng test`

---

## Author

Developed by **Vivace Fouefack**  
Contact: vivacefouefack@yahoo.com

---

## License

This project is open source.
