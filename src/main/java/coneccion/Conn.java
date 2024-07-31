package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private String jdbcURL;
    private String jdbcUSER;
    private String jdbcPASS;
    
    private Connection jdbcConnection;
    
    public Conn(String jdbcURL, String jdbcUSER, String jdbcPASS){
        this.jdbcURL = jdbcURL;
        this.jdbcUSER = jdbcUSER;
        this.jdbcPASS = jdbcPASS;
    }
    
    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver registrado exitosamente.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver.");
                ex.printStackTrace();
                throw new SQLException(ex);
            }
            
            try {
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUSER, jdbcPASS);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (SQLException ex) {
                System.out.println("Error al establecer la conexión con la base de datos.");
                ex.printStackTrace();
                throw ex;
            }
        }
    }
    
    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
            System.out.println("Conexión cerrada.");
        }
    }
    
    public Connection getJdbcConnection() {
        return jdbcConnection;
    }
}
