package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class DetalleSolicitudReserva extends JFrame {

    public JButton btnVolver, btnAceptar, btnCancelar;
    public JPanel panelMapa;


    public JTextField txtFechaEntrada, txtHoraEntrada, txtTipoVehiculo, txtFechaSalida,
            txtHoraSalida, txtVehiculo, txtLugar, txtIdentificacionCliente;

    public DetalleSolicitudReserva() {
        setTitle("Detalle Solicitud de Reserva - ParqueAPP");
        setSize(950, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // navbar genera
        NavbarPanel navbar = new NavbarPanel(this);
        navbar.setBounds(0, 0, 950, 50);
        add(navbar);

        // titulo
        JLabel lblTituloPrincipal = new JLabel("Detalles de la Solicitud de Reserva", SwingConstants.CENTER);
        lblTituloPrincipal.setOpaque(true);
        lblTituloPrincipal.setBackground(new Color(178, 255, 178));
        lblTituloPrincipal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloPrincipal.setBounds(280, 80, 370, 40);
        add(lblTituloPrincipal);

        // boton volver
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolver.setBackground(new Color(178, 255, 178));
        btnVolver.setBounds(830, 80, 90, 30);
        add(btnVolver);

        int y = 160;

        // fecha de entrada
        JLabel lblFechaEntrada = new JLabel("Fecha de entrada:");
        lblFechaEntrada.setBounds(90, y, 150, 25);
        add(lblFechaEntrada);

        txtFechaEntrada = crearCampoTexto(230, y);
        add(txtFechaEntrada);

        JLabel lblHoraEntrada = new JLabel("Hora de entrada:");
        lblHoraEntrada.setBounds(480, y, 150, 25);
        add(lblHoraEntrada);

        txtHoraEntrada = crearCampoTexto(630, y);
        add(txtHoraEntrada);

        y += 60;

        // tipo de vehiculo y fecha salida
        JLabel lblTipo = new JLabel("Tipo de vehículo:");
        lblTipo.setBounds(90, y, 150, 25);
        add(lblTipo);

        txtTipoVehiculo = crearCampoTexto(230, y);
        add(txtTipoVehiculo);

        JLabel lblFechaSalida = new JLabel("Fecha de salida:");
        lblFechaSalida.setBounds(480, y, 150, 25);
        add(lblFechaSalida);

        txtFechaSalida = crearCampoTexto(630, y);
        add(txtFechaSalida);

        y += 60;

        // hora salida y vehiculo 
        JLabel lblHoraSalida = new JLabel("Hora de salida:");
        lblHoraSalida.setBounds(90, y, 150, 25);
        add(lblHoraSalida);

        txtHoraSalida = crearCampoTexto(230, y);
        add(txtHoraSalida);

        JLabel lblVehiculo = new JLabel("Seleccione el vehículo:");
        lblVehiculo.setBounds(480, y, 150, 25);
        add(lblVehiculo);

        txtVehiculo = crearCampoTexto(630, y);
        add(txtVehiculo);

        y += 60;

        // lugar y identificacion cliente 
        JLabel lblLugar = new JLabel("Lugar a reserva:");
        lblLugar.setBounds(90, y, 150, 25);
        add(lblLugar);

        txtLugar = crearCampoTexto(230, y);
        add(txtLugar);

        JLabel lblIdentificacion = new JLabel("Identificación del cliente:");
        lblIdentificacion.setBounds(480, y, 180, 25);
        add(lblIdentificacion);

        txtIdentificacionCliente = crearCampoTexto(630, y);
        add(txtIdentificacionCliente);

        // botones
        btnAceptar = new JButton("Aceptar solicitud");
        btnAceptar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAceptar.setBackground(new Color(51, 153, 255));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBounds(280, 460, 180, 45);
        add(btnAceptar);

        btnCancelar = new JButton("Cancelar solicitud");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(255, 80, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(490, 460, 180, 45);
        add(btnCancelar);

        // mapa
        JLabel lblMapa = new JLabel("Mapa del parqueadero");
        lblMapa.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMapa.setBounds(100, 520, 200, 25);
        add(lblMapa);

        panelMapa = new JPanel(new GridLayout(3, 4, 30, 20));
        panelMapa.setBackground(new Color(204, 229, 255));

        JScrollPane scrollMapa = new JScrollPane(panelMapa);
        scrollMapa.setBounds(120, 550, 700, 200);
        scrollMapa.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollMapa.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollMapa);
    }

    private JTextField crearCampoTexto(int x, int y) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, 150, 30);
        campo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        campo.setEditable(false); // no editable
        campo.setBackground(new Color(245, 245, 245));
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DetalleSolicitudReserva vista = new DetalleSolicitudReserva();
            vista.setVisible(true);
        });
    }
}
