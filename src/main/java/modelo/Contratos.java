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
 * @author jeanc
 */
public class Contratos {
    private String id;
    private java.math.BigDecimal valorMensual;
    private Integer diaMaximoDePago;
    private java.sql.Timestamp fechaInicioContrato;
    private java.sql.Timestamp fechaFinContrato;
    private Boolean estado;
    private Integer inquilinoId;
    private Integer propiedadId;
    private Conn conn;

    public Contratos(Conn conn) {
        this.conn = conn;
    }

    public Contratos() {
    }

    public Contratos(String id, java.math.BigDecimal valorMensual, Integer diaMaximoDePago, java.sql.Timestamp fechaInicioContrato, java.sql.Timestamp fechaFinContrato, Boolean estado, Integer inquilinoId, Integer propiedadId) {
        this.id = id;
        this.valorMensual = valorMensual;
        this.diaMaximoDePago = diaMaximoDePago;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinContrato = fechaFinContrato;
        this.estado = estado;
        this.inquilinoId = inquilinoId;
        this.propiedadId = propiedadId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.math.BigDecimal getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(java.math.BigDecimal valorMensual) {
        this.valorMensual = valorMensual;
    }

    public Integer getDiaMaximoDePago() {
        return diaMaximoDePago;
    }

    public void setDiaMaximoDePago(Integer diaMaximoDePago) {
        this.diaMaximoDePago = diaMaximoDePago;
    }

    public java.sql.Timestamp getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(java.sql.Timestamp fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public java.sql.Timestamp getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(java.sql.Timestamp fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
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

    public Integer getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(Integer propiedadId) {
        this.propiedadId = propiedadId;
    }

    private static ArrayList<Map<String, Object>> contratos = new ArrayList<>();

    public void mostrarContratos() {
        contratos.clear();
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection().prepareCall("{call obtenerContratos()}");
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Map<String, Object> contrato = new HashMap<>();
                contrato.put("id", resultSet.getInt("id"));
                contrato.put("valorMensual", resultSet.getBigDecimal("valorMensual"));
                contrato.put("diaMaximoDePago", resultSet.getInt("diaMaximoDePago"));
                contrato.put("fechaInicioContrato", resultSet.getTimestamp("fechaInicioContrato"));
                contrato.put("fechaFinContrato", resultSet.getTimestamp("fechaFinContrato"));
                contrato.put("estado", resultSet.getBoolean("estado"));
                contrato.put("inquilino_id", resultSet.getInt("inquilino_id"));
                contrato.put("propiedad_id", resultSet.getInt("propiedad_id"));
                contratos.add(contrato);
            }
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar contratos", e);
        }
    }

    public ArrayList<Map<String, Object>> getContratos() {
        return contratos;
    }

    public void ingresarContrato(java.math.BigDecimal valorMensual, Integer diaMaximoDePago, Boolean estado, Integer inquilinoId, Integer propiedadId) {
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection().prepareCall("{call ingresarContrato(?, ?, ?, ?, ?)}");
            statement.setBigDecimal(1, valorMensual);
            statement.setInt(2, diaMaximoDePago);
            statement.setBoolean(3, estado);
            statement.setInt(4, inquilinoId);
            statement.setInt(5, propiedadId);
            statement.execute();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al ingresar contrato", e);
        }
    }

    public void editarContrato(String id, java.math.BigDecimal valorMensual, Integer diaMaximoDePago, Boolean estado, Integer inquilinoId, Integer propiedadId) {
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection()
                    .prepareCall("{call editarContratoPorId(?, ?, ?, ?, ?, ?)}");
            statement.setString(1, id);
            statement.setBigDecimal(2, valorMensual);
            statement.setInt(3, diaMaximoDePago);
            statement.setBoolean(4, estado);
            statement.setInt(5, inquilinoId);
            statement.setInt(6, propiedadId);
            statement.execute();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al editar contrato", e);
        }
    }

    public void eliminarContrato(String id) {
        try {
            conn.connect();
            CallableStatement statement = conn.getJdbcConnection().prepareCall("{call eliminarContratoPorId(?)}");
            statement.setString(1, id);
            statement.execute();
            conn.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar contrato", e);
        }
    }
}
