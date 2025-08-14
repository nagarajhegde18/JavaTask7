import java.sql.*;
import java.util.Scanner;

public class EmployeeDatabaseApp {
    // Database credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";
    static final String PASS = "root";

    // Add employee
    public static void addEmployee(Connection conn, String name, String role, double salary) throws SQLException {
        String sql = "INSERT INTO employees (name, role, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, role);
            pstmt.setDouble(3, salary);
            pstmt.executeUpdate();
            System.out.println("✅ Employee added successfully!");
        }
    }

    // View employees
    public static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📋 Employee List:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("role") + " | ₹" +
                        rs.getDouble("salary"));
            }
        }
    }

    // Update employee
    public static void updateEmployee(Connection conn, int id, String name, String role, double salary) throws SQLException {
        String sql = "UPDATE employees SET name=?, role=?, salary=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, role);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee updated successfully!");
            } else {
                System.out.println("❌ Employee ID not found.");
            }
        }
    }

    // Delete employee
    public static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully!");
            } else {
                System.out.println("❌ Employee ID not found.");
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("✅ Connected to database!");

            int choice;
            do {
                System.out.println("\n===== Employee Database Menu =====");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Role: ");
                        String role = sc.nextLine();
                        System.out.print("Enter Salary: ₹");
                        double salary = sc.nextDouble();
                        addEmployee(conn, name, role, salary);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        System.out.print("Enter Employee ID to update: ");
                        int idU = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String nameU = sc.nextLine();
                        System.out.print("Enter New Role: ");
                        String roleU = sc.nextLine();
                        System.out.print("Enter New Salary: ₹");
                        double salaryU = sc.nextDouble();
                        updateEmployee(conn, idU, nameU, roleU, salaryU);
                        break;
                    case 4:
                        System.out.print("Enter Employee ID to delete: ");
                        int idD = sc.nextInt();
                        deleteEmployee(conn, idD);
                        break;
                    case 5:
                        System.out.println("👋 Exiting...");
                        break;
                    default:
                        System.out.println("❌ Invalid choice!");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
