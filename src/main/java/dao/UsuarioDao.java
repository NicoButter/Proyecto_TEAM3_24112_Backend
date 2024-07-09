package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Usuario;
import util.DatabaseConnection;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Método para insertar un nuevo usuario
    public boolean insertarUsuario(Usuario usuario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO usuario(nombre_usuario, contrasena, rol, email, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getContrasena());
            preparedStatement.setString(3, usuario.getRol());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un usuario existente
    public boolean actualizarUsuario(Usuario usuario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE usuario SET nombre_usuario=?, email=?, fecha_nacimiento=? WHERE id=?"
            );
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            preparedStatement.setInt(4, usuario.getId());
            
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario por su ID
    public boolean eliminarUsuario(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM usuario WHERE id=?"
            );
            preparedStatement.setInt(1, id);
            
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM usuario"
            );
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreUsuario = rs.getString("nombre_usuario");
                String contrasena = rs.getString("contrasena");
                String rol = rs.getString("rol");
                String email = rs.getString("email");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                
                Usuario usuario = new Usuario(id, nombreUsuario, contrasena, rol, email, fechaNacimiento);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM usuario WHERE id=?"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nombreUsuario = rs.getString("nombre_usuario");
                String contrasena = rs.getString("contrasena");
                String rol = rs.getString("rol");
                String email = rs.getString("email");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");

                usuario = new Usuario(id, nombreUsuario, contrasena, rol, email, fechaNacimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
