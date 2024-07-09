package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDao;
import model.Usuario;

@WebServlet("/gestionUsuarios")
public class GestionUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuarioDao;
    private ObjectMapper objectMapper;

    public GestionUsuariosServlet() throws SQLException {
        this.usuarioDao = new UsuarioDao();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Usuario usuario = usuarioDao.obtenerUsuarioPorId(id);
            objectMapper.writeValue(response.getWriter(), usuario);
        } else {
            List<Usuario> usuarios = usuarioDao.obtenerTodosUsuarios();
            objectMapper.writeValue(response.getWriter(), usuarios);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario nuevoUsuario = objectMapper.readValue(request.getReader(), Usuario.class);
        System.out.println("Datos del nuevo usuario recibido para agregar: " + nuevoUsuario);

        boolean exito = usuarioDao.insertarUsuario(nuevoUsuario);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write("{\"exito\": " + exito + "}");
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = objectMapper.readValue(request.getReader(), Usuario.class);
        System.out.println("Datos del usuario recibido para actualizar: " + usuario);

        boolean exito = usuarioDao.actualizarUsuario(usuario);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write("{\"exito\": " + exito + "}");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean exito = usuarioDao.eliminarUsuario(id);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
    }
}
