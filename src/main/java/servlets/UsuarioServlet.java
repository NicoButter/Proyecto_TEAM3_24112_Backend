package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDao;
import model.Usuario;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDao usuarioDao;
    private ObjectMapper objectMapper;

    public UsuarioServlet() throws SQLException {
        this.usuarioDao = new UsuarioDao();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Obtener el nombre de usuario desde la sesión o base de datos
        // Supongamos que tienes el usuario almacenado en la sesión
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario != null) {
            objectMapper.writeValue(response.getWriter(), usuario);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autorizado");
        }
    }
}


















/*package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/usuario")
public class UsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{ \"username\": \"" + session.getAttribute("username") + "\" }");
            out.flush();
        } else {
            response.sendRedirect("http://127.0.0.1/wines/templates/log_in.html");
        }
    }
}*/
