package controlador;

import modelo.*;
import vista.FacturaConFiltro;
import vista.FacturaDetallada;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.List;

public class FacturaDetalladasController {

    private FacturaDetallada vista;
    private FacturaDao dao;
    private DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");

    // --- Constructor ---
    public FacturaDetalladasController(FacturaDetallada vista) {
        this.vista = vista;
        this.dao = new FacturaDao();

        // --- Eventos de botones ---
        this.vista.btnVolver.addActionListener(e -> volverAFacturaConFiltro());
        this.vista.btnGenerarPDF.addActionListener(e -> generarPDF());

        // --- Carga la información de la factura ---
        cargarFacturaDetallada();
    }

    /**
     * Carga la información detallada de una factura y la muestra en la vista.
     */
    private void cargarFacturaDetallada() {
        try {
            List<Factura> lista = dao.FacturaDetallado();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay facturas detalladas disponibles.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 🔹 Por ahora tomamos la primera factura (puedes adaptar para seleccionar una en particular)
            Factura f = lista.get(0);

            // --- Información de tiempos y vehículo ---
            vista.lblFechaEntrada.setText("Fecha de entrada: " + nulo(f.getFechaEntrada()));
            vista.lblHoraEntrada.setText("Hora de entrada: " + nulo(f.getHoraEntrada()));
            vista.lblFechaSalida.setText("Fecha de salida: " + nulo(f.getFechaSalida()));
            vista.lblHoraSalida.setText("Hora de salida: " + nulo(f.getHoraSalida()));
            vista.lblTipoVehiculo.setText("Tipo de vehículo: " + nulo(f.getTipoVehiculo()));
            vista.lblPlacaVehiculo.setText("Placa del vehículo: " + nulo(f.getPlacaVehiculo()));

            // --- Datos del cliente y vehículo ---
            vista.lblModelo.setText("Modelo: " + nulo(f.getModeloVehiculo()));
            vista.lblMarca.setText("Marca: " + nulo(f.getMarca()));
            vista.lblColor.setText("Color: " + nulo(f.getColor()));
            vista.lblIdentificacion.setText("Identificación del cliente: " + nulo(f.getIdentificacionCliente()));
            vista.lblNombreCliente.setText("Nombre del cliente: " + nulo(f.getNombreCompleto()));
            vista.lblCorreo.setText("Correo electrónico: " + nulo(f.getCorreoElectronico()));

            // --- Estados y totales ---
            vista.txtEstadoEntrada.setText(nulo(f.getEstadoEntrada()));
            vista.txtEstadoSalida.setText(nulo(f.getEstadoSalida()));
            vista.lblSubtotal.setText("Subtotal: $" + formatoMoneda.format(f.getSubtotal()));
            vista.lblTotal.setText("Total (IVA 19%): $" + formatoMoneda.format(f.getTotalPagar()));

            // --- Calcular horas de uso ---
            String horasUso = calcularHorasUso(f.getFechaEntrada(), f.getHoraEntrada(), f.getFechaSalida(), f.getHoraSalida());
            vista.lblHorasUso.setText("Horas de uso: " + horasUso);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar factura detallada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Calcula las horas totales entre la entrada y salida (simple cálculo basado en diferencia horaria).
     */
    private String calcularHorasUso(String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida) {
        try {
            if (fechaEntrada == null || horaEntrada == null || fechaSalida == null || horaSalida == null)
                return "N/A";

            java.time.LocalDateTime entrada = java.time.LocalDateTime.parse(fechaEntrada + "T" + horaEntrada);
            java.time.LocalDateTime salida = java.time.LocalDateTime.parse(fechaSalida + "T" + horaSalida);

            java.time.Duration duracion = java.time.Duration.between(entrada, salida);
            long horas = duracion.toHours();
            long minutos = duracion.toMinutesPart();

            return horas + "h " + minutos + "min";
        } catch (Exception e) {
            return "N/A";
        }
    }

    /**
     * Simula la generación de un PDF (puedes implementar con ReportLab, iText o JasperReports).
     */
    private void generarPDF() {
        JOptionPane.showMessageDialog(null, "📄 Generar PDF aún no implementado (pendiente de librería).", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Regresa a la pantalla de facturas con filtro (manteniendo sesión activa).
     */
    private void volverAFacturaConFiltro() {
        vista.dispose();

        // Mantener la sesión con Globales
        FacturaConFiltro vistaFiltro = new FacturaConFiltro();
        new FacturaConFiltroController(vistaFiltro);

        System.out.println("🔄 Volviendo a FacturaConFiltro...");
        System.out.println("Sesión activa: " + Globales.global_nombre_usuario + " (" + Globales.global_rol_usuario + ")");

        vistaFiltro.setVisible(true);
    }

    /**
     * Devuelve un texto por defecto si el valor es nulo o vacío.
     */
    private String nulo(String valor) {
        return (valor == null || valor.isEmpty()) ? "N/A" : valor;
    }
}
