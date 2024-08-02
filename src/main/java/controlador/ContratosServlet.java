package controlador;

import java.io.IOException;
import java.math.BigDecimal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Contratos;
import coneccion.Conn;

/**
 * Servlet para gestionar contratos.
 * 
 * @author Pablo Perez
 */
public class ContratosServlet extends HttpServlet {

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

    // Obtener parámetros del formulario
    String valorMensualStr = request.getParameter("valorMensual");
    String diaMaximoDePagoStr = request.getParameter("diaMaximoDePago");
    String estadoStr = request.getParameter("estado");
    String inquilinoIdStr = request.getParameter("inquilino_id");
    String propiedadIdStr = request.getParameter("propiedad_id");
    String accionInput = request.getParameter("accionInput");
    String id = request.getParameter("id");

    // Configura la conexión a la base de datos
    String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
    String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

    Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    Contratos contrato = new Contratos(conn);

    try {
      if ("eliminar".equals(accionInput)) {
        // Eliminar contrato
        if (id != null && !id.isEmpty()) {
          contrato.setId(Integer.parseInt(id));
          contrato.eliminarContrato(id);
        } else {
          throw new RuntimeException("ID del contrato no especificado.");
        }
      } else if ("Ingresar".equals(accionInput)) {
        // Crear un nuevo contrato
        BigDecimal valorMensual = new BigDecimal(valorMensualStr);
        int diaMaximoDePago = Integer.parseInt(diaMaximoDePagoStr);
        boolean estado = Boolean.parseBoolean(estadoStr);
        int inquilinoId = Integer.parseInt(inquilinoIdStr);
        int propiedadId = Integer.parseInt(propiedadIdStr);

        contrato.ingresarContrato(valorMensual, diaMaximoDePago, estado, inquilinoId, propiedadId);
      } else {
        // Actualizar contrato existente
        if (id == null || id.isEmpty()) {
          throw new RuntimeException("ID del contrato no especificado.");
        }
        contrato.setId(Integer.parseInt(id));
        BigDecimal valorMensual = new BigDecimal(valorMensualStr);
        int diaMaximoDePago = Integer.parseInt(diaMaximoDePagoStr);
        boolean estado = Boolean.parseBoolean(estadoStr);
        contrato.editarContrato(id, valorMensual, diaMaximoDePago, estado);
      }
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("Error al manejar el contrato", e);
    }

    response.sendRedirect("./VistasServlet?vista=Contratos");
  }

  @Override
  public String getServletInfo() {
    return "Servlet para gestionar contratos";
  }
}
