package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import coneccion.Conn;

public class EDITAR {

    private static Map<String, Object> dato = new HashMap<>();

    private static Conn conn;

    public static void setConn(Conn connection) {
        conn = connection;
    }

    public static void mostrarInquilino(Integer id) {
        dato.clear();

        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conn.getJdbcConnection();
            stmt = connection.prepareCall("{call obtenerInquilinoPorId(?)}");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> inquilino = new HashMap<>();
                inquilino.put("id", rs.getInt("id"));
                inquilino.put("nombres", rs.getString("nombres"));
                inquilino.put("apellidos", rs.getString("apellidos"));
                inquilino.put("cedula", rs.getString("cedula"));
                inquilino.put("telefono", rs.getString("telefono"));
                inquilino.put("correo", rs.getString("correo"));
                dato = inquilino;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar inquilino", e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar conexión", e);
            }
        }
    }

    public static void mostrarPropiedadPorId(Integer id) {
        dato.clear();

        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conn.getJdbcConnection();
            stmt = connection.prepareCall("{call obtenerPropiedadPorId(?)}");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> propiedad = new HashMap<>();
                propiedad.put("id", rs.getInt("id"));   
                propiedad.put("direccion", rs.getString("direccion"));
                propiedad.put("numero", rs.getString("numero"));
                propiedad.put("descripcion", rs.getString("descripcion"));
                propiedad.put("estado", rs.getString("estado"));
                dato = propiedad;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar propiedad", e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar conexión", e);
            }
        }
    }   

    public static void mostrarContratoPorId(Integer id) {
        dato.clear();

        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conn.getJdbcConnection();
            stmt = connection.prepareCall("{call obtenerContratoPorId(?)}");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> contrato = new HashMap<>();
                contrato.put("id", rs.getInt("id"));
                contrato.put("valorMensual", rs.getDouble("valorMensual"));
                contrato.put("diaMaximoDePago", rs.getInt("diaMaximoDePago"));
                contrato.put("fechaInicioContrato", rs.getDate("fechaInicioContrato"));
                contrato.put("fechaFinContrato", rs.getDate("fechaFinContrato"));   
                contrato.put("estado", rs.getString("estado"));
                dato = contrato;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar contrato", e);
        } finally { 
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar conexión", e);
            }
        }
    }

    public static void mostrarPagoPorId(Integer id) {
        dato.clear();

        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = conn.getJdbcConnection();
            stmt = connection.prepareCall("{call obtenerPagoPorId(?)}");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> pago = new HashMap<>();
                pago.put("id", rs.getInt("id"));
                pago.put("fechaRegistro", rs.getDate("fechaRegistro"));
                pago.put("monto", rs.getDouble("monto"));
                pago.put("formaPago", rs.getString("formaPago"));
                pago.put("detallePago", rs.getString("detallePago"));
                pago.put("estado", rs.getString("estado"));
                pago.put("inquilino_id", rs.getInt("inquilino_id"));
                dato = pago;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar pago", e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar conexión", e);
            }
        }
    }

    public static Map<String, Object> getDato() {
        if (dato.isEmpty()) {
            return null;
        } else {
            return dato;
        }
    }
}
