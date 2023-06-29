package com.example.houseagent;
import java.sql.*;
public class addInfo {
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/houseinfo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/houseinfo";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Mabohv123";

    public boolean addProperty(String name, double area, int buildingNumber, int roomNumber, double houseArea, double unitPrice, double totalPrice) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String createTableSql = "CREATE TABLE properties (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), area DOUBLE, building_number INT, room_number INT, house_area DOUBLE, unit_price DOUBLE, total_price DOUBLE)";

            // 执行创建表操作
            //Statement createStatement = conn.createStatement();
            //createStatement.executeUpdate(createTableSql);
            Statement createStatement = conn.createStatement();
            createStatement.executeUpdate("ALTER TABLE properties ADD COLUMN new_column1 INT");

            String sql = "INSERT INTO properties (name, area, building_number, room_number, house_area, unit_price, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, area);
            statement.setInt(3, buildingNumber);
            statement.setInt(4, roomNumber);
            statement.setDouble(5, houseArea);
            statement.setDouble(6, unitPrice);
            statement.setDouble(7, totalPrice);
            int rowsAffected = statement.executeUpdate();

            conn.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 其他操作，如查询、更新等

    public static void main(String[] args) {
        addInfo propertyDAO = new addInfo();
        boolean success = propertyDAO.addProperty("Property 1", 1000.0, 1, 101, 80.0, 5000.0, 400000.0);

        if (success) {
            System.out.println("Property added successfully!");
        } else {
            System.out.println("Failed to add property.");
        }
    }
}

