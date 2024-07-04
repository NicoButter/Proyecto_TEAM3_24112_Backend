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

    public boolean insertarUsuario(Usuario usuario) {
        boolean insertado = false;
        try {
            String query = "INSERT INTO usuario (nombre_usuario, contrasena, rol, email, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getEmail());
            ps.setDate(5, usuario.getFechaNacimiento());

            int filasInsertadas = ps.executeUpdate();
            insertado = filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertado;
    }

    public Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        try {
            String query = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento")
                );
                usuario.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        boolean actualizado = false;
        try {
            String query = "UPDATE usuario SET nombre_usuario=?, contrasena=?, rol=?, email=?, fecha_nacimiento=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getEmail());
            ps.setDate(5, usuario.getFechaNacimiento());
            ps.setInt(6, usuario.getId());
    
            int filasActualizadas = ps.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }

    public boolean eliminarUsuario(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM usuario WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate();
            eliminado = filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
    }

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
                        rs.getString("rol"),
                        rs.getString("email"),
                        rs.getDate("fecha_nacimiento")
                );
                usuario.setId(rs.getInt("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
