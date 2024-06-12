package org.example;

import java.sql.*;

public class postgresql {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:example.db";

        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            // Створення таблиць
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT, email TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS orders (id INTEGER PRIMARY KEY, user_id INTEGER, product TEXT)");



            // CRUD операції
            // CREATE
            stmt.execute("INSERT INTO users (name, email) VALUES ('Іванов Іван', 'ivan@example.com')");

            // READ
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while(rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }

            // UPDATE
            stmt.execute("UPDATE users SET name = 'Петров Петро' WHERE id = 1");

            // DELETE
            stmt.execute("DELETE FROM users WHERE id = 1");



            // Запит з об'єднанням таблиць
            rs = stmt.executeQuery("SELECT users.name, orders.product FROM users JOIN orders ON users.id = orders.user_id");
            while(rs.next()) {
                System.out.println("User: " + rs.getString("name") + ", Product: " + rs.getString("product"));
            }

        } catch (SQLException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}