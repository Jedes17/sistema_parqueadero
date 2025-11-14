package controlador;

import modelo.Usuario;
import modelo.UsuarioDao;
import vista.InformacionUsuario;
import vista.Principal;
import modelo.Globales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexion.Conexion;

public class InformacionUsuarioController {

    private final InformacionUsuario vista;
    private final UsuarioDao usuarioDao;

    public InformacionUsuarioController(InformacionUsuario vista) {
        this.vista = vista;
        this.usuarioDao = new UsuarioDao();

        // ✅ Validar que solo el cliente pueda acceder
        if (!"Cliente".equalsIgnoreCase(Globales.global_rol_usuario)) {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Solo los clientes pueden ver esta sección.");
            vista.dispose();
            return;
        }

        // 🔹 Cargar nombre del cliente en el encabezado
        vista.getLblClienteNombre().setText("Cliente: " + Globales.global_nombre_usuario);

        // 🔹 Cargar datos personales del cliente
        cargarDatosCliente();

        // 🔹 Cargar vehículos asociados
        cargarVehiculosCliente();

        // 🔹 Configurar acciones de botones
        configurarEventos();
    }

    private void configurarEventos() {
        // 🔹 Botón "Volver" → Regresa a la vista Principal (manteniendo la sesión activa)
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            vista.Principal ventanaPrincipal = new vista.Principal();
            new controlador.PrincipalController(ventanaPrincipal);
            ventanaPrincipal.setVisible(true);
        });

        // 🔹 Botón "Cerrar sesión" → Limpia variables globales y cierra la ventana
        vista.getBtnCerrarSesion().addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                modelo.Globales.global_id_usuario = 0;
                modelo.Globales.global_nombre_usuario = "";
                modelo.Globales.global_rol_usuario = "";
                vista.dispose();
                JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente.");
            }
        });
    }

    // 🟩 Cargar información del cliente logueado
    private void cargarDatosCliente() {
        try (Connection con = Conexion.getInstance().getConnection()) {
            String sql = "SELECT cedula, CONCAT(nombre, ' ', apellido) AS nombre, correo, telefono " +
                         "FROM usuarios WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Globales.global_id_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String[] datos = {
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                };
                vista.setDatosUsuario(datos);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la información del usuario.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos del cliente: " + e.getMessage());
        }
    }

    // 🟦 Cargar vehículos asociados al cliente
    private void cargarVehiculosCliente() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTablaVehiculos().getModel();
        modelo.setRowCount(0);

        String sql = "SELECT v.tipo_vehiculo, v.numero_placa, v.modelo_vehiculo, v.color, v.marca_vehiculo " +
                     "FROM vehiculo v " +
                     "INNER JOIN usuario_vehiculo uv ON uv.id_vehiculo = v.id " +
                     "WHERE uv.id_usuario = ?";

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Globales.global_id_usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                        rs.getString("tipo_vehiculo"),
                        rs.getString("numero_placa"),
                        rs.getString("modelo_vehiculo"),
                        rs.getString("color"),
                        rs.getString("marca_vehiculo")
                };
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar vehículos: " + e.getMessage());
        }
    }
}
