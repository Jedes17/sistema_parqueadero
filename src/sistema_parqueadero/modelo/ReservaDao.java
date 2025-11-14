package modelo;

import crud.CrudReserva;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao implements CrudReserva<Reserva> {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    //SOLICITAR RESERVA
    @Override
    public int SolicitarReserva(Reserva r) {
        int filas = 0;
        String sql = "INSERT INTO reserva (id_usuario, id_vehiculo, id_lugar, fecha_entrada, hora_entrada, fecha_salida, hora_salida, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, 'pendiente')";
        try {
            con = Conexion.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, r.getIdUsuario());
            ps.setInt(2, r.getIdVehiculo());
            ps.setInt(3, r.getIdLugar());
            ps.setString(4, r.getFechaEntrada());
            ps.setString(5, r.getHoraEntrada());
            ps.setString(6, r.getFechaSalida());
            ps.setString(7, r.getHoraSalida());
            filas = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("❌ Error al solicitar reserva: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return filas;
    }

    // FILTRO DE SOLICITUD DE RESERVA
    @Override
    public List<Reserva> FiltroSolicitudDeReserva(String tipoVehiculo, String placa, String cedula) {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.id, u.cedula, v.numero_placa, v.tipo_vehiculo, " +
                     "r.fecha_entrada, r.hora_entrada, r.fecha_salida, r.hora_salida, r.estado " +
                     "FROM reserva r " +
                     "JOIN usuarios u ON r.id_usuario = u.id " +
                     "JOIN vehiculo v ON r.id_vehiculo = v.id " +
                     "WHERE v.tipo_vehiculo LIKE ? AND v.numero_placa LIKE ? AND u.cedula LIKE ?";

        try {
            con = Conexion.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tipoVehiculo + "%");
            ps.setString(2, "%" + placa + "%");
            ps.setString(3, "%" + cedula + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setCedulaUsuario(rs.getString("cedula"));
                r.setPlacaVehiculo(rs.getString("numero_placa"));
                r.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                r.setFechaEntrada(rs.getString("fecha_entrada"));
                r.setHoraEntrada(rs.getString("hora_entrada"));
                r.setFechaSalida(rs.getString("fecha_salida"));
                r.setHoraSalida(rs.getString("hora_salida"));
                r.setEstado(rs.getString("estado"));
                lista.add(r);
            }

        } catch (Exception e) {
            System.out.println("❌ Error en filtro de reservas: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // RESERVA DETALLADA
    @Override
    public List<Reserva> ReservaDetallada() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.*, u.nombre AS nombre_usuario, u.cedula, v.numero_placa, v.tipo_vehiculo, " +
                     "l.numero AS nombre_lugar, l.disponible, c.nombre AS nombre_cuadrante " +
                     "FROM reserva r " +
                     "JOIN usuarios u ON r.id_usuario = u.id " +
                     "JOIN vehiculo v ON r.id_vehiculo = v.id " +
                     "JOIN lugar l ON r.id_lugar = l.id " +
                     "JOIN cuadrante c ON l.id_cuadrante = c.id";

        try {
            con = Conexion.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setNombreUsuario(rs.getString("nombre_usuario"));
                r.setCedulaUsuario(rs.getString("cedula"));
                r.setPlacaVehiculo(rs.getString("numero_placa"));
                r.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                r.setNumeroLugar(rs.getString("nombre_lugar"));
                r.setNombreCuadrante(rs.getString("nombre_cuadrante"));
                r.setDisponibilidad(rs.getBoolean("disponible"));
                r.setFechaEntrada(rs.getString("fecha_entrada"));
                r.setHoraEntrada(rs.getString("hora_entrada"));
                r.setFechaSalida(rs.getString("fecha_salida"));
                r.setHoraSalida(rs.getString("hora_salida"));
                r.setEstado(rs.getString("estado"));
                lista.add(r);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener reserva detallada: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    // 🔹 NUEVOS MÉTODOS AUXILIARES PARA IDs REALES
    public int obtenerIdUsuarioPorCedula(String cedula) {
        int id = -1;
        String sql = "SELECT id FROM usuarios WHERE cedula = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) id = rs.getInt("id");
        } catch (Exception e) {
            System.out.println("❌ Error al obtener ID de usuario: " + e.getMessage());
        }
        return id;
    }

    public int obtenerIdVehiculoPorPlaca(String placa) {
        int id = -1;
        String sql = "SELECT id FROM vehiculo WHERE numero_placa = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) id = rs.getInt("id");
        } catch (Exception e) {
            System.out.println("❌ Error al obtener ID de vehículo: " + e.getMessage());
        }
        return id;
    }

    public int obtenerIdLugarPorNumero(String numeroLugar) {
        int id = -1;
        String sql = "SELECT id FROM lugar WHERE numero = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numeroLugar.replace("Lugar ", ""));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) id = rs.getInt("id");
        } catch (Exception e) {
            System.out.println("❌ Error al obtener ID del lugar: " + e.getMessage());
        }
        return id;
    }

    // Vehículos del usuario por tipo
    public List<String[]> obtenerVehiculosUsuarioPorTipo(int idUsuario, String tipoVehiculo) {
        List<String[]> vehiculos = new ArrayList<>();
        String sql = """
            SELECT v.id, v.numero_placa 
            FROM vehiculo v
            JOIN usuario_vehiculo uv ON uv.id_vehiculo = v.id
            WHERE uv.id_usuario = ? AND v.tipo_vehiculo = ?
        """;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, tipoVehiculo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                vehiculos.add(new String[]{
                    rs.getString("id"),
                    rs.getString("numero_placa")
                });
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener vehículos del usuario: " + e.getMessage());
        }
        return vehiculos;
    }

    // Lugares disponibles por tipo de vehículo
    public List<String[]> obtenerLugaresDisponiblesPorTipo(String tipoVehiculo) {
        List<String[]> lugares = new ArrayList<>();
        String sql = """
            SELECT l.id, l.numero
            FROM lugar l
            JOIN cuadrante c ON l.id_cuadrante = c.id
            WHERE c.tipo_vehiculo = ? AND l.disponible = 1
        """;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoVehiculo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lugares.add(new String[]{
                    rs.getString("id"),
                    rs.getString("numero")
                });
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener lugares disponibles: " + e.getMessage());
        }
        return lugares;
    }

    // 🗺️ Mapa del cuadrante por tipo de vehículo
    public List<Reserva> obtenerMapaPorTipoVehiculo(String tipoVehiculo) {
        List<Reserva> mapa = new ArrayList<>();
        String sql = """
            SELECT l.numero, l.disponible, c.nombre AS nombre_cuadrante
            FROM lugar l
            JOIN cuadrante c ON l.id_cuadrante = c.id
            WHERE c.tipo_vehiculo = ?
        """;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoVehiculo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setNumeroLugar(rs.getString("numero"));
                r.setDisponibilidad(rs.getBoolean("disponible"));
                r.setNombreCuadrante(rs.getString("nombre_cuadrante"));
                mapa.add(r);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener mapa del cuadrante: " + e.getMessage());
        }
        return mapa;
    }
    public boolean actualizarEstadoReserva(int idReserva, String nuevoEstado) {
        String sql = "UPDATE reserva SET estado = ? WHERE id = ?";
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idReserva);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar estado de reserva: " + e.getMessage());
            return false;
        }
    }

    // 🔚 Cerrar recursos
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.out.println("⚠ Error al cerrar recursos: " + e.getMessage());
        }
    }
}
