# 📱 AI-Powered Smartphone E-Commerce Platform

An AI-powered smartphone e-commerce web application built using **Java, Spring Boot, MySQL, and Google Gemini API**. The platform allows users to explore smartphones, view product details, manage a shopping cart, and receive AI-powered smartphone recommendations.

## 🎯 Project Overview

This project combines a traditional smartphone shopping platform with an AI-powered recommendation assistant. Users can browse available smartphones and interact with an AI assistant to get recommendations based on their requirements.

## ✨ Features

- **Smartphone Catalog:** Browse available smartphones.
- **Product Details:** View individual smartphone information.
- **Phone Management:** Add new smartphones to the product inventory.
- **Shopping Cart:** Add products and manage cart items.
- **AI Recommendation Assistant:** Get smartphone recommendations using Google Gemini API.
- **Database Integration:** Store and retrieve smartphone information using MySQL.
- **Responsive Interface:** User-friendly web interface built with HTML and CSS.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Backend framework |
| Spring Data JPA | Database operations |
| MySQL | Relational database |
| Google Gemini API | AI-powered recommendations |
| HTML | Web page structure |
| CSS | Styling and layout |
| Thymeleaf | Dynamic HTML rendering |
| Maven | Dependency management |

## 🏗️ Project Structure

```text
ProjectSpringboot/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/springbootproj/ProjectSpringboot/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repo/
│   │   │       └── services/
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   └── images/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/AnshuRay-ar/AI-Powered-Smartphone-Ecommerce-Platform.git
```

### 2. Open the Project

Open the cloned project in IntelliJ IDEA, Eclipse, or Visual Studio Code.

### 3. Configure MySQL

- Install and start MySQL.
- Create a database for the application.
- Configure the database URL, username, and password in `application.properties`.

### 4. Configure Google Gemini API

- Generate an API key from [Google AI Studio](https://aistudio.google.com/).
- Configure the API key using an environment variable.
- Set the Gemini model in your application configuration.

**Important:** Never upload your actual API key or database password to GitHub.

### 5. Run the Application

On Windows, open the terminal in the project directory and execute:

```powershell
.\mvnw.cmd spring-boot:run
```

### 6. Open the Application

Visit:

```text
http://localhost:8080
```

## 🤖 AI Integration

The application integrates Google Gemini API to provide an AI-powered smartphone recommendation assistant.

The AI service processes user queries and generates recommendations based on the application's smartphone information and recommendation instructions.

## 🎥 Project Demo

[▶ Watch Project Demo on YouTube](https://youtu.be/p9nHGt6ZP2g)

## 👩‍💻 Author

**Anshu Ray**

GitHub: [AnshuRay-ar](https://github.com/AnshuRay-ar)

---

⭐ If you find this project interesting, consider giving the repository a star!
