package dao;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Oferta;
import util.DatabaseConnection;

public class OfertaDao {
    private static final String GET_ALL_OFERTAS_QUERY = "SELECT * FROM ofertas";

    public List<Oferta> obtenerTodasLasOfertas() {
        List<Oferta> ofertas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_OFERTAS_QUERY)) {

            while (rs.next()) {
                Oferta oferta = new Oferta();
                oferta.setId(rs.getInt("id"));
                oferta.setNombre(rs.getString("nombre"));
                oferta.setTipoDescuento(rs.getString("tipo_descuento"));
                oferta.setValorDescuento(rs.getBigDecimal("valor_descuento"));
                
                // Asegúrate de decodificar el nombre de la oferta si está codificado en la base de datos
                String nombreOfertaDecodificado = URLDecoder.decode(oferta.getNombre(), StandardCharsets.UTF_8);
                oferta.setNombre(nombreOfertaDecodificado);
                
                ofertas.add(oferta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ofertas;
    }

    // Otros métodos CRUD (crear, actualizar, eliminar) pueden agregarse aquí
}
