package modelo;

import coneccion.Conn;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Pablo Perez
 */
public class Pagos {
    private Integer id;
    private java.sql.Timestamp fechaRegistro;
    private java.math.BigDecimal monto;
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

    public Pagos(Integer id, java.sql.Timestamp fechaRegistro, java.math.BigDecimal monto, String formaPago, String detallePago, Boolean estado, Integer inquilinoId) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.monto = monto;
        this.formaPago = formaPago;
        this.detallePago = detallePago;
        this.estado = estado;
        this.inquilinoId = inquilinoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(java.sql.Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public java.math.BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(java.math.BigDecimal monto) {
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

    private static ArrayList<Map<String, Object>> pagos = new ArrayList<>();

    public void mostrarPagos() {
        pagos.clear();
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection().prepareCall("{call obtenerPagos()}");
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Map<String, Object> pago = new HashMap<>();
                pago.put("id", resultSet.getInt("id"));
                pago.put("fechaRegistro", resultSet.getTimestamp("fechaRegistro"));
                pago.put("monto", resultSet.getBigDecimal("monto"));
                pago.put("formaPago", resultSet.getString("formaPago"));
                pago.put("detallePago", resultSet.getString("detallePago"));
                pago.put("estado", resultSet.getBoolean("estado"));
                pago.put("inquilino_id", resultSet.getInt("inquilino_id"));
                pagos.add(pago);
            }
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar pagos", e);
        }
    }

    public ArrayList<Map<String, Object>> getPagos() {
        return pagos;
    }

    public void ingresarPago(java.math.BigDecimal monto, String formaPago, String detallePago, Boolean estado, Integer inquilinoId) {
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection().prepareCall("{call ingresarPago(?, ?, ?, ?, ?)}");
            statement.setBigDecimal(1, monto);
            statement.setString(2, formaPago);
            statement.setString(3, detallePago);
            statement.setBoolean(4, estado);
            statement.setInt(5, inquilinoId);
            statement.execute();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al ingresar pago", e);
        }
    }

    public void editarPago(Integer id, java.math.BigDecimal monto, String formaPago, String detallePago, Boolean estado, Integer inquilinoId) {
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection()
                    .prepareCall("{call editarPagoPorId(?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, id);
            statement.setBigDecimal(2, monto);
            statement.setString(3, formaPago);
            statement.setString(4, detallePago);
            statement.setBoolean(5, estado);
            statement.setInt(6, inquilinoId);
            statement.execute();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al editar pago", e);
        }
    }

    public void eliminarPago(Integer id) {
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection().prepareCall("{call eliminarPagoPorId(?)}");
            statement.setInt(1, id);
            statement.execute();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar pago", e);
        }
    }
}
