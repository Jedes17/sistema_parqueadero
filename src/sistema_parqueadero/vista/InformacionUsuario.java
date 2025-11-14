
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class InformacionUsuario extends JFrame {
    private JButton btnVolver, btnCerrarSesion;
    private JLabel lblClienteNombre;
    private JTable tablaVehiculos;
    private JPanel panelCentro;
    private JPanel panelBotonVolver;

    public InformacionUsuario() {
        setTitle("Mi información - ParqueAPP");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // navbar 
        JPanel navbarPlaceholder = new JPanel(new BorderLayout());
        navbarPlaceholder.setPreferredSize(new Dimension(900, 50));
        navbarPlaceholder.setBackground(new Color(153, 255, 204));

        // nombre del cliente
        lblClienteNombre = new JLabel("Cliente: ---");
        lblClienteNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblClienteNombre.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

        // boton cerrar sesion
        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBackground(new Color(255, 153, 153));
        btnCerrarSesion.setForeground(Color.BLACK);
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        navbarPlaceholder.add(lblClienteNombre, BorderLayout.WEST);
        navbarPlaceholder.add(btnCerrarSesion, BorderLayout.EAST);

        panelPrincipal.add(navbarPlaceholder, BorderLayout.NORTH);

        // panel central
        panelCentro = new JPanel();
        panelCentro.setBackground(new Color(240, 240, 240));
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblTituloInfo = new JLabel("Mi información", SwingConstants.CENTER);
        lblTituloInfo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloInfo.setOpaque(true);
        lblTituloInfo.setBackground(new Color(153, 255, 204));
        lblTituloInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTituloInfo.setPreferredSize(new Dimension(300, 40));
        lblTituloInfo.setMaximumSize(new Dimension(300, 40));
        panelCentro.add(lblTituloInfo);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        // panel de datos personales
        JPanel panelDatos = new JPanel(new GridLayout(2, 4, 10, 10));
        panelDatos.setBackground(new Color(240, 240, 240));

        String[] etiquetas = {
                "Identificación del cliente", "Nombre del cliente",
                "Correo electrónico del cliente", "Teléfono del cliente"
        };

        for (String texto : etiquetas) {
            JLabel lblCampo = new JLabel(texto);
            lblCampo.setOpaque(true);
            lblCampo.setBackground(new Color(153, 255, 153));
            lblCampo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblCampo.setHorizontalAlignment(SwingConstants.CENTER);
            panelDatos.add(lblCampo);
        }

        // celdas vacías para los datos
        for (int i = 0; i < 4; i++) {
            JLabel lblDato = new JLabel("");
            lblDato.setHorizontalAlignment(SwingConstants.CENTER);
            lblDato.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            panelDatos.add(lblDato);
        }

        panelCentro.add(panelDatos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 30)));

        // seccion de vehiculos
        JLabel lblVehiculos = new JLabel("Vehículos", SwingConstants.CENTER);
        lblVehiculos.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblVehiculos.setOpaque(true);
        lblVehiculos.setBackground(new Color(153, 255, 204));
        lblVehiculos.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblVehiculos.setPreferredSize(new Dimension(200, 30));
        lblVehiculos.setMaximumSize(new Dimension(200, 30));
        panelCentro.add(lblVehiculos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] columnas = {"Tipo de vehículo", "Placa del vehículo", "Modelo", "Color", "Marca"};
        Object[][] datos = {};
        tablaVehiculos = new JTable(new javax.swing.table.DefaultTableModel(datos, columnas));
        tablaVehiculos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaVehiculos.setRowHeight(25);
        tablaVehiculos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        // evitar mover columnas
        tablaVehiculos.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollTabla = new JScrollPane(tablaVehiculos);
        scrollTabla.setPreferredSize(new Dimension(600, 120));
        scrollTabla.getViewport().setBackground(Color.WHITE);

        JPanel panelTabla = new JPanel();
        panelTabla.setBackground(new Color(240, 240, 240));
        panelTabla.add(scrollTabla);
        panelCentro.add(panelTabla);

        // boton Volver
        panelBotonVolver = new JPanel();
        panelBotonVolver.setBackground(Color.WHITE);

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(153, 255, 204));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(100, 35));
        panelBotonVolver.add(btnVolver);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotonVolver, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // getters para el controlador
    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public JLabel getLblClienteNombre() {
        return lblClienteNombre;
    }

    public JTable getTablaVehiculos() {
        return tablaVehiculos;
    }

    public JPanel getPanelCentro() {
        return panelCentro;
    }

    public JPanel getPanelBotonVolver() {
        return panelBotonVolver;
    }

    // mostrar los datos personales del usuario
    public void setDatosUsuario(String[] datos) {
        JPanel panelDatos = (JPanel) panelCentro.getComponent(2);
        for (int i = 0; i < 4; i++) {
            JLabel lbl = (JLabel) panelDatos.getComponent(4 + i);
            lbl.setText(datos[i]);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InformacionUsuario vista = new InformacionUsuario();
            new controlador.InformacionUsuarioController(vista);
            vista.setVisible(true);
        });
    }
}
