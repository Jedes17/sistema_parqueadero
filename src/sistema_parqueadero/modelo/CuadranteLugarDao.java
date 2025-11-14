package modelo;

import conexion.Conexion;
import crud.CrudCuadranteLugar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CuadranteLugarDao implements CrudCuadranteLugar<CuadranteLugar> {

    @Override
    public List<CuadranteLugar> listarCuadrantesYLugares() {
        List<CuadranteLugar> datos = new ArrayList<>();
        String sql = """
            SELECT 
                c.id AS idCuadrante, 
                c.nombre AS nombreCuadrante, 
                c.tipo_vehiculo,
                l.id AS idLugar, 
                l.numero, 
                l.disponible
            FROM cuadrante c
            INNER JOIN lugar l ON c.id = l.id_cuadrante
            ORDER BY c.id, l.numero;
        """;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CuadranteLugar v = new CuadranteLugar();
                v.setId(rs.getInt("idCuadrante"));
                v.setNombre(rs.getString("nombreCuadrante"));
                v.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                v.setId_lugar(rs.getInt("idLugar"));
                v.setNumero(rs.getString("numero"));
                v.setIdCuadrante(rs.getInt("idCuadrante"));
                v.setDisponible(rs.getBoolean("disponible"));
                datos.add(v);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar cuadrantes y lugares:\n" + e, "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }

    @Override
    public List<CuadranteLugar> listarLugaresPorTipoVehiculo(String tipoVehiculo) {
        List<CuadranteLugar> lista = new ArrayList<>();
        String sql = """
            SELECT 
                c.id AS idCuadrante, 
                c.nombre AS nombreCuadrante, 
                c.tipo_vehiculo,
                l.id AS idLugar, 
                l.numero, 
                CASE 
                    WHEN EXISTS (
                        SELECT 1 FROM reserva r 
                        WHERE r.id_lugar = l.id 
                          AND r.estado IN ('Activa', 'Confirmada')
                    ) THEN 0
                    ELSE l.disponible
                END AS disponible_real
            FROM cuadrante c
            INNER JOIN lugar l ON c.id = l.id_cuadrante
            WHERE (? IS NULL OR c.tipo_vehiculo = ?)
            ORDER BY c.id, l.numero;
        """;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (tipoVehiculo == null || tipoVehiculo.trim().isEmpty()) {
                ps.setNull(1, Types.VARCHAR);
                ps.setNull(2, Types.VARCHAR);
            } else {
                ps.setString(1, tipoVehiculo);
                ps.setString(2, tipoVehiculo);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CuadranteLugar v = new CuadranteLugar();
                v.setId(rs.getInt("idCuadrante"));
                v.setNombre(rs.getString("nombreCuadrante"));
                v.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                v.setId_lugar(rs.getInt("idLugar"));
                v.setNumero(rs.getString("numero"));
                v.setIdCuadrante(rs.getInt("idCuadrante"));
                v.setDisponible(rs.getInt("disponible_real") == 1);
                lista.add(v);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar lugares:\n" + e, "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    @Override
    public CuadranteLugar buscarPorNumero(String numero) {
        CuadranteLugar lugar = null;
        String sql = """
            SELECT 
                l.id AS id_lugar, 
                l.numero, 
                l.disponible, 
                c.id AS id_cuadrante, 
                c.nombre, 
                c.tipo_vehiculo
            FROM lugar l
            INNER JOIN cuadrante c ON l.id_cuadrante = c.id
            WHERE l.numero = ?;
        """;

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lugar = new CuadranteLugar();
                lugar.setId_lugar(rs.getInt("id_lugar"));
                lugar.setNumero(rs.getString("numero"));
                lugar.setDisponible(rs.getBoolean("disponible"));
                lugar.setIdCuadrante(rs.getInt("id_cuadrante"));
                lugar.setNombre(rs.getString("nombre"));
                lugar.setTipoVehiculo(rs.getString("tipo_vehiculo"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar lugar:\n" + e, "Error SQL", JOptionPane.ERROR_MESSAGE);
        }

        return lugar;
    }
}