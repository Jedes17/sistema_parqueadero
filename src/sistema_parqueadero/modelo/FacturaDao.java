package modelo;

import conexion.Conexion;
import crud.CrudFactura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDao implements CrudFactura<Factura> {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    //  Consulta general con filtros (cedula, tipo y placa)
    @Override
    public List<Factura> FacturaConFiltro(String CedulaCliente, String TipoVehiculo, String PlacaVehiculo) {
        List<Factura> lista = new ArrayList<>();
        String sql = """
            SELECT r.fecha_entrada, r.fecha_salida, r.hora_entrada, r.hora_salida,
                   v.numero_placa, v.tipo_vehiculo, f.total AS total_pagar
            FROM factura f
            INNER JOIN reserva r ON f.id_reserva = r.id
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            INNER JOIN usuarios u ON r.id_usuario = u.id
            WHERE (u.cedula LIKE ? OR ? = '')
              AND (v.tipo_vehiculo LIKE ? OR ? = '')
              AND (v.numero_placa LIKE ? OR ? = '');
        """;

        try {
            con = Conexion.getInstance().getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, "%" + CedulaCliente + "%");
            ps.setString(2, CedulaCliente);
            ps.setString(3, "%" + TipoVehiculo + "%");
            ps.setString(4, TipoVehiculo);
            ps.setString(5, "%" + PlacaVehiculo + "%");
            ps.setString(6, PlacaVehiculo);

            rs = ps.executeQuery();
            while (rs.next()) {
                Factura f = new Factura();
                f.setFechaEntrada(rs.getString("fecha_entrada"));
                f.setFechaSalida(rs.getString("fecha_salida"));
                f.setHoraEntrada(rs.getString("hora_entrada"));
                f.setHoraSalida(rs.getString("hora_salida"));
                f.setPlacaVehiculo(rs.getString("numero_placa"));
                f.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                f.setTotalPagar(rs.getDouble("total_pagar"));
                lista.add(f);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar facturas con filtro: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Consulta detallada (con más datos relacionados)
    @Override
    public List<Factura> FacturaDetallado() {
        List<Factura> lista = new ArrayList<>();
        String sql = """
            SELECT r.fecha_entrada, r.fecha_salida, r.hora_entrada, r.hora_salida,
                   v.numero_placa, v.tipo_vehiculo, f.total AS total_pagar,
                   v.modelo_vehiculo, u.cedula AS identificacion_cliente,
                   CONCAT(u.nombre, ' ', u.apellido) AS nombre_completo,
                   u.correo, v.marca_vehiculo, v.color,
                   ev.observaciones_entrada AS estado_entrada,
                   ev.observaciones_salida AS estado_salida,
                   df.subtotal
            FROM factura f
            INNER JOIN reserva r ON f.id_reserva = r.id
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            INNER JOIN usuarios u ON r.id_usuario = u.id
            LEFT JOIN detalle_factura df ON f.id = df.id_factura
            LEFT JOIN estado_vehiculo ev ON r.id = ev.id_reserva
            WHERE (u.cedula LIKE ? OR ? = '')
              AND (v.tipo_vehiculo LIKE ? OR ? = '')
              AND (v.numero_placa LIKE ? OR ? = '');
        """;

        try {
            con = Conexion.getInstance().getConnection();
            ps = con.prepareStatement(sql);

            // Valores vacíos por defecto (puedes parametrizar si lo deseas)
            String CedulaCliente = "";
            String TipoVehiculo = "";
            String PlacaVehiculo = "";

            ps.setString(1, "%" + CedulaCliente + "%");
            ps.setString(2, CedulaCliente);
            ps.setString(3, "%" + TipoVehiculo + "%");
            ps.setString(4, TipoVehiculo);
            ps.setString(5, "%" + PlacaVehiculo + "%");
            ps.setString(6, PlacaVehiculo);

            rs = ps.executeQuery();
            while (rs.next()) {
                Factura f = new Factura();
                f.setFechaEntrada(rs.getString("fecha_entrada"));
                f.setFechaSalida(rs.getString("fecha_salida"));
                f.setHoraEntrada(rs.getString("hora_entrada"));
                f.setHoraSalida(rs.getString("hora_salida"));
                f.setPlacaVehiculo(rs.getString("numero_placa"));
                f.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                f.setTotalPagar(rs.getDouble("total_pagar"));
                f.setModeloVehiculo(rs.getString("modelo_vehiculo"));
                f.setIdentificacionCliente(rs.getString("identificacion_cliente"));
                f.setNombreCompleto(rs.getString("nombre_completo"));
                f.setCorreoElectronico(rs.getString("correo"));
                f.setMarca(rs.getString("marca_vehiculo"));
                f.setColor(rs.getString("color"));
                f.setEstadoEntrada(rs.getString("estado_entrada"));
                f.setEstadoSalida(rs.getString("estado_salida"));
                f.setSubtotal(rs.getDouble("subtotal"));
                lista.add(f);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar facturas detalladas: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Cierre seguro de recursos JDBC
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("⚠️ Error al cerrar conexión: " + e.getMessage());
        }
    }
}
