package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import model.Usuario;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        // Crear objeto Usuario y establecer sus valores
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        usuario.setRol("cliente");
        usuario.setEmail(email);

        // Validar y establecer fecha de nacimiento
        try {
            usuario.setFechaNacimiento(fechaNacimiento == null || fechaNacimiento.isEmpty() ? 
                Date.valueOf("1980-11-17") : Date.valueOf(fechaNacimiento)); // Mi cumpleanios
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.sendRedirect("pages/registrarse.html?error=formatoFecha");
            return;
        }

        // Depuración: Imprimir valores capturados
        System.out.println("Datos del Usuario:");
        System.out.println("Nombre de Usuario: " + nombreUsuario);
        System.out.println("Contraseña: " + contrasena);
        System.out.println("Rol: cliente");
        System.out.println("Email: " + email);
        System.out.println("Fecha de Nacimiento: " + usuario.getFechaNacimiento());

        // Usar UsuarioDAO para insertar el nuevo usuario en la base de datos
        UsuarioDao usuarioDao = null;
        try {
            usuarioDao = new UsuarioDao();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("pages/registrarse.html?error=conexion");
            return;
        }

        boolean registroExitoso = usuarioDao.insertarUsuario(usuario);

        // Redirigir al usuario según el resultado de la operación
        if (registroExitoso) {
            response.sendRedirect("http://127.0.0.1/wines/templates/log_in.html?exito=true");
        } else {
            response.sendRedirect("http://127.0.0.1/wines/templates/log_in.html?error=true");
        }
    }
}

/*@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        //String rol = request.getParameter("rol");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        // Crear objeto Usuario y establecer sus valores
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        usuario.setRol("cliente");
        usuario.setEmail(email);
     
        try {
            usuario.setFechaNacimiento(fechaNacimiento == null || fechaNacimiento.isEmpty() ? 
                Date.valueOf("1980-11-17") : Date.valueOf(fechaNacimiento)); // Mi cumpleanios
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.sendRedirect("pages/registrarse.html?error=formatoFecha");
            return;
        }

        // Usar UsuarioDAO para insertar el nuevo usuario en la base de datos
        UsuarioDao usuarioDao = null;
		try {
			usuarioDao = new UsuarioDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean registroExitoso = usuarioDao.insertarUsuario(usuario);

        // Redirigir al usuario según el resultado de la operación
        if (registroExitoso) {
            response.sendRedirect("http://127.0.0.1/wines/templates/log_in.html?exito=true");
        } else {
            response.sendRedirect("http://127.0.0.1/wines/templates/log_in.html?error=true");
        }
    }
}*/
