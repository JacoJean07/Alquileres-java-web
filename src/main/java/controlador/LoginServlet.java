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
    private final String MAIN_PAGE = "main/index.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUSER = getServletContext().getInitParameter("jdbcUSER");
        String jdbcPASS = getServletContext().getInitParameter("jdbcPASS");
        
        Conn conn = new Conn(jdbcURL, jdbcUSER, jdbcPASS);

        HttpSession session = request.getSession();
        Login login = (Login) session.getAttribute("login");
        if (login == null) {
            login = new Login(usuario, password, conn);
            session.setAttribute("login", login);
        } else {
            login.setUser(usuario);
            login.setPassword(password);
        }

        if (login.VerificarContrasena(password, usuario)) {
            String id = login.getId();
            session.setAttribute("id_usuario", id);

            response.sendRedirect(MAIN_PAGE);
        } else {
            request.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">Credenciales inválidas.</div>");
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
