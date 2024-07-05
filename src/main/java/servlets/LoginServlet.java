package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthDao authDao = new AuthDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String rol = authDao.validarCredenciales(username, password);

        if (rol != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("rol", rol);
            if (rol.equals("admin")) {
                response.sendRedirect("http://127.0.0.1:5500/templates/dashboard.html");
            } else if (rol.equals("cliente")) {
                response.sendRedirect("http://127.0.0.1:5500/templates/carrito_compras.html");
            }
        } else {
            response.sendRedirect("http://127.0.0.1:5500/templates/log_in.html?error=true");
        }
    }
}




/*package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDao;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthDao authDao = new AuthDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authDao.validarCredenciales(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("http://127.0.0.1:5500/templates/dashboard.html"); 
        } else {
            response.sendRedirect("http://127.0.0.1:5500/templates/log_in.html?error=true"); // Redirigir al login con error
        }
    }
}*/
