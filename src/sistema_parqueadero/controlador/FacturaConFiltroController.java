package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FacturaConFiltroController {

    private FacturaConFiltro vista;
    private FacturaDao dao;

    public FacturaConFiltroController(FacturaConFiltro vista) {
        this.vista = vista;
        this.dao = new FacturaDao();

        // --- Botones ---
        this.vista.getBtnVolver().addActionListener(e -> volverAlMenu());
        this.vista.getBtnFiltros().addActionListener(e -> aplicarFiltros());

        // --- Evento al hacer clic en tabla ---
        this.vista.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = vista.getTable().rowAtPoint(evt.getPoint());
                int col = vista.getTable().columnAtPoint(evt.getPoint());

                if (col == vista.getTable().getColumnCount() - 1) { // Columna "Consultar"
                    String tipoVehiculo = (String) vista.getTable().getValueAt(row, 5);
                    String placaVehiculo = (String) vista.getTable().getValueAt(row, 4);
                    String cedula = vista.getTxtIdentificacion().getText();

                    abrirFacturaDetallada(cedula, tipoVehiculo, placaVehiculo);
                }
            }
        });
    }

    private void aplicarFiltros() {
        String cedula = vista.getTxtIdentificacion().getText().trim();
        String tipo = vista.getTxtTipo().getText().trim();
        String placa = vista.getTxtPlaca().getText().trim();

        List<Factura> facturas = dao.FacturaConFiltro(cedula, tipo, placa);
        DefaultTableModel model = (DefaultTableModel) vista.getTable().getModel();
        model.setRowCount(0);

        if (facturas.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se encontraron facturas con esos filtros.");
            return;
        }

        for (Factura f : facturas) {
            Object[] fila = {
                f.getFechaEntrada(),
                f.getHoraEntrada(),
                f.getFechaSalida(),
                f.getHoraSalida(),
                f.getPlacaVehiculo(),
                f.getTipoVehiculo(),
                "$" + String.format("%,.2f", f.getTotalPagar()),
                "Consultar"
            };
            model.addRow(fila);
        }
    }

    private void abrirFacturaDetallada(String cedula, String tipoVehiculo, String placaVehiculo) {
        try {
            List<Factura> facturas = dao.FacturaDetallado();
            Factura seleccionada = facturas.stream()
                    .filter(f -> f.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)
                            && f.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo))
                    .findFirst()
                    .orElse(null);

            if (seleccionada == null) {
                JOptionPane.showMessageDialog(vista, "No se encontró información detallada para la factura seleccionada.");
                return;
            }

            FacturaDetallada vistaDetallada = new FacturaDetallada();
            new FacturaDetalladasController(vistaDetallada); // mantiene el flujo
            vista.dispose();
            vistaDetallada.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al abrir factura detallada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 🔹 Volver a Principal manteniendo sesión
    private void volverAlMenu() {
        vista.dispose();

        Principal ventana = new Principal();
        new PrincipalController(ventana);

        // Mostrar quién sigue logueado
        System.out.println("Sesión mantenida: " + Globales.global_nombre_usuario + " (" + Globales.global_rol_usuario + ")");
        ventana.setVisible(true);
    }
}
