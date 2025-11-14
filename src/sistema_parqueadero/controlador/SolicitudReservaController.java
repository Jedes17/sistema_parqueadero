package controlador;

import modelo.Reserva;
import modelo.ReservaDao;
import vista.SolicitudReserva;
import vista.DetalleSolicitudReserva;
import vista.Principal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class SolicitudReservaController {

    private SolicitudReserva vista;
    private ReservaDao dao = new ReservaDao();

    public SolicitudReservaController(SolicitudReserva vista) {
        this.vista = vista;

        // --- Listeners ---
        vista.getBtnFiltros().addActionListener(e -> aplicarFiltros());
        vista.getBtnVolver().addActionListener(e -> volver());

        // --- Cargar todas las reservas al inicio ---
        cargarReservas("", "", "");

        // --- Evento para la columna "Consultar" ---
        vista.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = vista.getTable().rowAtPoint(evt.getPoint());
                int col = vista.getTable().columnAtPoint(evt.getPoint());

                if (col == vista.getTable().getColumnCount() - 1) { // Última columna = "Consultar"
                    abrirDetalleReserva(row);
                }
            }
        });
    }

    /**
     * 🧾 Aplica los filtros introducidos por el usuario.
     */
    private void aplicarFiltros() {
        String tipo = vista.getTxtTipo().getText().trim();
        String placa = vista.getTxtPlaca().getText().trim();
        String cedula = vista.getTxtIdentificacion().getText().trim();
        cargarReservas(tipo, placa, cedula);
    }

    /**
     * 📋 Carga las reservas filtradas en la tabla.
     */
    private void cargarReservas(String tipo, String placa, String cedula) {
        List<Reserva> lista = dao.FiltroSolicitudDeReserva(tipo, placa, cedula);
        DefaultTableModel model = (DefaultTableModel) vista.getTable().getModel();
        model.setRowCount(0); // limpiar tabla

        for (Reserva r : lista) {
            model.addRow(new Object[]{
                    r.getFechaEntrada(),
                    r.getHoraEntrada(),
                    r.getFechaSalida(),
                    r.getHoraSalida(),
                    r.getCedulaUsuario(),
                    r.getTipoVehiculo(),
                    r.getEstado(),
                    "Consultar"
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "⚠️ No se encontraron reservas con los filtros aplicados.");
        }
    }

    /**
     * 🔍 Abre la vista de DetalleSolicitudReserva con los datos seleccionados.
     */
    private void abrirDetalleReserva(int row) {
        try {
            String cedula = (String) vista.getTable().getValueAt(row, 4);
            String tipoVehiculo = (String) vista.getTable().getValueAt(row, 5);
            String fechaEntrada = (String) vista.getTable().getValueAt(row, 0);
            String horaEntrada = (String) vista.getTable().getValueAt(row, 1);

            // Buscar la reserva detallada que coincida
            List<Reserva> reservas = dao.ReservaDetallada();
            Reserva reservaSeleccionada = null;

            for (Reserva r : reservas) {
                if (r.getCedulaUsuario().equals(cedula)
                        && r.getTipoVehiculo().equals(tipoVehiculo)
                        && r.getFechaEntrada().equals(fechaEntrada)
                        && r.getHoraEntrada().equals(horaEntrada)) {
                    reservaSeleccionada = r;
                    break;
                }
            }

            if (reservaSeleccionada != null) {
                DetalleSolicitudReserva detalleVista = new DetalleSolicitudReserva();
                new ControladorDetalleSolicitudReserva(detalleVista, dao, vista, reservaSeleccionada);
                vista.setVisible(false);
                detalleVista.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(vista, "⚠️ No se encontró información detallada de esta reserva.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "❌ Error al abrir el detalle: " + e.getMessage());
        }
    }

    /**
     * 🔙 Regresa al menú principal sin cerrar sesión.
     */
    private void volver() {
        vista.dispose();
        Principal ventana = new Principal();
        new PrincipalController(ventana);
        ventana.setVisible(true);
    }
}