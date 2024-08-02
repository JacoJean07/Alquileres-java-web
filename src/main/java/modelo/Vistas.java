package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import coneccion.Conn;

public class Vistas {

    private static ArrayList<Map<String, Object>> inquilinos = new ArrayList<>();
    private static ArrayList<Map<String, Object>> propiedades = new ArrayList<>();
    private static ArrayList<Map<String, Object>> contratos = new ArrayList<>();
    private static ArrayList<Map<String, Object>> pagos = new ArrayList<>();
    private static ArrayList<Map<String, Object>> lista = new ArrayList<>();
    private static ArrayList<Map<String, Object>> lista2 = new ArrayList<>();
    private static ArrayList<Map<String, Object>> lista3 = new ArrayList<>();
    private static Map<String, Object> dato = new HashMap<>();

    private static Conn conn;

    // Métodos estáticos para establecer la conexión
    public static void setConn(Conn connection) {
        conn = connection;
    }

    public static void mostrarInquilinos() {
        lista.clear();
        lista2.clear();
        lista3.clear();
        inquilinos.clear();
        dato.clear();
        // Obtener los inquilinos
        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();
            // Llamar al procedimiento almacenado
            CallableStatement stmt = connection.prepareCall("{CALL obtenerInquilinos}");
            ResultSet rs = stmt.executeQuery();
            // Iterar sobre los resultados para mostrarlos en la lista
            while (rs.next()) {
                Map<String, Object> inquilino = new HashMap<>();
                inquilino.put("id", rs.getInt("id"));
                inquilino.put("nombres", rs.getString("nombres"));
                inquilino.put("apellidos", rs.getString("apellidos"));
                inquilino.put("cedula", rs.getString("cedula"));
                inquilino.put("telefono", rs.getString("telefono"));
                inquilino.put("correo", rs.getString("correo"));
                inquilinos.add(inquilino);
            }
        } catch (SQLException e) {
            // Si hay algún error, imprimirlo
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Añadir los inquilinos a la lista
        lista.addAll(inquilinos);
    }
    // Método para mostrar los inquilinos por ID PARA MOSTRAR UN SOLO INQUILINO
    public static void mostrarInquilino(int id) {
        lista.clear();
        lista2.clear();
        lista3.clear();
        dato.clear();
        inquilinos.clear();

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL obtenerInquilinoPorId(?)}");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> inquilino = new HashMap<>();
                inquilino.put("id", rs.getInt("id"));
                inquilino.put("nombres", rs.getString("nombres"));
                inquilino.put("apellidos", rs.getString("apellidos"));
                inquilino.put("cedula", rs.getString("cedula"));
                inquilino.put("telefono", rs.getString("telefono"));
                inquilino.put("correo", rs.getString("correo"));
                dato = inquilino; // Añadir directamente al resultado esperado
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } 

    public static void mostrarPropiedades() {
        lista.clear();
        lista2.clear();
        lista3.clear();
        propiedades.clear();
        dato.clear();

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL obtenerPropiedades}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> propiedad = new HashMap<>();
                propiedad.put("id", rs.getInt("id"));
                propiedad.put("direccion", rs.getString("direccion"));
                propiedad.put("numero", rs.getString("numero"));
                propiedad.put("descripcion", rs.getString("descripcion"));
                propiedad.put("estado", rs.getString("estado"));
                propiedad.put("fechaRegistro", rs.getTimestamp("fechaRegistro"));
                propiedad.put("propietario_id", rs.getInt("propietario_id"));
                propiedades.add(propiedad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        lista.addAll(propiedades);
    }

    public static void mostrarPropiedad(int id) {
        lista.clear();
        lista2.clear();
        lista3.clear();
        dato.clear();
        propiedades.clear();

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL obtenerPropiedadePorId(?)}");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> propiedad = new HashMap<>();
                propiedad.put("id", rs.getInt("id"));
                propiedad.put("direccion", rs.getString("direccion"));
                propiedad.put("numero", rs.getString("numero"));
                propiedad.put("descripcion", rs.getString("descripcion"));
                propiedad.put("estado", rs.getString("estado"));
                propiedad.put("fechaRegistro", rs.getTimestamp("fechaRegistro"));
                propiedad.put("propietario_id", rs.getInt("propietario_id"));
                dato = propiedad; // Añadir directamente al resultado esperado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        lista2.addAll(propiedades);
    }

    public static void mostrarContratos() {
        lista.clear();
        lista2.clear();
        lista3.clear();
        contratos.clear();
        dato.clear();

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL obtenerContratos}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> contrato = new HashMap<>();
                contrato.put("id", rs.getInt("id"));
                contrato.put("valorMensual", rs.getBigDecimal("valorMensual"));
                contrato.put("diaMaximoDePago", rs.getInt("diaMaximoDePago"));
                contrato.put("fechaInicioContrato", rs.getTimestamp("fechaInicioContrato"));
                contrato.put("fechaFinContrato", rs.getTimestamp("fechaFinContrato"));
                contrato.put("estado", rs.getString("estado"));
                contrato.put("inquilino_id", rs.getInt("inquilino_id"));
                contrato.put("propiedad_id", rs.getInt("propiedad_id"));
                contratos.add(contrato);
            }

            CallableStatement stmt2 = connection.prepareCall("{CALL obtenerInquilinos}");
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                Map<String, Object> inquilino = new HashMap<>();
                inquilino.put("id", rs2.getInt("id"));
                inquilino.put("nombres", rs2.getString("nombres"));
                inquilino.put("apellidos", rs2.getString("apellidos"));
                inquilino.put("cedula", rs2.getString("cedula"));
                inquilino.put("telefono", rs2.getString("telefono"));
                inquilino.put("correo", rs2.getString("correo"));
                inquilinos.add(inquilino);
            }

            CallableStatement stmt3 = connection.prepareCall("{CALL obtenerPropiedades}");
            ResultSet rs3 = stmt3.executeQuery();

            while (rs3.next()) {
                Map<String, Object> propiedad = new HashMap<>();
                propiedad.put("id", rs3.getInt("id"));
                propiedad.put("direccion", rs3.getString("direccion"));
                propiedad.put("numero", rs3.getString("numero"));
                propiedad.put("descripcion", rs3.getString("descripcion"));
                propiedad.put("estado", rs3.getString("estado"));
                propiedad.put("fechaRegistro", rs3.getTimestamp("fechaRegistro"));
                propiedad.put("propietario_id", rs3.getInt("propietario_id"));
                propiedades.add(propiedad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        lista.addAll(contratos);
        lista2.addAll(propiedades);
        lista3.addAll(inquilinos);
    }

    public static void mostrarContrato(int id) {
        lista.clear();
        lista2.clear();
        lista3.clear();
        dato.clear();
        contratos.clear();

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL obtenerContratoPorId(?)}");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> contrato = new HashMap<>();
                contrato.put("id", rs.getInt("id"));
                contrato.put("valorMensual", rs.getBigDecimal("valorMensual"));
                contrato.put("diaMaximoDePago", rs.getInt("diaMaximoDePago"));
                contrato.put("fechaInicioContrato", rs.getTimestamp("fechaInicioContrato"));
                contrato.put("fechaFinContrato", rs.getTimestamp("fechaFinContrato"));
                contrato.put("estado", rs.getBoolean("estado"));
                contrato.put("inquilino_id", rs.getInt("inquilino_id"));
                contrato.put("propiedad_id", rs.getInt("propiedad_id"));
                dato = contrato; // Añadir directamente al resultado esperado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mostrarPagos() {
        lista.clear();
        lista2.clear();
        lista3.clear();
        pagos.clear();
        dato.clear();

        try {
            conn.connect(); // Conecta a la base de datos
            Connection connection = conn.getJdbcConnection();

            CallableStatement stmt = connection.prepareCall("{CALL obtenerPagos}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> pago = new HashMap<>();
                pago.put("id", rs.getInt("id"));
                pago.put("fechaRegistro", rs.getTimestamp("fechaRegistro"));
                pago.put("monto", rs.getBigDecimal("monto"));
                pago.put("formaPago", rs.getString("formaPago"));
                pago.put("detallePago", rs.getString("detallePago"));
                pago.put("estado", rs.getBoolean("estado"));
                pago.put("inquilino_id", rs.getInt("inquilino_id"));
                pagos.add(pago);
            }

            CallableStatement stmt3 = connection.prepareCall("{CALL obtenerInquilinos}");
            ResultSet rs3 = stmt3.executeQuery();

            while (rs3.next()) {
                Map<String, Object> inquilino = new HashMap<>();
                inquilino.put("id", rs3.getInt("id"));
                inquilino.put("nombres", rs3.getString("nombres"));
                inquilino.put("apellidos", rs3.getString("apellidos"));
                inquilino.put("cedula", rs3.getString("cedula"));
                inquilino.put("telefono", rs3.getString("telefono"));
                inquilino.put("correo", rs3.getString("correo"));
                inquilinos.add(inquilino);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.disconnect(); // Cierra la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        lista.addAll(pagos);
        lista3.addAll(inquilinos);
    }

    public static ArrayList<Map<String, Object>> getLista() {
        return lista;
    }

    public static ArrayList<Map<String, Object>> getLista2() {
        return lista2;
    }

    public static ArrayList<Map<String, Object>> getLista3() {
        return lista3;
    }

    public static Map<String, Object> getDato() {
        if (dato.isEmpty()) {
            return null;
        } else {
            return dato;
        }
    }

    public static void limpiarListas() {
        lista.clear();
        lista2.clear();
        lista3.clear();
    }   
}
