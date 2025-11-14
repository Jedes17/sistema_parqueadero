package vista;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FacturaDetallada extends JFrame {

    // botones
    public JButton btnGenerarPDF;
    public JButton btnVolver;

    // etiquetas accesibles desde el controlador
    public JLabel lblFechaEntrada, lblHoraEntrada, lblFechaSalida, lblHoraSalida;
    public JLabel lblTipoVehiculo, lblPlacaVehiculo, lblModelo, lblMarca, lblColor;
    public JLabel lblIdentificacion, lblNombreCliente, lblCorreo, lblHorasUso;
    public JTextArea txtEstadoEntrada, txtEstadoSalida;
    public JLabel lblSubtotal, lblTotal;

    public FacturaDetallada() {
        setTitle("ParqueAPP - Facturas de Pago");
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

        // botones
        btnGenerarPDF = new JButton("<html><center>Generar<br>PDF</center></html>");
        btnGenerarPDF.setBounds(40, 90, 110, 60);
        btnGenerarPDF.setBackground(new Color(230, 30, 30));
        btnGenerarPDF.setForeground(Color.WHITE);
        btnGenerarPDF.setFocusPainted(false);
        btnGenerarPDF.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mainPanel.add(btnGenerarPDF);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(960, 90, 90, 45);
        btnVolver.setBackground(new Color(153, 230, 190));
        btnVolver.setFocusPainted(false);
        mainPanel.add(btnVolver);

        JLabel lblTitulo = new JLabel("Facturas de pago", SwingConstants.CENTER);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(198, 255, 220));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(300, 80, 400, 50);
        mainPanel.add(lblTitulo);

        // panel de factura
        JPanel invoicePanel = new JPanel(new GridBagLayout());
        invoicePanel.setBackground(Color.WHITE);
        invoicePanel.setBorder(new LineBorder(Color.BLACK, 1));
        invoicePanel.setBounds(120, 160, 860, 460);
        mainPanel.add(invoicePanel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.BOTH;

        // sección superior (fechas y vehículo) 
        JPanel topInfo = new JPanel(new GridLayout(2, 6));
        topInfo.setBorder(new LineBorder(Color.BLACK, 1));

        lblFechaEntrada = makeInfoLabel("Fecha de entrada:");
        lblHoraEntrada = makeInfoLabel("Hora de entrada:");
        lblFechaSalida = makeInfoLabel("Fecha de salida:");
        lblHoraSalida = makeInfoLabel("Hora de salida:");
        lblTipoVehiculo = makeInfoLabel("Tipo de vehículo:");
        lblPlacaVehiculo = makeInfoLabel("Placa del vehículo:");
        lblHorasUso = makeInfoLabel("Horas de uso:");

        topInfo.add(lblFechaEntrada);
        topInfo.add(lblHoraEntrada);
        topInfo.add(lblFechaSalida);
        topInfo.add(lblHoraSalida);
        topInfo.add(lblTipoVehiculo);
        topInfo.add(lblPlacaVehiculo);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 1;
        c.weighty = 0.15;
        invoicePanel.add(topInfo, c);

        // seccion de informacion del cliente
        JPanel clientInfo = new JPanel(new GridLayout(1, 6));
        clientInfo.setBorder(new LineBorder(Color.BLACK, 1));

        lblModelo = makeInfoLabel("Modelo:");
        lblMarca = makeInfoLabel("Marca:");
        lblColor = makeInfoLabel("Color:");
        lblIdentificacion = makeInfoLabel("Identificación del cliente:");
        lblNombreCliente = makeInfoLabel("Nombre del cliente:");
        lblCorreo = makeInfoLabel("Correo electrónico:");

        clientInfo.add(lblModelo);
        clientInfo.add(lblMarca);
        clientInfo.add(lblColor);
        clientInfo.add(lblIdentificacion);
        clientInfo.add(lblNombreCliente);
        clientInfo.add(lblCorreo);

        c.gridy = 1;
        c.weighty = 0.12;
        invoicePanel.add(clientInfo, c);

        // seccion inferior (estados y totales) 
        JPanel middleSection = new JPanel(new GridLayout(1, 3));
        middleSection.setBorder(new LineBorder(Color.BLACK, 1));

        // estado entrada
        JPanel panelEntrada = new JPanel(new BorderLayout());
        panelEntrada.setBackground(Color.WHITE);
        JLabel lblEntradaTitulo = new JLabel("Estado general en entrada", SwingConstants.CENTER);
        lblEntradaTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEstadoEntrada = new JTextArea();
        txtEstadoEntrada.setEditable(false);
        txtEstadoEntrada.setBackground(new Color(245, 245, 245));
        txtEstadoEntrada.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        lblSubtotal = new JLabel("Subtotal: $0", SwingConstants.CENTER);
        lblSubtotal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelEntrada.add(lblEntradaTitulo, BorderLayout.NORTH);
        panelEntrada.add(txtEstadoEntrada, BorderLayout.CENTER);
        panelEntrada.add(lblSubtotal, BorderLayout.SOUTH);

        // total central
        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(Color.WHITE);
        lblTotal = new JLabel("Total (IVA 19%): $0", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelTotal.add(lblTotal, BorderLayout.CENTER);

        // estado salida
        JPanel panelSalida = new JPanel(new BorderLayout());
        panelSalida.setBackground(Color.WHITE);
        JLabel lblSalidaTitulo = new JLabel("Estado general en salida", SwingConstants.CENTER);
        lblSalidaTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEstadoSalida = new JTextArea();
        txtEstadoSalida.setEditable(false);
        txtEstadoSalida.setBackground(new Color(245, 245, 245));
        txtEstadoSalida.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        JLabel lblImpuestos = new JLabel("Impuestos aplicables (19%)", SwingConstants.CENTER);
        lblImpuestos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelSalida.add(lblSalidaTitulo, BorderLayout.NORTH);
        panelSalida.add(txtEstadoSalida, BorderLayout.CENTER);
        panelSalida.add(lblImpuestos, BorderLayout.SOUTH);

        middleSection.add(panelEntrada);
        middleSection.add(panelTotal);
        middleSection.add(panelSalida);

        c.gridy = 2;
        c.weighty = 0.6;
        invoicePanel.add(middleSection, c);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JLabel makeInfoLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        return label;
    }
}
