package modelo;

import conexion.Conexion;
import modelo.EstadoVehicular;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoVehicularDao {

    private Connection con;

    public EstadoVehicularDao() throws Exception {
        con = Conexion.getInstance().getConnection();
    }

    //Consulta general con filtros (Tipo, Placa, Cliente, Operador)
    public List<EstadoVehicular> consultarConFiltros(String tipoVehiculo, String placa, String cedulaCliente, String cedulaOperador) throws SQLException {
        List<EstadoVehicular> lista = new ArrayList<>();

        String sql = """
        SELECT 
            r.fecha_entrada, r.hora_entrada, 
            r.fecha_salida, r.hora_salida,
            v.numero_placa, v.tipo_vehiculo,
            l.numero AS lugar_reservado,
            r.estado AS estado_reserva,
            u.cedula AS cedula_cliente,
            op.cedula AS cedula_operador
        FROM reserva r
        INNER JOIN vehiculo v ON r.id_vehiculo = v.id
        INNER JOIN usuarios u ON r.id_usuario = u.id
        INNER JOIN lugar l ON r.id_lugar = l.id
        LEFT JOIN estado_vehiculo ev ON ev.id_reserva = r.id
        LEFT JOIN usuarios op ON ev.registrado_por = op.id
        WHERE (v.tipo_vehiculo LIKE ? OR ? = '')
          AND (v.numero_placa LIKE ? OR ? = '')
          AND (u.cedula LIKE ? OR ? = '')
          AND (COALESCE(op.cedula, '') LIKE ? OR ? = '')
        ORDER BY r.fecha_entrada DESC
        """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tipoVehiculo);
        ps.setString(2, tipoVehiculo);
        ps.setString(3, placa);
        ps.setString(4, placa);
        ps.setString(5, cedulaCliente);
        ps.setString(6, cedulaCliente);
        ps.setString(7, cedulaOperador);
        ps.setString(8, cedulaOperador);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            EstadoVehicular ev = new EstadoVehicular();

            ev.setFechaIngreso(rs.getTimestamp("fecha_entrada"));
            ev.setHoraEntrada(rs.getString("hora_entrada"));
            ev.setFechaSalida(rs.getTimestamp("fecha_salida"));
            ev.setHoraSalida(rs.getString("hora_salida"));
            ev.setPlacaVehiculo(rs.getString("numero_placa"));
            ev.setTipoVehiculo(rs.getString("tipo_vehiculo"));
            ev.setLugarReservado(rs.getString("lugar_reservado"));
            ev.setEstadoGeneral(rs.getString("estado_reserva"));
            ev.setCedulaCliente(rs.getString("cedula_cliente"));
            ev.setCedulaOperador(rs.getString("cedula_operador"));

            lista.add(ev);
        }

        return lista;
    }
    // Consulta solo "Activa"
    public List<EstadoVehicular> consultarActivas(String tipoVehiculo, String placa, String cedulaCliente) throws SQLException {
        List<EstadoVehicular> lista = new ArrayList<>();

        String sql = """
            SELECT r.fecha_entrada, r.hora_entrada, r.fecha_salida, r.hora_salida,
                   v.numero_placa, v.tipo_vehiculo, l.numero AS lugar_reservado, r.estado
            FROM reserva r
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            INNER JOIN usuarios u ON r.id_usuario = u.id
            INNER JOIN lugar l ON r.id_lugar = l.id
            WHERE r.estado = 'Activa'
              AND (v.tipo_vehiculo LIKE ? OR ? = '')
              AND (v.numero_placa LIKE ? OR ? = '')
              AND (u.cedula LIKE ? OR ? = '')
            """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tipoVehiculo);
        ps.setString(2, tipoVehiculo);
        ps.setString(3, placa);
        ps.setString(4, placa);
        ps.setString(5, cedulaCliente);
        ps.setString(6, cedulaCliente);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            EstadoVehicular ev = new EstadoVehicular();
            ev.setFechaIngreso(rs.getTimestamp("fecha_entrada"));
            ev.setFechaSalida(rs.getTimestamp("fecha_salida"));
            ev.setEstadoGeneral(rs.getString("estado"));
            lista.add(ev);
        }
        return lista;
    }

    // Insert entrada
    public boolean registrarEntrada(EstadoVehicular ev) throws SQLException {
        String sql = """
            INSERT INTO estado_vehiculo (id_reserva, observaciones_entrada, fecha_ingreso, foto_entrada, registrado_por)
            VALUES (?, ?, ?, ?, ?)
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ev.getIdReserva());
        ps.setString(2, ev.getObservacionesEntrada());
        ps.setTimestamp(3, new Timestamp(ev.getFechaIngreso().getTime()));
        ps.setString(4, ev.getFotoEntrada());
        ps.setInt(5, ev.getRegistradoPor());
        return ps.executeUpdate() > 0;
    }

    // Insert salida
    public boolean registrarSalida(EstadoVehicular ev) throws SQLException {
        String sql = """
            UPDATE estado_vehiculo
            SET observaciones_salida = ?, estado_general = ?, fecha_salida = ?, foto_salida = ?
            WHERE id_reserva = ?
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ev.getObservacionesSalida());
        ps.setString(2, ev.getEstadoGeneral());
        ps.setTimestamp(3, new Timestamp(ev.getFechaSalida().getTime()));
        ps.setString(4, ev.getFotoSalida());
        ps.setInt(5, ev.getIdReserva());
        return ps.executeUpdate() > 0;
    }

    // Observación de entrada por placa
    public String obtenerObservacionEntrada(String placa) throws SQLException {
        String sql = """
            SELECT ev.observaciones_entrada
            FROM estado_vehiculo ev
            INNER JOIN reserva r ON ev.id_reserva = r.id
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            WHERE v.numero_placa = ?
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, placa);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getString("observaciones_entrada") : null;
    }

    // Consulta detallada
    public ResultSet obtenerDetallePorPlaca(String placa) throws SQLException {
        String sql = """
            SELECT r.fecha_entrada, r.hora_entrada, r.fecha_salida, r.hora_salida,
                   v.tipo_vehiculo, r.estado AS estado_reserva, v.numero_placa,
                   v.modelo_vehiculo, v.color, v.marca_vehiculo,
                   u.cedula AS identificacion_cliente,
                   CONCAT(u.nombre, ' ', u.apellido) AS nombre_cliente,
                   u.correo, u.telefono,
                   ev.observaciones_entrada, ev.observaciones_salida,
                   ev.estado_general, ev.foto_salida, ev.foto_entrada,
                   op.cedula AS identificacion_operador
            FROM estado_vehiculo ev
            INNER JOIN reserva r ON ev.id_reserva = r.id
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            INNER JOIN usuarios u ON r.id_usuario = u.id
            LEFT JOIN usuarios op ON ev.registrado_por = op.id
            WHERE v.numero_placa = ?
        """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, placa);
        return ps.executeQuery();
    }
    // Actualiza el estado de la reserva a "cancelada" según la placa del vehículo
    public boolean actualizarEstadoReserva(String placa, String nuevoEstado) throws SQLException {
        String sql = """
            UPDATE reserva r
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            SET r.estado = ?
            WHERE v.numero_placa = ? AND r.estado = 'Confirmada'
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado.toLowerCase()); // 'cancelada'
            ps.setString(2, placa);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
    // Actualiza los datos de una reserva (fecha/hora entrada y salida)
    public boolean actualizarReserva(String placa, java.util.Date fechaEntrada, String horaEntrada,
                                     java.util.Date fechaSalida, String horaSalida) throws SQLException {
        String sql = """
            UPDATE reserva r
            INNER JOIN vehiculo v ON r.id_vehiculo = v.id
            SET r.fecha_entrada = ?, r.hora_entrada = ?, 
                r.fecha_salida = ?, r.hora_salida = ?
            WHERE v.numero_placa = ? AND r.estado = 'Confirmada'
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(fechaEntrada.getTime()));
            ps.setString(2, horaEntrada);
            ps.setTimestamp(3, new java.sql.Timestamp(fechaSalida.getTime()));
            ps.setString(4, horaSalida);
            ps.setString(5, placa);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
}
