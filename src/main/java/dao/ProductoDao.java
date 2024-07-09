package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Producto;
import util.DatabaseConnection;

public class ProductoDao {

    private final Connection connection;

    public ProductoDao() {
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

    public boolean crear(Producto producto) {
        boolean insertado = false;
        try {
            String query = "INSERT INTO producto (nombre, tipo, descripcion, precio, imagen_nombre) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, producto.getImagenNombre());

            int filasInsertadas = ps.executeUpdate();
            insertado = filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertado;
    }

    public Producto obtenerPorId(int id) {
        Producto producto = null;
        try {
            String query = "SELECT * FROM producto WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagenNombre = rs.getString("imagen_nombre");

                producto = new Producto(productId, nombre, tipo, descripcion, imagenNombre, precio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public boolean actualizar(Producto producto) {
        boolean actualizado = false;
        try {
            String query = "UPDATE producto SET nombre=?, tipo=?, descripcion=?, precio=?, imagen_nombre=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, producto.getImagenNombre());
            ps.setInt(6, producto.getId());

            int filasActualizadas = ps.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }

    public boolean eliminar(int id) {
        boolean eliminado = false;
        try {
            String query = "DELETE FROM producto WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            int filasEliminadas = ps.executeUpdate();
            eliminado = filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
    }

    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        try {
            String query = "SELECT * FROM producto";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagenNombre = rs.getString("imagen_nombre");

                Producto producto = new Producto(id, nombre, tipo, descripcion, imagenNombre, precio);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public boolean actualizarConImagen(Producto producto, String imagenNombre) {
        boolean actualizado = false;
        try {
            String query = "UPDATE producto SET nombre=?, tipo=?, descripcion=?, precio=?, imagen_nombre=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, imagenNombre != null ? imagenNombre : producto.getImagenNombre());
            ps.setInt(6, producto.getId());

            int filasActualizadas = ps.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }

}
