# Restaurant Food Ordering System

A modular desktop-based Restaurant Food Ordering System developed using Java Swing, JDBC, and MySQL. The application provides a modern graphical user interface with dynamic menu loading from a MySQL database, category and Veg/Non-Veg filtering, shopping cart management, automated bill generation, and persistent order storage.

---

## Features

- Modern Java Swing graphical user interface
- Dynamic menu loading from MySQL database
- Category-wise food filtering
- Veg and Non-Veg filtering
- Shopping cart with quantity management
- Automatic bill generation
- Order storage using JDBC
- Modular Object-Oriented Design

---

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- MySQL Connector/J
- Object-Oriented Programming (OOP)

---

## Project Structure

```text
Restaurant-Food-Ordering-System/
│
├── database/
│   └── restaurant_db.sql
│
├── lib/
│   └── mysql-connector-j-9.7.0.jar
│
├── src/
│   ├── Cart.java
│   ├── DBConnection.java
│   ├── Food.java
│   ├── Main.java
│   ├── Order.java
│   └── UI.java
│
├── .gitignore
└── README.md
```

---

## Prerequisites

- Java JDK 17 or later
- MySQL Server
- MySQL Connector/J

---

## Database Setup

1. Open MySQL Workbench.
2. Execute the `database/restaurant_db.sql` script.
3. The script automatically creates the `restaurant_db` database and required tables.
4. Open `src/DBConnection.java`.
5. Update your MySQL username and password.

```java
private static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
private static final String USER = "YOUR_USERNAME";
private static final String PASSWORD = "YOUR_PASSWORD";
```

---

## Running the Application

1. Clone the repository.

```bash
git clone https://github.com/Adiii-02/Restaurant-Food-Ordering-System.git
```

2. Open the project in your preferred Java IDE (IntelliJ IDEA, Eclipse, or VS Code).
3. Add the MySQL Connector/J JAR from the `lib` folder to the project's build path.
4. Configure the database credentials in `DBConnection.java`.
5. Run `Main.java`.

---

## Future Enhancements

- User authentication
- Admin dashboard
- Order history
- Search functionality
- Online payment integration
- Food images
- Customer reviews

---

## Author

**Aditya Singh**

GitHub: https://github.com/Adiii-02
