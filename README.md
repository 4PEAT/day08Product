
---

# 🛍️ Product & Category Management App

This is a Spring Boot + Thymeleaf application that lets you **manage products and categories** with full **CRUD support**, **role-based security**, and **Bootstrap-based UI**.

---

## ✅ Features

* Add, update, delete and view **products**
* Add, update, delete and view **categories**
* Enforced relationship: a product belongs to a category
* Thymeleaf templates with Bootstrap styling
* **Role-based access control** using Spring Security:

  * Only `ADMIN` can add/edit/delete
  * `USER` can only view
* Login via Spring Security form
* JDBC-based authentication from database
* CSRF protection enabled by default

---

## 🚀 Getting Started

### 🔧 Requirements

* Java 21+
* Maven
* MySQL (or H2 for development)
* Spring Boot 3.x

---

## ⚙️ Database Setup (MySQL)

Run the following SQL to create user and role tables:

```sql
CREATE TABLE users (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
  username VARCHAR(50),
  authority VARCHAR(50),
  FOREIGN KEY (username) REFERENCES users(username)
);
```

---

### 🔐 Creating Users

To add users, you first need to hash their passwords.

#### 1. Generate hashed password:

In your `SecurityConfig.java`, run the following main method:

```java
public static void main(String[] args) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("your_password_here"));
}
```

🔒 This will output a secure, hashed password (e.g. `$2a$10$...`).

#### 2. Insert users into the database:

```sql
INSERT INTO users (username, password, enabled) VALUES ('admin', '<hashed_password>', true);
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO users (username, password, enabled) VALUES ('user', '<hashed_password>', true);
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
```

---

## 🧪 Running the Application

```bash
# Clone the repo
git clone https://github.com/4PEAT/day08Product.git
cd day08Product

# Run with Maven
mvn spring-boot:run
```

The app will start at:
📍 `http://localhost:8080`

---

## 🌐 Routes Overview

| Route                          | Access        | Description          |
| ------------------------------ | ------------- | -------------------- |
| `/home`                        | Public        | Home page            |
| `/view/products`               | Authenticated | View products list   |
| `/view/products/add`           | ADMIN only    | Add product          |
| `/view/products/edit/{id}`     | ADMIN only    | Edit product         |
| `/view/products/delete/{id}`   | ADMIN only    | Delete product       |
| `/view/categories`             | Authenticated | View categories list |
| `/view/categories/add`         | ADMIN only    | Add category         |
| `/view/categories/edit/{id}`   | ADMIN only    | Edit category        |
| `/view/categories/delete/{id}` | ADMIN only    | Delete category      |
| `/login`                       | Public        | Login form           |

---

## 🎨 UI & Styling

* Built using **Bootstrap 5**
* Responsive, modern forms and tables
* User-friendly layout with confirmation dialogs, navigation links, and error handling

---

## 🛡️ Security

* 🔐 Spring Security configuration in `SecurityConfig.java`
* ✔️ Form-based login with success redirect
* 🔐 Role-based access (`hasRole("ADMIN")` or `authenticated()`)
* 🚪 Logout functionality
* 🧷 CSRF enabled (handled automatically in Thymeleaf forms)

---

## 🧠 Built With

* Spring Boot
* Spring Security
* Thymeleaf
* Bootstrap 5

---
