package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ProductoDao;
import model.Producto;

@WebServlet("/gestionProductos")
public class GestionProductosServlet extends HttpServlet {

    private ProductoDao productoDao;
    private ObjectMapper objectMapper;

    public GestionProductosServlet() {
        this.productoDao = new ProductoDao(); 
        this.objectMapper = new ObjectMapper(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Producto producto = productoDao.obtenerPorId(id);
            objectMapper.writeValue(response.getWriter(), producto);
        } else {
            List<Producto> productos = productoDao.obtenerTodos();
            objectMapper.writeValue(response.getWriter(), productos);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producto producto = objectMapper.readValue(request.getReader(), Producto.class);
        boolean exito = productoDao.crear(producto);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producto producto = objectMapper.readValue(request.getReader(), Producto.class);
        boolean exito = productoDao.actualizar(producto);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean exito = productoDao.eliminar(id);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
    }
}
