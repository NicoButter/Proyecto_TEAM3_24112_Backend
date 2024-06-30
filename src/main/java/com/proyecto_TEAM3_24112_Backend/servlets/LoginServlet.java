package com.proyecto_TEAM3_24112_Backend.servlets;

import com.proyecto_TEAM3_24112_Backend.model.Usuario;
import com.proyecto_TEAM3_24112_Backend.dao.UsuarioDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO(); 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener parámetros del formulario de login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validar las credenciales
        if (validarCredenciales(username, password)) {
            // Credenciales válidas, iniciar sesión
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/dashboard"); // Redirigir al dashboard u otra página segura
        } else {
            // Credenciales inválidas, redirigir de vuelta al formulario de login con mensaje de error
            response.sendRedirect("/login.html?error=true");
        }
    }

    private boolean validarCredenciales(String username, String password) {
        // Lógica para validar las credenciales utilizando tu DAO o método apropiado
        Usuario usuario = usuarioDAO.obtenerUsuarioPorUsername(username);

        // Verificar si el usuario existe y la contraseña coincide
        return usuario != null && usuario.getPassword().equals(password);
    }
}
