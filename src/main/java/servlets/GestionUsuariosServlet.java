package servlets;

import java.io.IOException;
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

    private UsuarioDao usuarioDao;
    private ObjectMapper objectMapper;

    public GestionUsuariosServlet() {
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = objectMapper.readValue(request.getReader(), Usuario.class);
        boolean exito = usuarioDao.actualizarUsuario(usuario);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
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

