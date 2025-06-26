
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
###  User Panel
   #### Register Page
![Screenshot 2025-06-25 202447](https://github.com/user-attachments/assets/a31232d2-d3b2-4f6d-aa49-a3c8f43cbe05)

   #### Login Page
![Screenshot 2025-06-25 202611](https://github.com/user-attachments/assets/e9982046-6d93-4309-985a-e6bdb51dbcc3)

   #### Home Page (Header Section And Hero Section)
![Screenshot 2025-06-25 202659](https://github.com/user-attachments/assets/4a4d1160-7d70-4345-8945-5b4335d982a0)

  #### Home Page (Body Section)
![Screenshot 2025-06-25 202753](https://github.com/user-attachments/assets/ee1d283f-2137-4d61-847a-91d59339a3ee)

  ####  Product Description Page
![Screenshot 2025-06-25 203454](https://github.com/user-attachments/assets/6aa21eaa-8c4a-4aa1-af66-44516091049a)

  ####  Related Product of Selected Product
![Screenshot 2025-06-25 203636](https://github.com/user-attachments/assets/d01f8228-f767-4aa3-91a2-737af7131b9d)

  ####  Home Page (Body Section And Footer Section)
![Screenshot 2025-06-25 203407](https://github.com/user-attachments/assets/6807b231-7487-44da-8951-d1eedaced59b)

  ####  Wishlist Page
![Screenshot 2025-06-25 205036](https://github.com/user-attachments/assets/f59ee477-9175-4cb1-8603-09710fb15768)

####  Product Cart Page
![Screenshot 2025-06-25 205057](https://github.com/user-attachments/assets/79f37cdd-1d05-4cf8-8627-38e7778498b3)

####  Product Billing Page
![Screenshot 2025-06-25 205136](https://github.com/user-attachments/assets/dd75e9e9-bf33-4296-b370-d8cdd3062220)

##  Admin Panel
#### Admin Login Page
![Screenshot 2025-06-26 015128](https://github.com/user-attachments/assets/8e0d606d-eabf-4d8a-b5af-3f3680064d0a)

####  Home Page
![Screenshot 2025-06-26 015216](https://github.com/user-attachments/assets/cae676de-048e-4551-b28d-8b740af28bf3)

#### New Orders and Completed Orders Page
![Screenshot 2025-06-26 015248](https://github.com/user-attachments/assets/a813829d-732e-4ece-896c-b796aae27957)

#### Registered Users Page
![Screenshot 2025-06-26 015312](https://github.com/user-attachments/assets/1e23d11d-1a8f-4dd1-b8d0-2bf8090edb87)

## MySql Workbench Database 
### Registered Users Data
![Screenshot 2025-06-26 015859](https://github.com/user-attachments/assets/d1dca370-1044-403e-9d84-74e621244f57)


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
