package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Login;
import coneccion.Conn;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String LOGIN_PAGE = "index.jsp";
    private final String MAIN_PAGE = "VistasServlet?vista=index";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                // Obtener los datos del formulario
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
                // Configura la conexión a la base de datos
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
        String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");
                // Crear la conexión a la base de datos
        Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);
                // Crear un objeto de tipo Login
        HttpSession session = request.getSession();
        Login login = (Login) session.getAttribute("login");
        if (login == null) {
            login = new Login(usuario, password, conn);
            session.setAttribute("login", login);
        } else {
            login.setUser(usuario);
            login.setPassword(password);
        }
                // Verificar la contraseña del usuario
        if (login.VerificarContrasena(password, usuario)) {
            String id = login.getId();
            session.setAttribute("id_usuario", id);
                // Si se pudo verificar la contraseña, redirigir al usuario a la página principal
            response.sendRedirect(MAIN_PAGE);
        } else {
                // Si no se pudo verificar la contraseña, mostrar un mensaje de error
            request.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">Credenciales inválidas.</div>");
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
