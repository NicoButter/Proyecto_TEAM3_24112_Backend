package com.proyecto_TEAM3_24112_Backend.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proyecto_TEAM3_24112_Backend.dao.UsuarioDAO;
import com.proyecto_TEAM3_24112_Backend.model.Usuario;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        // Crear objeto Usuario y establecer sus valores
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuario.setEmail(email);
        usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento));

        // Usar UsuarioDAO para insertar el nuevo usuario en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean registroExitoso = usuarioDAO.insertarUsuario(usuario);

        // Redirigir al usuario según el resultado de la operación
        if (registroExitoso) {
            response.sendRedirect("pages/registrarse.html?exito=true");
        } else {
            response.sendRedirect("pages/registrarse.html?error=true");
        }
    }
}
