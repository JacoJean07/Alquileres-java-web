package modelo;

import coneccion.Conn;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author Pablo Perez
 */
public class Inquilinos {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String correo;
    private Conn conn;

    public Inquilinos(Conn conn) {
        this.conn = conn;
    }

    public Inquilinos() {
    }

    public Inquilinos(Integer id, String nombres, String apellidos, String cedula, String telefono, String correo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void EditarInquilino() {
        if (conn == null) {
            throw new RuntimeException("Coneccion no establecida.");
        }

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement;
            statement = connection.prepareCall("{CALL editarInquilinoPorId(?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, id);
            statement.setString(2, nombres);
            statement.setString(3, apellidos);
            statement.setString(4, cedula);
            statement.setString(5, telefono);
            statement.setString(6, correo);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error, no se pudo editar el inquilino.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }

    public void insertarInquilino() {
        if (conn == null) {
            throw new RuntimeException("Coneccion no establecida.");
        }
        
        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement;
            statement = connection.prepareCall("{CALL ingresarInquilino(?, ?, ?, ?, ?)}");
            statement.setString(1, nombres);
            statement.setString(2, apellidos);
            statement.setString(3, cedula);
            statement.setString(4, telefono);
            statement.setString(5, correo);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error, no se pudo insertar el inquilino.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }

    public void eliminarInquilino() {
        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL eliminarInquilinoPorId(?)}");
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error, no se pudo eliminar el inquilino.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error occurred while disconnecting.", e);
            }
        }
    }
}
