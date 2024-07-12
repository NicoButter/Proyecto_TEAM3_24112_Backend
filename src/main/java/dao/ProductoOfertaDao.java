package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductoOferta;
import util.DatabaseConnection;

public class ProductoOfertaDao {
    private static final String GET_PRODUCTOS_OFERTAS_QUERY = "SELECT * FROM productos_ofertas WHERE id_oferta = ?";

    public List<ProductoOferta> obtenerProductosPorOferta(int idOferta) {
        List<ProductoOferta> productosOfertas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_PRODUCTOS_OFERTAS_QUERY)) {

            stmt.setInt(1, idOferta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductoOferta productoOferta = new ProductoOferta();
                productoOferta.setId(rs.getInt("id"));
                productoOferta.setIdProducto(rs.getInt("id_producto"));
                productoOferta.setIdOferta(rs.getInt("id_oferta"));
                productoOferta.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                productoOferta.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                productosOfertas.add(productoOferta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosOfertas;
    }

    // Otros métodos CRUD (crear, actualizar, eliminar) pueden agregarse aquí
}
