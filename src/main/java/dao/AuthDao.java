package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseConnection;

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
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String validarCredenciales(String username, String password) {
        String rol = null;

        String sql = "SELECT rol FROM usuario WHERE nombre_usuario = ? AND contrasena = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rol = resultSet.getString("rol");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rol;
    }
}
