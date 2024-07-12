package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            
        	// HttpSession session = request.getSession(true);
            // session.setAttribute("username", username);
            // session.setAttribute("rol", rol);

            // Crear y configurar la cookie de sesión
            // Cookie cookie = new Cookie("JSESSIONID", session.getId());
            // cookie.setMaxAge(30 * 60); 
            // cookie.setHttpOnly(true); 
            // cookie.setSecure(true); 
            
            // response.addCookie(cookie); 
            // response.addCookie(cookie); 
            // response.addCookie(cookie); 
            // response.setStatus(HttpServletResponse.SC_OK);

            if (rol.equals("admin")) {   
                response.sendRedirect("http://127.0.0.1/wines/templates/dashboard.html");
            } else if (rol.equals("cliente")) {
                response.sendRedirect("http://127.0.0.1/wines/templates/carrito_compras.html");
            }
        } else {
            response.sendRedirect("http://127.0.0.1/wines/templates/log_in.html?error=true");
        }
    }

    @Override
    public void destroy() {
        authDao.cerrarConexion(); 
    }
}
