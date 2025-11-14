package controlador;

import modelo.*;
import vista.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class CrearReservaController {

    private CrearReserva vista;
    private ReservaDao dao = new ReservaDao();

    public CrearReservaController(CrearReserva vista) {
        this.vista = vista;

        // Validar rol antes de mostrar
        if (Globales.global_rol_usuario == null || !Globales.global_rol_usuario.equalsIgnoreCase("cliente")) {
            JOptionPane.showMessageDialog(null, "❌ Solo los usuarios con rol CLIENTE pueden crear reservas.");
            vista.dispose();
            return;
        }

        // Listeners
        vista.cbTipoVehiculo.addActionListener(e -> cargarVehiculosYLugares());
        vista.btnRegistrar.addActionListener(e -> registrarReserva());
        vista.btnVolver.addActionListener(e -> volver());

        // Cargar inicial según el tipo seleccionado por defecto
        cargarVehiculosYLugares();
    }

    /** 
     * Carga los vehículos y lugares según el tipo de vehículo seleccionado.
     */
    private void cargarVehiculosYLugares() {
        String tipo = (String) vista.cbTipoVehiculo.getSelectedItem();
        if (tipo == null) return;

        // 🚗 Cargar vehículos del usuario según tipo
        vista.cbSeleccionVehiculo.removeAllItems();
        List<String[]> vehiculos = dao.obtenerVehiculosUsuarioPorTipo(Globales.global_id_usuario, tipo);
        for (String[] v : vehiculos) {
            vista.cbSeleccionVehiculo.addItem(v[1]); // muestra placa
            vista.cbSeleccionVehiculo.setToolTipText(v[0]); // guarda id
        }

        // 📍 Cargar lugares disponibles según tipo
        vista.cbLugar.removeAllItems();
        List<String[]> lugares = dao.obtenerLugaresDisponiblesPorTipo(tipo);
        for (String[] l : lugares) {
            vista.cbLugar.addItem(l[1]); // muestra número
            vista.cbLugar.setToolTipText(l[0]); // guarda id
        }

        // 🗺️ Mostrar cuadrantes disponibles
        mostrarMapa(tipo);
    }

    /** 
     * Muestra en el panelMapa los cuadrantes con número y disponibilidad.
     */
    private void mostrarMapa(String tipoVehiculo) {
        vista.panelMapa.removeAll();
        vista.panelMapa.setLayout(new GridLayout(0, 4, 20, 15)); // filas dinámicas, 4 columnas

        List<Reserva> mapa = dao.obtenerMapaPorTipoVehiculo(tipoVehiculo);

        for (Reserva r : mapa) {
            JPanel celda = new JPanel(new BorderLayout());
            celda.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel lblNum = new JLabel("Lugar: " + r.getNumeroLugar(), SwingConstants.CENTER);
            JLabel lblCuad = new JLabel("Cuadrante: " + r.getNombreCuadrante(), SwingConstants.CENTER);

            // Color según disponibilidad
            if (r.isDisponibilidad()) {
                celda.setBackground(new Color(178, 255, 178)); // disponible (verde)
            } else {
                celda.setBackground(new Color(255, 150, 150)); // ocupado (rojo)
            }

            celda.add(lblNum, BorderLayout.CENTER);
            celda.add(lblCuad, BorderLayout.SOUTH);
            vista.panelMapa.add(celda);
        }

        vista.panelMapa.revalidate();
        vista.panelMapa.repaint();
    }

    /** 
     * Registra una nueva reserva en la base de datos.
     */
    private void registrarReserva() {
        try {
            if (vista.cbSeleccionVehiculo.getSelectedItem() == null || vista.cbLugar.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vista, "⚠️ Debes seleccionar un vehículo y un lugar disponible.");
                return;
            }

            // Obtener valores
            String tipoVehiculo = (String) vista.cbTipoVehiculo.getSelectedItem();
            String placaSeleccionada = (String) vista.cbSeleccionVehiculo.getSelectedItem();
            String lugarSeleccionado = (String) vista.cbLugar.getSelectedItem();

            // Buscar IDs de los seleccionados
            int idVehiculo = obtenerIdVehiculo(Globales.global_id_usuario, tipoVehiculo, placaSeleccionada);
            int idLugar = obtenerIdLugar(tipoVehiculo, lugarSeleccionado);

            if (idVehiculo == -1 || idLugar == -1) {
                JOptionPane.showMessageDialog(vista, "⚠️ No se pudieron identificar los registros seleccionados.");
                return;
            }

            // Fechas y horas
            SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

            Date fechaEntrada = vista.fechaEntrada.getDate();
            Date fechaSalida = vista.fechaSalida.getDate();
            String horaEntrada = sdfHora.format((Date) vista.horaEntrada.getValue());
            String horaSalida = sdfHora.format((Date) vista.horaSalida.getValue());

            if (fechaEntrada == null || fechaSalida == null) {
                JOptionPane.showMessageDialog(vista, "⚠️ Debes seleccionar las fechas de entrada y salida.");
                return;
            }

            // Crear objeto reserva
            Reserva r = new Reserva();
            r.setIdUsuario(Globales.global_id_usuario);
            r.setIdVehiculo(idVehiculo);
            r.setIdLugar(idLugar);
            r.setFechaEntrada(sdfFecha.format(fechaEntrada));
            r.setFechaSalida(sdfFecha.format(fechaSalida));
            r.setHoraEntrada(horaEntrada);
            r.setHoraSalida(horaSalida);
            r.setEstado("pendiente");

            // Registrar en BD
            int filas = dao.SolicitarReserva(r);
            if (filas > 0) {
                JOptionPane.showMessageDialog(vista, "✅ Reserva registrada con éxito.");
                cargarVehiculosYLugares(); // refrescar
            } else {
                JOptionPane.showMessageDialog(vista, "❌ Error al registrar la reserva.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "⚠️ Error al registrar reserva: " + e.getMessage());
        }
    }

    /** 
     * Busca el ID del vehículo por placa y tipo.
     */
    private int obtenerIdVehiculo(int idUsuario, String tipo, String placa) {
        List<String[]> vehiculos = dao.obtenerVehiculosUsuarioPorTipo(idUsuario, tipo);
        for (String[] v : vehiculos) {
            if (v[1].equals(placa)) {
                return Integer.parseInt(v[0]);
            }
        }
        return -1;
    }

    /** 
     * Busca el ID del lugar por número.
     */
    private int obtenerIdLugar(String tipo, String numeroLugar) {
        List<String[]> lugares = dao.obtenerLugaresDisponiblesPorTipo(tipo);
        for (String[] l : lugares) {
            if (l[1].equals(numeroLugar)) {
                return Integer.parseInt(l[0]);
            }
        }
        return -1;
    }

    /** 
     * Regresa a la pantalla principal manteniendo la sesión activa.
     */
    private void volver() {
        vista.dispose();
        Principal ventana = new Principal();
        new PrincipalController(ventana);
        ventana.setVisible(true);
    }

}