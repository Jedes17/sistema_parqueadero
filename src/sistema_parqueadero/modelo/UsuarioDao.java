package modelo;

import conexion.Conexion;
import crud.CrudUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements CrudUsuario<Usuario> {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Usuario> IniciarSession() {
        // Este método del CRUD no se usa directamente
        return new ArrayList<>();
    }

    // Método para validar inicio de sesión
    public Usuario iniciarSesion(String cedula, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT u.*, r.nombre AS rol FROM usuarios u " +
                     "JOIN rol r ON u.id_rol = r.id " +
                     "WHERE u.cedula = ? AND u.contrasena = ? AND u.estado = 'Activo'";
        try {
            con = Conexion.getInstance().getConnection(); // 
            ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setCedula(rs.getString("cedula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al iniciar sesión: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return usuario;
    }

    // Consulta con filtro (solo administrador)
    @Override
    public List<Usuario> ConsultarUsuarioConFiltro(String nombre, String placa, String cedula) {
        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT u.id, u.cedula, CONCAT(u.nombre, ' ', u.apellido) AS nombre_completo, " +
                     "u.correo, u.telefono, u.estado, COUNT(v.id) AS numero_vehiculos " +
                     "FROM usuarios u " +
                     "LEFT JOIN usuario_vehiculo uv ON uv.id_usuario = u.id " +
                     "LEFT JOIN vehiculo v ON v.id = uv.id_vehiculo " +
                     "WHERE (u.nombre LIKE ? OR u.apellido LIKE ?) " +
                     "OR (u.cedula LIKE ?) " +
                     "OR (v.numero_placa LIKE ?) " +
                     "GROUP BY u.id, u.cedula, u.nombre, u.apellido, u.correo, u.telefono, u.estado";

        try {
            con = Conexion.getInstance().getConnection(); // 
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + nombre + "%");
            ps.setString(3, "%" + cedula + "%");
            ps.setString(4, "%" + placa + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre_completo"));
                u.setCorreo(rs.getString("correo"));
                u.setTelefono(rs.getString("telefono"));
                u.setNumeroVehiculos(rs.getInt("numero_vehiculos"));
                u.setEstado(rs.getString("estado"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar usuarios con filtro: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Consulta detallada con vehículos asociados
    @Override
    public List<Usuario> ConsultarUsuarioDetalladoConVehiculo() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.id, CONCAT(u.nombre, ' ', u.apellido) AS nombre_completo, " +
                     "u.correo, u.telefono, u.estado, " +
                     "v.tipo_vehiculo, v.numero_placa, v.modelo_vehiculo, v.color, v.marca_vehiculo " +
                     "FROM usuarios u " +
                     "LEFT JOIN usuario_vehiculo uv ON uv.id_usuario = u.id " +
                     "LEFT JOIN vehiculo v ON v.id = uv.id_vehiculo " +
                     "ORDER BY u.id";

        try {
            con = Conexion.getInstance().getConnection(); 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString("nombre_completo"));
                u.setCorreo(rs.getString("correo"));
                u.setTelefono(rs.getString("telefono"));
                u.setEstado(rs.getString("estado"));
                u.setTipoVehiculo(rs.getString("tipo_vehiculo"));
                u.setPlacaVehiculo(rs.getString("numero_placa"));
                u.setModeloVehiculo(rs.getString("modelo_vehiculo"));
                u.setColorVehiculo(rs.getString("color"));
                u.setMarcaVehiculo(rs.getString("marca_vehiculo"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar usuario detallado: " + e.getMessage());
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
