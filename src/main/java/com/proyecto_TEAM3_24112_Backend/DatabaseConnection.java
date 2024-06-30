package com.proyecto_TEAM3_24112_Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/tienda_online";
    private static final String USER = "nicolas";
    private static final String PASSWORD = "nicolas010203";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
