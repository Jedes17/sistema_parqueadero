package controlador;

import modelo.*;
import vista.ConsultarEstadoVehicular;
import vista.Principal;
import vista.EditarReserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class ConsultarEstadoVehicularController implements ActionListener {

    private ConsultarEstadoVehicular vista;
    private EstadoVehicularDao dao;
    private String rol;

    public ConsultarEstadoVehicularController(ConsultarEstadoVehicular vista) {
        this.vista = vista;
        this.rol = Globales.global_rol_usuario; // Rol actual

        try {
            dao = new EstadoVehicularDao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }

        // Escuchar botones
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnFiltros().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        this.vista.getBtnCancelar().addActionListener(this);

        // Escuchar selección de tabla
        this.vista.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verificarEstadoSeleccionado();
            }
        });

        configurarSegunRol();
    }

    // 🔧 Configura la vista según el rol
    private void configurarSegunRol() {
        switch (rol.toLowerCase()) {
            case "cliente" -> {
                vista.getTxtCliente().setVisible(false);
                vista.getTxtOperador().setVisible(false);
                ocultarEtiqueta(vista.getTxtCliente());
                ocultarEtiqueta(vista.getTxtOperador());
            }
            case "operador" -> {
                vista.getTxtOperador().setVisible(false);
                ocultarEtiqueta(vista.getTxtOperador());
            }
            case "administrador" -> {
                // Admin ve todo
            }
            default -> JOptionPane.showMessageDialog(null, "Rol desconocido: " + rol);
        }
    }

    // Oculta etiquetas asociadas a los campos
    private void ocultarEtiqueta(JTextField campo) {
        java.awt.Component[] comps = vista.getContentPane().getComponents();
        for (java.awt.Component c : comps) {
            if (c instanceof JPanel panel) {
                for (java.awt.Component sub : panel.getComponents()) {
                    if (sub instanceof JLabel lbl &&
                        lbl.getBounds().y < campo.getBounds().y + 5 &&
                        lbl.getBounds().y > campo.getBounds().y - 30) {
                        lbl.setVisible(false);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnVolver()) {
            vista.dispose();
            Principal ventanaPrincipal = new Principal();
            new PrincipalController(ventanaPrincipal); // ✅ mantiene la sesión activa
            ventanaPrincipal.setVisible(true);
        }

        if (e.getSource() == vista.getBtnFiltros()) {
            aplicarFiltros();
        }

        if (e.getSource() == vista.getBtnEditar()) {
            abrirEditarReserva();
        }

        if (e.getSource() == vista.getBtnCancelar()) {
            cancelarReservaSeleccionada();
        }
    }

    // 🔍 Aplica los filtros según el rol
    private void aplicarFiltros() {
        String tipo = vista.getTxtTipo().getText().trim();
        String placa = vista.getTxtPlaca().getText().trim();
        String cliente = vista.getTxtCliente().getText().trim();
        String operador = vista.getTxtOperador().getText().trim();

        try {
            List<EstadoVehicular> lista;

            switch (rol.toLowerCase()) {
                case "cliente" -> {
                    cliente = String.valueOf(Globales.global_id_usuario);
                    lista = dao.consultarConFiltros(tipo, placa, cliente, "");
                }
                case "operador", "administrador" -> {
                    lista = dao.consultarConFiltros(tipo, placa, cliente, operador);
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Rol no reconocido");
                    return;
                }
            }

            cargarTabla(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar: " + ex.getMessage());
        }
    }

    // 🧾 Carga los resultados en la tabla
    private void cargarTabla(List<EstadoVehicular> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        modelo.setRowCount(0);

        java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");

        for (EstadoVehicular ev : lista) {
            String fechaIngreso = ev.getFechaIngreso() != null ? formatoFecha.format(ev.getFechaIngreso()) : "";
            String fechaSalida = ev.getFechaSalida() != null ? formatoFecha.format(ev.getFechaSalida()) : "";
            String horaEntrada = ev.getHoraEntrada() != null ? ev.getHoraEntrada() : "";
            String horaSalida = ev.getHoraSalida() != null ? ev.getHoraSalida() : "";

            Object[] fila = {
                fechaIngreso,
                horaEntrada,
                fechaSalida,
                horaSalida,
                ev.getPlacaVehiculo(),
                ev.getTipoVehiculo(),
                ev.getCedulaCliente(),
                ev.getLugarReservado(),
                ev.getEstadoGeneral(),
                "Ver"
            };
            modelo.addRow(fila);
        }

        ajustarColumnasSegunRol();
    }

    // 🧭 Ajusta visibilidad de columnas según el rol
    private void ajustarColumnasSegunRol() {
        JTable tabla = vista.getTabla();

        switch (rol.toLowerCase()) {
            case "cliente" -> ocultarColumna(tabla, "Identificación cliente");
            case "operador" -> {
                // operador ve igual que administrador
            }
            case "administrador" -> {
                // Admin ve todo
            }
        }
    }

    // 🔒 Método seguro para ocultar columnas
    private void ocultarColumna(JTable tabla, String nombreColumna) {
        try {
            int index = tabla.getColumnModel().getColumnIndex(nombreColumna);
            tabla.removeColumn(tabla.getColumnModel().getColumn(index));
        } catch (IllegalArgumentException ex) {
            System.out.println("⚠ Columna no encontrada (omitida): " + nombreColumna);
        }
    }

    // 🟢 Verifica si el registro seleccionado está "Confirmada"
    private void verificarEstadoSeleccionado() {
        JTable tabla = vista.getTabla();
        int fila = tabla.getSelectedRow();
        if (fila == -1) return;

        // Buscar índice real de la columna "Estado de reserva"
        int colEstado = -1;
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            if ("Estado de reserva".equals(tabla.getColumnName(i))) {
                colEstado = i;
                break;
            }
        }

        if (colEstado == -1) return; // por seguridad

        String estado = String.valueOf(tabla.getValueAt(fila, colEstado));

        if ("Confirmada".equalsIgnoreCase(estado)) {
            vista.getBtnEditar().setVisible(true);
            vista.getBtnCancelar().setVisible(true);
        } else {
            vista.getBtnEditar().setVisible(false);
            vista.getBtnCancelar().setVisible(false);
        }
    }

    // 🟠 Abrir ventana EditarReserva
    private void abrirEditarReserva() {
        JTable tabla = vista.getTabla();
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una reserva para editar.");
            return;
        }

        String placa = String.valueOf(tabla.getValueAt(fila, 4));
        vista.dispose();
        EditarReserva editarVista = new EditarReserva();
        new EditarReservaController(editarVista, placa);
        editarVista.setVisible(true);
    }

    // 🔴 Cancelar reserva (actualiza el estado en BD)
    private void cancelarReservaSeleccionada() {
        JTable tabla = vista.getTabla();
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una reserva para cancelar.");
            return;
        }

        String placa = String.valueOf(tabla.getValueAt(fila, 4));
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Desea cancelar la reserva del vehículo con placa " + placa + "?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean ok = dao.actualizarEstadoReserva(placa, "Cancelada");
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Reserva cancelada correctamente.");
                    aplicarFiltros(); // refrescar tabla
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo cancelar la reserva.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cancelar: " + ex.getMessage());
            }
        }
    }
}
