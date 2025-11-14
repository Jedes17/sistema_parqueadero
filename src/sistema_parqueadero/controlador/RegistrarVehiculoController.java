package controlador;

import modelo.Vehiculo;
import modelo.VehiculoDao;
import modelo.Globales;
import vista.RegistrarVehiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarVehiculoController implements ActionListener {

    private final RegistrarVehiculo vista;
    private final VehiculoDao dao;

    public RegistrarVehiculoController(RegistrarVehiculo vista) {
        this.vista = vista;
        this.dao = new VehiculoDao();

        // Ocultar campo identificación si el rol es "Cliente"
        if ("Cliente".equalsIgnoreCase(Globales.global_rol_usuario)) {
            vista.txtIdentificacion.setVisible(false);
            for (java.awt.Component c : vista.getContentPane().getComponents()) {
                if (c instanceof JPanel panelCentro) {
                    for (java.awt.Component label : panelCentro.getComponents()) {
                        if (label instanceof JLabel lbl && lbl.getText().equals("Identificación del Cliente")) {
                            lbl.setVisible(false);
                        }
                    }
                }
            }
        }

        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar) {
            registrarVehiculo();
        } else if (e.getSource() == vista.btnVolver) {
            vista.dispose();
            // Aquí podrías regresar al menú principal, por ejemplo:
            // new Principal().setVisible(true);
        }
    }

    private void registrarVehiculo() {
        try {
            String placa = vista.txtPlaca.getText().trim();
            String marca = vista.txtMarca.getText().trim();
            String modelo = vista.txtModelo.getText().trim();
            String tipo = (String) vista.cmbTipo.getSelectedItem();
            String color = vista.txtColor.getText().trim();
            int idUsuario;

            // Si es cliente, se usa su propio id
            if ("Cliente".equalsIgnoreCase(Globales.global_rol_usuario)) {
                idUsuario = Globales.global_id_usuario;
            } else {
                // Si es admin u operador, se pide el ID del cliente
                String identificacion = vista.txtIdentificacion.getText().trim();
                if (identificacion.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Debe ingresar la identificación del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                idUsuario = Integer.parseInt(identificacion); // Aquí asumes que el ID del cliente ya existe
            }

            if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || color.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vehiculo v = new Vehiculo();
            v.setNumeroPlaca(placa);
            v.setMarcaVehiculo(marca);
            v.setModeloVehiculo(modelo);
            v.setTipoVehiculo(tipo);
            v.setColor(color);
            v.setIdUsuario(idUsuario);
            v.setRegistradoPor(Globales.global_id_usuario);

            int idGenerado = dao.AgregarVehiculoCliente(v);
            if (idGenerado > 0) {
                JOptionPane.showMessageDialog(vista, "Vehículo registrado con éxito (ID: " + idGenerado + ")");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El campo identificación debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        vista.txtPlaca.setText("");
        vista.txtMarca.setText("");
        vista.txtModelo.setText("");
        vista.txtColor.setText("");
        if (!"Cliente".equalsIgnoreCase(Globales.global_rol_usuario)) {
            vista.txtIdentificacion.setText("");
        }
    }
}
