package controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pagos;
import coneccion.Conn;

/**
 * Servlet para gestionar pagos.
 * 
 * @author Pablo Perez
 */
public class PagosServlet extends HttpServlet {

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
    String fechaRegistroStr = request.getParameter("fechaRegistro");
    String montoStr = request.getParameter("monto");
    String formaPago = request.getParameter("formaPago");
    String detallePago = request.getParameter("detallePago");
    String estadoStr = request.getParameter("estado");
    String inquilinoIdStr = request.getParameter("inquilino_id");
    String accionInput = request.getParameter("accionInput");
    String id = request.getParameter("id");

    // Configura la conexión a la base de datos
    String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
    String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

    Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    Pagos pago = new Pagos(conn);

    try {
      if ("eliminar".equals(accionInput)) {
        // Eliminar pago
        if (id != null && !id.isEmpty()) {
          pago.setId(Integer.parseInt(id));
          pago.eliminarPago();
        } else {
          throw new RuntimeException("ID del pago no especificado.");
        }
      } else if ("Ingresar".equals(accionInput)) {
        // Crear un nuevo pago
        BigDecimal monto = new BigDecimal(montoStr);
        boolean estado = Boolean.parseBoolean(estadoStr);
        int inquilinoId = Integer.parseInt(inquilinoIdStr);

        pago.setMonto(monto);
        pago.setFormaPago(formaPago);
        pago.setDetallePago(detallePago);
        pago.setEstado(estado);
        pago.setInquilinoId(inquilinoId);

        pago.insertarPago();
      } else {
        // Actualizar pago existente
        if (id == null || id.isEmpty()) {
          throw new RuntimeException("ID del pago no especificado.");
        }
        pago.setId(Integer.parseInt(id));
        Timestamp fechaRegistro = Timestamp.valueOf(fechaRegistroStr);
        BigDecimal monto = new BigDecimal(montoStr);
        boolean estado = Boolean.parseBoolean(estadoStr);

        pago.setFechaRegistro(fechaRegistro);
        pago.setMonto(monto);
        pago.setFormaPago(formaPago);
        pago.setDetallePago(detallePago);
        pago.setEstado(estado);

        pago.editarPago();
      }
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("Error al manejar el pago", e);
    }

    response.sendRedirect("./VistasServlet?vista=Pagos");
  }

  @Override
  public String getServletInfo() {
    return "Servlet para gestionar pagos";
  }
}
