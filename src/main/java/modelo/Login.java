package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import coneccion.Conn;

public class Login {

    private String id;
    private String usuario;
    private String password;
    private Conn conn;

    public Login(String usuario, String password, Conn conn) {
        this.usuario = usuario;
        this.password = password;
        this.conn = conn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return usuario;
    }

    public void setUser(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean VerificarContrasena(String password, String usuario) {
        String sql = "SELECT id, password FROM propietario WHERE usuario = ?";
        try {
            conn.connect();
            Connection connection = conn.getJdbcConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    if (storedPassword.equals(password)) {
                        this.id = resultSet.getString("id"); // Asigna el id del usuario
                        return true;
                    }
                }
            }
            statement.close();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar el usuario", e);
        }

        return false;
    }
}
