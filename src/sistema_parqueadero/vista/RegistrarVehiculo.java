package vista;

import controlador.RegistrarVehiculoController;
import javax.swing.*;
import java.awt.*;
import modelo.Globales;

public class RegistrarVehiculo extends JFrame {

    // campos publicos para el controlador
    public JTextField txtIdentificacion, txtPlaca, txtMarca, txtModelo, txtColor;
    public JComboBox<String> cmbTipo;
    public JButton btnRegistrar, btnVolver;

    public RegistrarVehiculo() {
        setTitle("PARQUEAPP - Registrar Vehículo");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // navbar
        add(new NavbarPanel(this), BorderLayout.NORTH);

        // panel central
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(null);
        panelCentro.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Registrar vehículo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(350, 40, 200, 30);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(144, 238, 144)); 
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelCentro.add(lblTitulo);

        // identificacion del cliente
        JLabel lblIdentificacion = new JLabel("Identificación del Cliente");
        lblIdentificacion.setBounds(80, 80, 200, 20);
        panelCentro.add(lblIdentificacion);

        txtIdentificacion = new JTextField();
        txtIdentificacion.setBounds(80, 100, 220, 25);
        txtIdentificacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentro.add(txtIdentificacion);

        // placa
        JLabel lblPlaca = new JLabel("Número de Placa");
        lblPlaca.setBounds(330, 80, 200, 20);
        panelCentro.add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(330, 100, 220, 25);
        txtPlaca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentro.add(txtPlaca);

        // tipo de vehiculo
        JLabel lblTipo = new JLabel("Tipo de vehículo");
        lblTipo.setBounds(580, 80, 200, 20);
        panelCentro.add(lblTipo);

        cmbTipo = new JComboBox<>(new String[]{"moto", "carro", "cicla", "camioneta"});
        cmbTipo.setBounds(580, 100, 220, 25);
        cmbTipo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentro.add(cmbTipo);

        // marca
        JLabel lblMarca = new JLabel("Marca del vehículo");
        lblMarca.setBounds(80, 150, 200, 20);
        panelCentro.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(80, 170, 220, 25);
        txtMarca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentro.add(txtMarca);

        // modelo
        JLabel lblModelo = new JLabel("Modelo del vehículo");
        lblModelo.setBounds(330, 150, 200, 20);
        panelCentro.add(lblModelo);

        txtModelo = new JTextField();
        txtModelo.setBounds(330, 170, 220, 25);
        txtModelo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentro.add(txtModelo);

        // color
        JLabel lblColor = new JLabel("Color");
        lblColor.setBounds(580, 150, 200, 20);
        panelCentro.add(lblColor);

        txtColor = new JTextField();
        txtColor.setBounds(580, 170, 220, 25);
        txtColor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelCentro.add(txtColor);

        // botones
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(0, 0, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBounds(280, 260, 150, 35);
        btnRegistrar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnRegistrar.setFocusPainted(false);
        panelCentro.add(btnRegistrar);

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(144, 238, 144));
        btnVolver.setBounds(460, 260, 150, 35);
        btnVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnVolver.setFocusPainted(false);
        panelCentro.add(btnVolver);

        add(panelCentro, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistrarVehiculo vista = new RegistrarVehiculo();
            new RegistrarVehiculoController(vista); 
            vista.setVisible(true);
        });
    }
}
