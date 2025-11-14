package vista;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RegistroEstadoVehicular extends JFrame {

    public RegistroEstadoVehicular() {
        setTitle("ParqueAPP - Registro de estado vehicular");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // navbar
        NavbarPanel navbar = new NavbarPanel(this);
        add(navbar, BorderLayout.NORTH);

        // panel principal
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);

        // boton
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(960, 90, 90, 45);
        btnVolver.setBackground(new Color(153, 230, 190));
        btnVolver.setFocusPainted(false);
        mainPanel.add(btnVolver);

        // titulo principal
        JLabel lblTitulo = new JLabel("Registro de estado vehicular", SwingConstants.CENTER);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(198, 255, 220));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(300, 80, 400, 50);
        mainPanel.add(lblTitulo);

        // panel de informacion
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new LineBorder(Color.BLACK, 1));
        infoPanel.setBounds(120, 160, 860, 460);
        mainPanel.add(infoPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.BOTH;

        // Informacion superior (entrada/salida y cliente) 
        JPanel topInfo = new JPanel(new GridLayout(2, 6));
        topInfo.setBorder(new LineBorder(Color.BLACK, 1));
        addTopInfoCells(topInfo);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 1;
        c.weighty = 0.15;
        infoPanel.add(topInfo, c);

        JPanel clientVehicleInfo = new JPanel(new GridLayout(2, 6));
        clientVehicleInfo.setBorder(new LineBorder(Color.BLACK, 1));
        addClientVehicleCells(clientVehicleInfo);
        c.gridy = 1;
        c.weighty = 0.15;
        infoPanel.add(clientVehicleInfo, c);

        // seccion de 2 filas y 3 columnas
        JPanel bottomGrid = new JPanel(new GridLayout(2, 3, 10, 10));
        bottomGrid.setBackground(Color.WHITE);

        // fila 1
        bottomGrid.add(createObservacionesEntrada());
        bottomGrid.add(createEstadoGeneral());
        bottomGrid.add(createObservacionesSalida());

        // fila 2
        bottomGrid.add(createRegistroFotoEntrada());
        bottomGrid.add(createVacioPanel());
        bottomGrid.add(createRegistroFotoSalida());

        c.gridy = 2;
        c.weighty = 0.7;
        infoPanel.add(bottomGrid, c);

        add(mainPanel, BorderLayout.CENTER);
    }

    // encabezados superiores
    private static void addTopInfoCells(JPanel container) {
        container.add(makeLabelCell("Fecha de entrada:"));
        container.add(makeLabelCell("Hora de entrada:"));
        container.add(makeLabelCell("Tipo de vehículo:"));
        container.add(makeLabelCell("Placa:"));
        container.add(makeLabelCell("Fecha de salida:"));
        container.add(makeLabelCell("Hora de salida:"));
    }

    // informacion 
    private static void addClientVehicleCells(JPanel container) {
        container.setLayout(new GridLayout(2, 4));
        container.add(makeLabelCell("Identificación:"));
        container.add(makeLabelCell("Nombre:"));
        container.add(makeLabelCell("Correo:"));
        container.add(makeLabelCell("Teléfono:"));
        container.add(makeLabelCell("Marca:"));
        container.add(makeLabelCell("Modelo:"));
        container.add(makeLabelCell("Color:"));
        container.add(makeLabelCell("Estado de reserva:"));
    }

    private static JPanel makeLabelCell(String label) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        JLabel l1 = new JLabel("<html><b>" + label + "</b></html>");
        l1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        p.add(l1, BorderLayout.WEST);
        return p;
    }

    // paneles independientes
    private static JPanel createObservacionesEntrada() {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Observaciones en entrada", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(lbl, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setName("areaObsEntrada");
        area.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        area.setBackground(new Color(245, 245, 245));
        p.add(new JScrollPane(area), BorderLayout.CENTER);

        return p;
    }

    private static JPanel createEstadoGeneral() {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Estado general", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(lbl, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setName("areaEstadoGeneral");
        area.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        area.setBackground(new Color(245, 245, 245));
        p.add(new JScrollPane(area), BorderLayout.CENTER);

        return p;
    }

    private static JPanel createObservacionesSalida() {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Observaciones en salida", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(lbl, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setName("areaObsSalida");
        area.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        area.setBackground(new Color(245, 245, 245));
        p.add(new JScrollPane(area), BorderLayout.CENTER);

        return p;
    }

    private static JPanel createRegistroFotoEntrada() {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Registro fotográfico entrada", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        p.add(lbl, BorderLayout.NORTH);

        JPanel cuadro = new JPanel();
        cuadro.setPreferredSize(new Dimension(200, 150));
        cuadro.setBackground(new Color(240, 240, 240));
        cuadro.setBorder(new LineBorder(Color.GRAY, 1));
        p.add(cuadro, BorderLayout.CENTER);

        return p;
    }

    private static JPanel createRegistroFotoSalida() {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Registro fotográfico salida", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        p.add(lbl, BorderLayout.NORTH);

        JPanel cuadro = new JPanel();
        cuadro.setPreferredSize(new Dimension(200, 150));
        cuadro.setBackground(new Color(240, 240, 240));
        cuadro.setBorder(new LineBorder(Color.GRAY, 1));
        p.add(cuadro, BorderLayout.CENTER);

        return p;
    }

    private static JPanel createVacioPanel() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroEstadoVehicular().setVisible(true));
    }
}
