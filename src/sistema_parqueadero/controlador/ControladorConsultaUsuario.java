package controlador;

import modelo.Usuario;
import modelo.UsuarioDao;
import modelo.Globales;
import vista.ConsultaUsuario;
import vista.Principal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ControladorConsultaUsuario implements ActionListener {

    private final ConsultaUsuario vista;
    private final UsuarioDao usuarioDao;

    public ControladorConsultaUsuario(ConsultaUsuario vista) {
        this.vista = vista;
        this.usuarioDao = new UsuarioDao();

        // Verificar permisos apenas se abre la vista
        verificarPermisos();

        // Asignar acciones
        this.vista.getBtnFiltros().addActionListener(this);
        this.vista.getBtnVolver().addActionListener(this);
    }

    // --- Verificación de rol ---
    private void verificarPermisos() {
        String rol = Globales.global_rol_usuario;
        if (rol == null || !(rol.equalsIgnoreCase("Administrador") || rol.equalsIgnoreCase("Operador"))) {
            JOptionPane.showMessageDialog(
                    null,
                    "⚠️ No tienes permisos para acceder a esta sección.",
                    "Acceso denegado",
                    JOptionPane.WARNING_MESSAGE
            );

            // Regresar al menú principal
            SwingUtilities.invokeLater(() -> {
                vista.dispose();
                Principal principal = new Principal();
                new PrincipalController(principal);
                principal.setVisible(true);
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnFiltros()) {
            aplicarFiltros();
        } else if (e.getSource() == vista.getBtnVolver()) {
            volverMenu();
        }
    }

    // --- Aplicar filtros de búsqueda ---
    private void aplicarFiltros() {
        String nombre = vista.getTxtNombre().getText().trim();
        String cedula = vista.getTxtCedula().getText().trim();
        String placa = vista.getTxtPlaca().getText().trim();

        List<Usuario> lista = usuarioDao.ConsultarUsuarioConFiltro(nombre, placa, cedula);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vista,
                    "No se encontraron usuarios con esos filtros.",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // Mostrar los resultados en la tabla
        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        modelo.setRowCount(0); // limpiar la tabla

        for (Usuario u : lista) {
            modelo.addRow(new Object[]{
                    u.getCedula(),
                    u.getNombre(),
                    u.getCorreo(),
                    u.getNumeroVehiculos(),
                    u.getEstado(),
                    "Ver Detalle"
            });
        }
    }

    // --- Regresar a la ventana principal ---
    private void volverMenu() {
        vista.dispose();
        Principal principal = new Principal();
        new PrincipalController(principal);
        principal.setVisible(true);
    }
}
