package controlador;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pagos;
import coneccion.Conn;

/**
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

    String montoStr = request.getParameter("monto");
    String formaPago = request.getParameter("formaPago");
    String detallePago = request.getParameter("detallePago");
    String estadoStr = request.getParameter("estado");
    String accionInput = request.getParameter("accionInput");
    Integer id = null;
    Integer inquilinoId = null;

    // Convertir el monto de String a BigDecimal
    java.math.BigDecimal monto = new java.math.BigDecimal(montoStr);

    // Convertir el estado de String a Boolean
    Boolean estado = Boolean.parseBoolean(estadoStr);

    List<Map<String, Object>> listaPagos = null;

    // Maneja el caso en que id puede no estar presente
    if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
      id = Integer.parseInt(request.getParameter("id"));
    }

    if (request.getParameter("inquilino_id") != null && !request.getParameter("inquilino_id").isEmpty()) {
      inquilinoId = Integer.parseInt(request.getParameter("inquilino_id"));
    }

    System.out.println(accionInput);

    // Configura la conexión a la base de datos
    String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
    String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

    Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    Pagos pagos = new Pagos(conn);

    switch (accionInput) {
      case "Ingresar":
        pagos.ingresarPago(monto, formaPago, detallePago, estado, inquilinoId);
        // ACTUALIZA LA LISTA DE PAGOS
        pagos.mostrarPagos();
        listaPagos = pagos.getPagos();
        request.getSession().setAttribute("pagos", listaPagos);
        if (listaPagos.isEmpty()) {
          System.out.println("No hay pagos disponibles para mostrar.");
        }
        break;
      case "editar":
        if (id != null) {
          pagos.editarPago(id, monto, formaPago, detallePago, estado, inquilinoId);
          // ACTUALIZA LA LISTA DE PAGOS
          pagos.mostrarPagos();
          listaPagos = pagos.getPagos();
          request.getSession().setAttribute("pagos", listaPagos);
          if (listaPagos.isEmpty()) {
            System.out.println("No hay pagos disponibles para mostrar.");
          }
        }
        break;
      case "eliminar":
        if (id != null) {
          pagos.eliminarPago(id);
          // ACTUALIZA LA LISTA DE PAGOS
          pagos.mostrarPagos();
          listaPagos = pagos.getPagos();
          request.getSession().setAttribute("pagos", listaPagos);
          if (listaPagos.isEmpty()) {
            System.out.println("No hay pagos disponibles para mostrar.");
          }
        }
        break;
      case "mostrar":
        pagos.mostrarPagos();
        listaPagos = pagos.getPagos();
        request.getSession().setAttribute("pagos", listaPagos);
        if (listaPagos.isEmpty()) {
          System.out.println("No hay pagos disponibles para mostrar.");
        }
        break;
      default:
        pagos.mostrarPagos();
        break;
    }
    // Redirige a la página de pagos
    response.sendRedirect("main/pagos.jsp");
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}
