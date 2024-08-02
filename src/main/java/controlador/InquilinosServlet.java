package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Inquilinos;
import coneccion.Conn;

/**
 *
 * @author Pablo Perez
 */
public class InquilinosServlet extends HttpServlet {

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

    String nombres = request.getParameter("nombres");
    String apellidos = request.getParameter("apellidos");
    String cedula = request.getParameter("cedula");
    String telefono = request.getParameter("telefono");
    String correo = request.getParameter("correo");
    String accionInput = request.getParameter("accionInput");
    String id = request.getParameter("id");

    // Configura la conexión a la base de datos
    String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
    String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

    Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    Inquilinos inquilinos = new Inquilinos(conn);

    try {
      if ("eliminar".equals(accionInput)) {
        // Eliminar inquilinos
        if (id != null && !id.isEmpty()) {
          inquilinos.setId(Integer.parseInt(id));
          inquilinos.eliminarInquilino();
        } else {
          throw new RuntimeException("ID del inquilino no especificado.");
        }
      } else if ("Ingresar".equals(accionInput)) {

        // Crear una nueva inquilinos
        inquilinos.setNombres(nombres);
        inquilinos.setApellidos(apellidos);
        inquilinos.setCedula(cedula);
        inquilinos.setTelefono(telefono);
        inquilinos.setCorreo(correo);
        inquilinos.insertarInquilino();
      } else {
        // Actualizar inquilinos existente
        if (id == null || id.isEmpty()) {
          throw new RuntimeException("ID del inquilino no especificado.");
        }
        inquilinos.setId(Integer.parseInt(id));
        inquilinos.setNombres(nombres);
        inquilinos.setApellidos(apellidos);
        inquilinos.setCedula(cedula);
        inquilinos.setTelefono(telefono);
        inquilinos.setCorreo(correo);
        inquilinos.EditarInquilino();

      }
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("Error al manejar la inquilinos", e);
    }

    response.sendRedirect("./VistasServlet?vista=Inquilinos");
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}
