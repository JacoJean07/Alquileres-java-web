package controlador;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Contratos;
import coneccion.Conn;

/**
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

    // Supongamos que recibes parámetros como Strings desde una solicitud HTTP
    String valorMensualStr = request.getParameter("valorMensual");
    String diaMaximoDePagoStr = request.getParameter("diaMaximoDePago");
    String estadoStr = request.getParameter("estado");
    Integer inquilinoId = Integer.parseInt(request.getParameter("inquilino_id"));
    Integer propiedadId = Integer.parseInt(request.getParameter("propiedad_id"));
    String accionInput = request.getParameter("accionInput");
    String id = request.getParameter("id");
    
    // Convertir los Strings a los tipos esperados
    java.math.BigDecimal valorMensual = new java.math.BigDecimal(valorMensualStr);
    Integer diaMaximoDePago = Integer.parseInt(diaMaximoDePagoStr);
    Boolean estado = Boolean.parseBoolean(estadoStr);

    List<Map<String, Object>> listaContratos = null;

    if (request.getParameter("inquilino_id") != null && !request.getParameter("inquilino_id").isEmpty()) {
      inquilinoId = Integer.parseInt(request.getParameter("inquilino_id"));
    }

    if (request.getParameter("propiedad_id") != null && !request.getParameter("propiedad_id").isEmpty()) {
      propiedadId = Integer.parseInt(request.getParameter("propiedad_id"));
    }

    System.out.println(accionInput);

    // Configura la conexión a la base de datos
    String jdbcURL = getServletContext().getInitParameter("jdbcURL");
    String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
    String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

    Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    Contratos contratos = new Contratos(conn);

    switch (accionInput) {
      case "Ingresar":
        contratos.ingresarContrato(valorMensual, diaMaximoDePago, estado,
            inquilinoId, propiedadId);
        // ACTUALIZA LA LISTA DE CONTRATOS
        contratos.mostrarContratos();
        listaContratos = contratos.getContratos();
        request.getSession().setAttribute("contratos", listaContratos);
        if (listaContratos.isEmpty()) {
          System.out.println("No hay contratos disponibles para mostrar.");
        }
        break;
      case "editar":
        if (id != null) {
          contratos.editarContrato(id, valorMensual, diaMaximoDePago, estado,
              inquilinoId, propiedadId);
          // ACTUALIZA LA LISTA DE CONTRATOS
          contratos.mostrarContratos();
          listaContratos = contratos.getContratos();
          request.getSession().setAttribute("contratos", listaContratos);
          if (listaContratos.isEmpty()) {
            System.out.println("No hay contratos disponibles para mostrar.");
          }
        }
        break;
      case "eliminar":
        if (id != null) {
          contratos.eliminarContrato(id);
          // ACTUALIZA LA LISTA DE CONTRATOS
          contratos.mostrarContratos();
          listaContratos = contratos.getContratos();
          request.getSession().setAttribute("contratos", listaContratos);
          if (listaContratos.isEmpty()) {
            System.out.println("No hay contratos disponibles para mostrar.");
          }
        }
        break;
      case "mostrar":
        contratos.mostrarContratos();
        listaContratos = contratos.getContratos();
        request.getSession().setAttribute("contratos", listaContratos);
        if (listaContratos.isEmpty()) {
          System.out.println("No hay contratos disponibles para mostrar.");
        }
        break;
      default:
        contratos.mostrarContratos();
        break;
    }
    // Redirige a la página de contratos
    response.sendRedirect("main/contratos.jsp");
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }
}
