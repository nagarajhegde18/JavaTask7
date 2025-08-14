# 🗂 Java JDBC – Employee Database App

## 📌 Objective
A Java console application that connects to a **MySQL** database using **JDBC** to perform full **CRUD** (Create, Read, Update, Delete) operations on an Employee table.

## 🛠 Tools & Technologies
- **Java** (JDK 17+ recommended)
- **MySQL** (or PostgreSQL with minor changes)
- **JDBC Driver** (MySQL Connector/J)
- **VS Code** (or IntelliJ/Eclipse)

## 📂 Project Structure
EmployeeDatabaseApp.java
mysql-connector-j-8.x.x.jar



## 🚀 Features
- **Add Employee** → Insert new employee records.
- **View Employees** → Display all employees in the table.
- **Update Employee** → Modify employee details.
- **Delete Employee** → Remove employee records.
- Menu-driven interface for easy navigation.

## 🗄 Database Setup (MySQL)
Run these commands in MySQL:
```sql
CREATE DATABASE employee_db;

USE employee_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL
);

How It Works
Database Connection

Uses DriverManager.getConnection(DB_URL, USER, PASS) to connect to MySQL.

Requires MySQL Connector/J in the project classpath.

Core JDBC Classes Used

Connection → Connects to DB.

PreparedStatement → Executes parameterized SQL queries.

ResultSet → Reads query results.

🖥 How to Run in VS Code
1️.Install MySQL & JDBC Driver

Download MySQL Connector/J: https://dev.mysql.com/downloads/connector/j/

Place the .jar file in your project folder.
2.Compile
javac -cp ".;mysql-connector-j-8.3.0.jar" EmployeeDatabaseApp.java

3️.Run
java -cp ".;mysql-connector-j-8.3.0.jar" EmployeeDatabaseApp

 Sample Output
 Connected to database!

===== Employee Database Menu =====
1. Add Employee
2. View Employees
3. Update Employee
4. Delete Employee
5. Exit
Enter choice: 1
Enter Name: John
Enter Role: Manager
Enter Salary: ₹50000
 Employee added successfully!



