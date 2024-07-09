package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.ServletException;


import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ProductoDao;
import model.Producto;

@WebServlet("/gestionProductos")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
    maxFileSize = 1024 * 1024 * 10,       // 10 MB
    maxRequestSize = 1024 * 1024 * 15     // 15 MB
)
public class GestionProductosServlet extends HttpServlet {

    private ProductoDao productoDao;
    private ObjectMapper objectMapper;

    public GestionProductosServlet() {
        this.productoDao = new ProductoDao();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Producto producto = productoDao.obtenerPorId(id);
            objectMapper.writeValue(response.getWriter(), producto);
        } else {
            List<Producto> productos = productoDao.obtenerTodos();
            objectMapper.writeValue(response.getWriter(), productos);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Procesar el formulario multipart/form-data
            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");

            Part filePart = request.getPart("imagen");
            String fileName = null;

            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = System.getProperty("ruta.imagenes");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists() && !uploadDirFile.mkdirs()) {
                    throw new ServletException("No se pudo crear el directorio para subir archivos");
                }
                File file = new File(uploadDir, fileName);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new ServletException("Error al copiar el archivo al directorio de subida", e);
                }
            }

            // Crear el objeto Producto
            Producto nuevoProducto = new Producto(0, nombre, tipo, descripcion, fileName, precio);

            // Guardar en la base de datos
            boolean exito = productoDao.crear(nuevoProducto);

            // Preparar la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"exito\": " + exito + "}");

        } catch (ServletException | IOException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servlet al procesar la solicitud: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Obtener y actualizar datos del producto desde el formulario
            String idParam = request.getParameter("id");
            int id = Integer.parseInt(idParam);
            Producto producto = productoDao.obtenerPorId(id);

            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String descripcion = request.getParameter("descripcion");

            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);

            // Procesar la imagen adjunta
            Part filePart = request.getPart("imagen");
            String fileName = null;

            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("/") + "imagenes";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdir();
                }
                File file = new File(uploadDir, fileName);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            // Actualizar el producto en la base de datos con la nueva imagen
            boolean exito = productoDao.actualizarConImagen(producto, fileName);
            
            // Devolver respuesta al cliente
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"exito\": " + exito + "}");
        } catch (ServletException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servlet al procesar la solicitud.");
        }
    }

}
