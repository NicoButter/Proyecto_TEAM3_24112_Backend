package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/api/*" })
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // No crea sesión si no existe

        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (loggedIn) {
            chain.doFilter(request, response); 
        } else {
            httpResponse.sendRedirect("http://127.0.0.1/wines/templates/log_in.html");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }
}









/* package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/api/*" },
initParams = {
        @WebInitParam(name = "allowedOrigins", value = "http://127.0.0.1:5500"),
        @WebInitParam(name = "allowedMethods", value = "GET,POST,PUT,DELETE"),
        @WebInitParam(name = "allowedHeaders", value = "Content-Type,Authorization"),
        @WebInitParam(name = "preflightMaxAge", value = "300"),
        @WebInitParam(name = "allowCredentials", value = "true")
})// Ajusta esta URL al patrón que necesites
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // No crea sesión si no existe

        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (loggedIn) {
            chain.doFilter(request, response); 
        	httpResponse.sendRedirect("http://127.0.0.1:5500/templates/log_in.html");

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Configuración inicial del filtro (opcional)
    }

    @Override
    public void destroy() {
        // Limpieza del filtro (opcional)
    }
}*/
