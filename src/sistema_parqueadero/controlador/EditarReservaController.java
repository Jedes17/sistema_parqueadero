package controlador;

import modelo.EstadoVehicularDao;
import vista.EditarReserva;
import vista.ConsultarEstadoVehicular;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarReservaController implements ActionListener {

    private EditarReserva vista;
    private EstadoVehicularDao dao;
    private String placaSeleccionada;

    public EditarReservaController(EditarReserva vista, String placaSeleccionada) {
        this.vista = vista;
        this.placaSeleccionada = placaSeleccionada;

        try {
            dao = new EstadoVehicularDao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        }

        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar) {
            registrarCambios();
        }

        if (e.getSource() == vista.btnVolver) {
            vista.dispose();
            ConsultarEstadoVehicular ventana = new ConsultarEstadoVehicular();
            new ConsultarEstadoVehicularController(ventana);
            ventana.setVisible(true);
        }
    }

    private void registrarCambios() {
        try {
            Date fechaEntrada = vista.dateEntrada.getDate();
            Date fechaSalida = vista.dateSalida.getDate();

            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            String horaEntrada = formatoHora.format((Date) vista.spnHoraEntrada.getValue());
            String horaSalida = formatoHora.format((Date) vista.spnHoraSalida.getValue());

            if (fechaEntrada == null || fechaSalida == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar las fechas de entrada y salida.");
                return;
            }

            boolean actualizado = dao.actualizarReserva(placaSeleccionada, fechaEntrada, horaEntrada, fechaSalida, horaSalida);
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Reserva actualizada correctamente.");
                vista.dispose();
                ConsultarEstadoVehicular ventana = new ConsultarEstadoVehicular();
                new ConsultarEstadoVehicularController(ventana);
                ventana.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la reserva.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
        }
    }
}
