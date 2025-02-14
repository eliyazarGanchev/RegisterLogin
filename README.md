# Java User Authentication System (Swing + PostgreSQL)

## Project Overview
This is a **User Registration and Login System** built with **Java (Swing UI) and PostgreSQL**.  
It allows users to:
- Register with **a valid email and strong password**.
- Login with **email and password**.
- Securely store passwords using **BCrypt hashing**.
- Use **JDBC to interact with PostgreSQL**.

---

##  Features
- **Password security:** Uses **BCrypt hashing** for safe storage.
- **Email validation:** Ensures valid **email format** (`must contain @`).
- **Password rules enforced:**
  - **At least 6 characters**
  - **At least one uppercase letter (A-Z)**
  - **At least one lowercase letter (a-z)**
  - **At least one number (0-9)**
- **Modern Java Swing UI** for **user-friendly interaction**.
- **Database: PostgreSQL** for storing user credentials.

---

## Technologies Used
- **Java (JDK 11+)**
- **Swing (GUI)**
- **PostgreSQL (Database)**
- **JDBC (Java Database Connectivity)**
- **BCrypt (Password Hashing)**
- **Maven (Dependency Management)**

---

 Future Improvements
Potential Enhancements:

- Password reset feature
- UI improvements (better styling with themes)
- Multi-language support
- Web version using JSP/Servlets
- Deploy the database on a remote server

---
