package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductoDao;
import model.Producto;

@WebServlet("/productosPorTipo")
public class ProductosPorTipoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String tipoBebida = request.getParameter("tipo");

        List<Producto> productos = obtenerProductosPorTipo(tipoBebida);

        Gson gson = new Gson();
        String jsonProductos = gson.toJson(productos);

        out.print(jsonProductos);
        out.flush();
    }

    private List<Producto> obtenerProductosPorTipo(String tipoBebida) {
        List<Producto> productos = ProductoDao.obtenerProductosPorTipo(tipoBebida);
        return productos;
    }
}


