
# E-Commerce Management System (Full Stack Java Project)

This is a **Full Stack Java Web Application** developed using Spring Boot, Spring Data JPA, Thymeleaf, and MySQL.  
It is designed for managing an e-commerce platform with admin and user functionalities including product management, category control, and order handling.

---

##  Features

- Admin and User Login
- Product CRUD Operations
- Category Management
- Shopping Cart and Checkout Flow
- Responsive UI with Bootstrap and Thymeleaf
- Spring Data JPA Integration
- MySQL Database Connectivity
- Maven Project Structure

---

##  Project Screenshots
![Screenshot 2025-06-25 202659](https://github.com/user-attachments/assets/4a4d1160-7d70-4345-8945-5b4335d982a0)




---

##  Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Thymeleaf
- HTML5 + CSS3 + Bootstrap
- MySQL
- Maven

---

## How to Run the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/kashi-bindeshwar/E-Commerce_Management_Project.git
   cd E-Commerce_Management_Project
   ```

2. **Create MySQL Database**
   - Create a database named `ecommerce`
   - Open `src/main/resources/application.properties` and update:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**
   - Navigate to `http://localhost:9090/erpDemo2/customer/showUserRegisterPage` for User panel in your browser.
   - Navigate to `localhost:9090/erpDemo2/admin/loginForm` for Admin panel in your browser.
---

## Project Folder Structure

```
bindeshwarmart/
├── src/
│   └── main/
│       ├── java/                    # Java source code
│       ├── resources/
│       │   ├── static/              
│       │   ├── templates/           # Thymeleaf templates
│       │   └── application.properties
├── pom.xml                          # Maven configuration
└── README.md
```

---



Developed by **Kashi Bindeshwar**  
With the blessings of **Lord Shiva (Mahadev)** 🕉️

---

> 📌 _Feel free to fork, clone, or contribute to this project!_
