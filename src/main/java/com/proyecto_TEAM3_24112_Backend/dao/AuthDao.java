package com.proyecto_TEAM3_24112_Backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyecto_TEAM3_24112_Backend.util.DatabaseConnection;

public class AuthDao {

    private final Connection connection;

    public AuthDao() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = conn;
    }

    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();     
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validarCredenciales(String username, String password) {
        boolean validado = false;
        String query = "SELECT * FROM usuarios WHERE username=? AND password=?";
        try {
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                validado = rs.next();
            } else {
                System.out.println("La conexión es nula. No se puede validar las credenciales.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validado;
    }
}
