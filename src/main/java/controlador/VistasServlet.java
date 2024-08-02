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
                // Obtener la vista que se desea mostrar
        String vista = request.getParameter("vista");
        Conn conn = createConnection();
                // Configurar la conexión a la base de datos
        Vistas.setConn(conn);
                // Obtener los datos de la vista
        String direccion = resolveView(vista, request);
        ArrayList<Map<String, Object>> lista = Vistas.getLista();
        ArrayList<Map<String, Object>> lista2 = Vistas.getLista2();
        ArrayList<Map<String, Object>> lista3 = Vistas.getLista3();
        Map<String, Object> dato = Vistas.getDato();
                // Guardar los datos en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("lista", lista);
        session.setAttribute("lista2", lista2);
        session.setAttribute("lista3", lista3);
        session.setAttribute("dato", dato);
                // Redirigir al usuario a la vista deseada
        response.sendRedirect(direccion);
    }
    // Método para crear la conexión a la base de datos
    private Conn createConnection() {
        // Obtener los parámetros de conexión desde web.xml
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
        String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");

        return new Conn(jdbcURL, jdbcUSER, jdbcPASS);
    }

    private String resolveView(String vista, HttpServletRequest request) {
        String id = request.getParameter("id");
        // Si no se especificó una vista, devolver la vista de index
        if (vista == null) {
            return INDEX;
        }
        // Dependiendo de la vista, mostrar los datos correspondientes
        switch (vista) {
            case "Inquilinos":
                // Mostrar los inquilinos
                Vistas.mostrarInquilinos();
                // Si se especificó un id, mostrar los datos del inquilino con ese id para editar
                if (id != null) {
                    Vistas.mostrarInquilino(Integer.parseInt(id));
                }
                // Limpiar las listas anteriores para que no se duplican los datos
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
