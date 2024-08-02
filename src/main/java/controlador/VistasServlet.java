package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

import coneccion.Conn;
import modelo.Vistas;

public class VistasServlet extends HttpServlet {

    // Rutas absolutas
    private static final String INDEX = "main/index.jsp";
    private static final String INQUILINOS = "main/inquilinos.jsp";
    private static final String PROPIEDADES = "main/propiedades.jsp";
    private static final String CONTRATOS = "main/contratos.jsp";
    private static final String PAGOS = "main/pagos.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String vista = request.getParameter("vista");
        Conn conn = createConnection();

        Vistas.setConn(conn);

        String direccion = resolveView(vista, request);
        ArrayList<Map<String, Object>> lista = Vistas.getLista();
        ArrayList<Map<String, Object>> lista2 = Vistas.getLista2();
        ArrayList<Map<String, Object>> lista3 = Vistas.getLista3();
        Map<String, Object> dato = Vistas.getDato();

        HttpSession session = request.getSession();
        session.setAttribute("lista", lista);
        session.setAttribute("lista2", lista2);
        session.setAttribute("lista3", lista3);
        session.setAttribute("dato", dato);

        response.sendRedirect(direccion);
    }

    private Conn createConnection() {
        // Obtener los parámetros de conexión desde web.xml
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
        String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

        return new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    }

    private String resolveView(String vista, HttpServletRequest request) {
        String id = request.getParameter("id");

        if (vista == null) {
            return INDEX;
        }

        switch (vista) {
            case "Inquilinos":
                Vistas.mostrarInquilinos();
                if (id != null) {
                    Vistas.mostrarInquilino(Integer.parseInt(id));
                }
                Vistas.limpiarListas();
                return INQUILINOS;
            case "Propiedades":
                Vistas.mostrarPropiedades();
                if (id != null) {
                    Vistas.mostrarPropiedad(Integer.parseInt(id));
                }
                Vistas.limpiarListas();
                return PROPIEDADES;
            case "Contratos":
                Vistas.mostrarContratos();
                if (id != null) {
                    Vistas.mostrarContrato(Integer.parseInt(id));
                }
                Vistas.limpiarListas();
                return CONTRATOS;
            case "Pagos":
                Vistas.mostrarPagos();
                Vistas.limpiarListas();
                return PAGOS;
            case "index":
                return INDEX;
            default:
                return INDEX;
        }
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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
