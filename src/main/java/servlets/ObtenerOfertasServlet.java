package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.OfertaDao;
import model.Oferta;

@WebServlet("/ofertas")
public class ObtenerOfertasServlet extends HttpServlet {

    private OfertaDao ofertaDao = new OfertaDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");

            List<Oferta> ofertas = ofertaDao.obtenerTodasLasOfertas();

            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(ofertas));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
