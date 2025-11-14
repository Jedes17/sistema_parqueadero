package controlador;

import modelo.Reserva;
import modelo.ReservaDao;
import vista.DetalleSolicitudReserva;
import vista.SolicitudReserva;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControladorDetalleSolicitudReserva {

    private DetalleSolicitudReserva vista;
    private ReservaDao dao;
    private SolicitudReserva vistaAnterior;
    private Reserva reservaSeleccionada;

    public ControladorDetalleSolicitudReserva(DetalleSolicitudReserva vista, ReservaDao dao,
                                              SolicitudReserva vistaAnterior, Reserva reservaSeleccionada) {
        this.vista = vista;
        this.dao = dao;
        this.vistaAnterior = vistaAnterior;
        this.reservaSeleccionada = reservaSeleccionada;

        // Cargar datos del detalle
        cargarDatosReserva();

        // Mostrar mapa con lugar reservado
        mostrarMapa(reservaSeleccionada.getTipoVehiculo(),
                    reservaSeleccionada.getNumeroLugar(),
                    reservaSeleccionada.getNombreCuadrante());

        // Botones
        vista.btnVolver.addActionListener(e -> volver());
        vista.btnAceptar.addActionListener(e -> aceptarSolicitud());
        vista.btnCancelar.addActionListener(e -> cancelarSolicitud());
    }

    /**
     * 🧾 Carga los datos de la reserva seleccionada en los campos de texto.
     */
    private void cargarDatosReserva() {
        vista.txtFechaEntrada.setText(reservaSeleccionada.getFechaEntrada());
        vista.txtHoraEntrada.setText(reservaSeleccionada.getHoraEntrada());
        vista.txtFechaSalida.setText(reservaSeleccionada.getFechaSalida());
        vista.txtHoraSalida.setText(reservaSeleccionada.getHoraSalida());
        vista.txtTipoVehiculo.setText(reservaSeleccionada.getTipoVehiculo());
        vista.txtVehiculo.setText(reservaSeleccionada.getPlacaVehiculo());
        vista.txtLugar.setText(reservaSeleccionada.getNumeroLugar());
        vista.txtIdentificacionCliente.setText(reservaSeleccionada.getCedulaUsuario());
    }

    /**
     * 🗺️ Muestra el mapa del parqueadero y resalta el lugar reservado.
     */
    private void mostrarMapa(String tipoVehiculo, String numeroLugarReservado, String cuadranteReservado) {
        vista.panelMapa.removeAll();
        vista.panelMapa.setLayout(new GridLayout(0, 4, 20, 15));

        List<Reserva> mapa = dao.obtenerMapaPorTipoVehiculo(tipoVehiculo);

        for (Reserva r : mapa) {
            JPanel celda = new JPanel(new BorderLayout());
            celda.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel lblNum = new JLabel("Lugar: " + r.getNumeroLugar(), SwingConstants.CENTER);
            JLabel lblCuad = new JLabel("Cuadrante: " + r.getNombreCuadrante(), SwingConstants.CENTER);

            // Colores según disponibilidad
            if (r.getNumeroLugar().equals(numeroLugarReservado)
                    && r.getNombreCuadrante().equals(cuadranteReservado)) {
                celda.setBackground(new Color(102, 153, 255)); // 🔹 Azul para el lugar reservado
            } else if (r.isDisponibilidad()) {
                celda.setBackground(new Color(178, 255, 178)); // 🟢 Disponible
            } else {
                celda.setBackground(new Color(255, 150, 150)); // 🔴 Ocupado
            }

            celda.add(lblNum, BorderLayout.CENTER);
            celda.add(lblCuad, BorderLayout.SOUTH);
            vista.panelMapa.add(celda);
        }

        vista.panelMapa.revalidate();
        vista.panelMapa.repaint();
    }

    /**
     * ✅ Acepta la solicitud (cambia estado a 'aceptada').
     */
    private void aceptarSolicitud() {
        int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Desea aceptar esta solicitud?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = dao.actualizarEstadoReserva(reservaSeleccionada.getId(), "Confirmada");
            if (exito) {
                JOptionPane.showMessageDialog(vista, "✅ Solicitud aceptada correctamente.");
                volver();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ Error al aceptar la solicitud.");
            }
        }
    }

    /**
     * ❌ Cancela la solicitud (cambia estado a 'rechazada').
     */
    private void cancelarSolicitud() {
        int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Desea cancelar esta solicitud?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = dao.actualizarEstadoReserva(reservaSeleccionada.getId(), "cancelada");
            if (exito) {
                JOptionPane.showMessageDialog(vista, "❌ Solicitud cancelada correctamente.");
                volver();
            } else {
                JOptionPane.showMessageDialog(vista, "⚠️ Error al cancelar la solicitud.");
            }
        }
    }

    /**
     * 🔙 Regresa a la vista anterior sin cerrar la sesión.
     */
    private void volver() {
        vista.dispose();
        vistaAnterior.setVisible(true);
    }
}
