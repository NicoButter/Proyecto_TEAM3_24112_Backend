package filters;

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

@WebFilter(filterName = "CorsFilter", urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "allowedOrigins", value = "http://127.0.0.1, http://localhost"),
                @WebInitParam(name = "allowedMethods", value = "GET,POST,PUT,DELETE,OPTIONS"),
                @WebInitParam(name = "allowedHeaders", value = "Content-Type,Authorization"),
                @WebInitParam(name = "preflightMaxAge", value = "300"),
                @WebInitParam(name = "allowCredentials", value = "true")
        })
public class CorsFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String allowedOrigins = filterConfig.getInitParameter("allowedOrigins");
        String allowedMethods = filterConfig.getInitParameter("allowedMethods");
        String allowedHeaders = filterConfig.getInitParameter("allowedHeaders");
        String preflightMaxAge = filterConfig.getInitParameter("preflightMaxAge");
        String allowCredentials = filterConfig.getInitParameter("allowCredentials");

        // Verificar si la cabecera 'Origin' es nula o está permitida
        String originHeader = request.getHeader("Origin");
        if (originHeader != null && allowedOrigins != null && allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        } else if (allowedOrigins != null && allowedOrigins.equals("*")) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        
        if (allowedMethods != null) {
            response.setHeader("Access-Control-Allow-Methods", allowedMethods);
        }
        if (preflightMaxAge != null) {
            response.setHeader("Access-Control-Max-Age", preflightMaxAge);
        }
        if (allowedHeaders != null) {
            response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
        }
        if (allowCredentials != null) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
