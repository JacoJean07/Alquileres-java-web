package modelo;

import coneccion.Conn;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * Modelo para gestionar pagos.
 * 
 * @autor Pablo Perez
 */
public class Pagos {
    private Integer id;
    private Timestamp fechaRegistro;
    private BigDecimal monto;
    private String formaPago;
    private String detallePago;
    private Boolean estado;
    private Integer inquilinoId;
    private Conn conn;

    public Pagos(Conn conn) {
        this.conn = conn;
    }

    public Pagos() {
    }

    public Pagos(Integer id, Timestamp fechaRegistro, BigDecimal monto, String formaPago, String detallePago, Boolean estado, Integer inquilinoId) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.monto = monto;
        this.formaPago = formaPago;
        this.detallePago = detallePago;
        this.estado = estado;
        this.inquilinoId = inquilinoId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(String detallePago) {
        this.detallePago = detallePago;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(Integer inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public void insertarPago() {
        if (conn == null) {
            throw new RuntimeException("Conexión no establecida.");
        }

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement = connection.prepareCall("{CALL ingresarPago( ?, ?, ?, ?, ?)}");
            statement.setBigDecimal(1, monto);
            statement.setString(2, formaPago);
            statement.setString(3, detallePago);
            statement.setBoolean(4, estado);
            statement.setInt(5, inquilinoId);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error SQL, no se pudo insertar el pago.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }

    public void editarPago() {
        if (conn == null) {
            throw new RuntimeException("Conexión no establecida.");
        }

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement = connection.prepareCall("{CALL editarPagoPorId(?, ?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, id);
            statement.setTimestamp(2, fechaRegistro);
            statement.setBigDecimal(3, monto);
            statement.setString(4, formaPago);
            statement.setString(5, detallePago);
            statement.setBoolean(6, estado);
            statement.setInt(7, inquilinoId);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error SQL, no se pudo editar el pago.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }

    public void eliminarPago() {
        if (conn == null) {
            throw new RuntimeException("Conexión no establecida.");
        }

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement statement = connection.prepareCall("{CALL eliminarPagoPorId(?)}");
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error SQL, no se pudo eliminar el pago.", e);
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }
}
