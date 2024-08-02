package modelo;

import coneccion.Conn;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase que maneja las operaciones CRUD para propiedades.
 */
public class Propiedades {
    private Integer id;
    private String direccion;
    private String numero;
    private String descripcion;
    private String estado; // Asumimos que es un ENUM, así que podrías usar una constante o un tipo específico si lo prefieres
    private java.util.Date fechaRegistro;
    private Integer propietarioId;
    private Conn conn;

    public Propiedades(Conn conn) {
        this.conn = conn;
    }

    public Propiedades() {
    }

    public Propiedades(Integer id, String direccion, String numero, String descripcion, String estado, java.util.Date fechaRegistro, Integer propietarioId) {
        this.id = id;
        this.direccion = direccion;
        this.numero = numero;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.propietarioId = propietarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public java.util.Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(java.util.Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Integer propietarioId) {
        this.propietarioId = propietarioId;
    }

    public void EditarPropiedad() {
        if (conn == null) {
            throw new RuntimeException("Coneccion no establecida.");
        }

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement;
            statement = connection.prepareCall("{CALL editarPropiedadPorId(?, ?, ?, ?, ?)}");
            statement.setInt(1, id);
            statement.setString(2, direccion);
            statement.setString(3, numero);
            statement.setString(4, descripcion);
            statement.setString(5, estado);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error, no se pudo editar la propiedad.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error occurred while disconnecting.", e);
            }
        }
    }   

    public void insertarPropiedad() {
        if (conn == null) {
            throw new RuntimeException("Coneccion no establecida.");
        }

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement;
            statement = connection.prepareCall("{CALL ingresarPropiedad(?, ?, ?, ?, ?)}");
            statement.setString(1, direccion);
            statement.setString(2, numero);
            statement.setString(3, descripcion);
            statement.setString(4, estado);
            statement.setInt(5, propietarioId);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error, no se pudo insertar la propiedad.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error occurred while disconnecting.", e);
            }
        }
    }   

    public void eliminarPropiedad() {
        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL eliminarPropiedadPorId(?)}");
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error SQL, no se pudo eliminar la propiedad.", e);
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
