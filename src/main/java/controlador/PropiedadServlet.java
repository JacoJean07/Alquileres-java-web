package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Propiedades;
import coneccion.Conn;

/**
 *
 * @author Pablo Perez
 */
public class PropiedadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String direccion = request.getParameter("direccion");
        String numero = request.getParameter("numero");
        String descripcion = request.getParameter("descripcion");
        String estado = request.getParameter("estado");
        String propietarioId = request.getParameter("propietarioId");
        String accionInput = request.getParameter("accionInput");
        String id = request.getParameter("id");

        // Configura la conexión a la base de datos
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
        String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

        Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
        Propiedades propiedad = new Propiedades(conn);

        try {
            if ("eliminar".equals(accionInput)) {
                // Eliminar propiedad
                if (id != null && !id.isEmpty()) {
                    propiedad.setId(Integer.parseInt(id));
                    propiedad.eliminarPropiedad();
                } else {
                    throw new RuntimeException("ID de la propiedad no especificado.");
                }
            } else if ("Ingresar".equals(accionInput)) {
                // Crear una nueva propiedad
                propiedad.setDireccion(direccion);
                propiedad.setNumero(numero);
                propiedad.setDescripcion(descripcion);
                propiedad.setEstado(estado);
                propiedad.setPropietarioId(Integer.parseInt(propietarioId));
                propiedad.insertarPropiedad();
            } else {
                // Actualizar inquilinos existente
                if (id == null || id.isEmpty()) {
                    throw new RuntimeException("ID del inquilino no especificado.");
                }
                // Actualizar propiedad existente
                propiedad.setId(Integer.parseInt(id));
                propiedad.setDireccion(direccion);
                propiedad.setNumero(numero);
                propiedad.setDescripcion(descripcion);
                propiedad.setEstado(estado);
                propiedad.setPropietarioId(Integer.parseInt(propietarioId));
                propiedad.EditarPropiedad();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al manejar la propiedad", e);
        }

        response.sendRedirect("./VistasServlet?vista=Propiedades");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar propiedades";
    }
}
