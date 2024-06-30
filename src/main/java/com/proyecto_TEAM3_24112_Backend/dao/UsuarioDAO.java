package com.proyecto_TEAM3_24112_Backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proyecto_TEAM3_24112_Backend.model.Usuario;
import com.proyecto_TEAM3_24112_Backend.util.DatabaseConnection;

public class UsuarioDAO {

    private final Connection connection;

    public UsuarioDAO() {
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

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM usuario";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                );
                usuario.setId(rs.getInt("id")); // Asignar el ID recuperado
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
  
    public Usuario obtenerUsuarioPorUsername(String username) {
        Usuario usuario = null;
        try {
            String query = "SELECT * FROM usuario WHERE nombre_usuario=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                );
                usuario.setId(rs.getInt("id")); // Asignar el ID recuperado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
