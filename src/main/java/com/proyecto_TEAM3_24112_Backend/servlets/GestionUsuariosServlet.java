package com.proyecto_TEAM3_24112_Backend.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto_TEAM3_24112_Backend.dao.UsuarioDAO;
import com.proyecto_TEAM3_24112_Backend.model.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gestionUsuarios")
public class GestionUsuariosServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    private ObjectMapper objectMapper;

    public GestionUsuariosServlet() {
        this.usuarioDAO = new UsuarioDAO();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
            objectMapper.writeValue(response.getWriter(), usuario);
        } else {
            List<Usuario> usuarios = usuarioDAO.obtenerTodosUsuarios();
            objectMapper.writeValue(response.getWriter(), usuarios);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = objectMapper.readValue(request.getReader(), Usuario.class);
        boolean exito = usuarioDAO.actualizarUsuario(usuario);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean exito = usuarioDAO.eliminarUsuario(id);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exito\": " + exito + "}");
    }
}

