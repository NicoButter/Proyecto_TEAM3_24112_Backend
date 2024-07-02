package com.proyecto_TEAM3_24112_Backend.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proyecto_TEAM3_24112_Backend.dao.AuthDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthDao authDao = new AuthDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authDao.validarCredenciales(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/dashboard"); // Redirigir al dashboard o página segura
        } else {
            response.sendRedirect("/login.html?error=true"); // Redirigir al login con error
        }
    }
}
