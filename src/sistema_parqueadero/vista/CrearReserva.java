package vista;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
public class CrearReserva extends JFrame {


    public JComboBox<String> cbTipoVehiculo;
    public JComboBox<String> cbSeleccionVehiculo;
    public JComboBox<String> cbLugar;
    public JButton btnVolver, btnRegistrar;
    public JPanel panelMapa;


    public JDateChooser fechaEntrada, fechaSalida;
    public JSpinner horaEntrada, horaSalida;

    public CrearReserva() {
        setTitle("Reservar parqueadero - ParqueAPP");
        setSize(950, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // nabra general
        NavbarPanel navbar = new NavbarPanel(this);
        navbar.setBounds(0, 0, 950, 50);
        add(navbar);

        // titulos
        JLabel lblTituloPrincipal = new JLabel("Reserva de parqueadero", SwingConstants.CENTER);
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

        fechaEntrada = new JDateChooser();
        fechaEntrada.setBounds(230, y, 150, 30);
        add(fechaEntrada);

        // hora de entrada
        JLabel lblHoraEntrada = new JLabel("Hora de entrada:");
        lblHoraEntrada.setBounds(480, y, 150, 25);
        add(lblHoraEntrada);

        horaEntrada = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor1 = new JSpinner.DateEditor(horaEntrada, "HH:mm");
        horaEntrada.setEditor(timeEditor1);
        horaEntrada.setValue(new Date()); 
        horaEntrada.setBounds(630, y, 150, 30);
        add(horaEntrada);

        y += 60;

        // tipo de vehiculo
        JLabel lblTipo = new JLabel("Tipo de vehículo:");
        lblTipo.setBounds(90, y, 150, 25);
        add(lblTipo);

        cbTipoVehiculo = new JComboBox<>(new String[]{"moto", "carro", "cicla", "camioneta"});
        cbTipoVehiculo.setBounds(230, y, 150, 30);
        add(cbTipoVehiculo);

        // fecha de salida
        JLabel lblFechaSalida = new JLabel("Fecha de salida:");
        lblFechaSalida.setBounds(480, y, 150, 25);
        add(lblFechaSalida);

        fechaSalida = new JDateChooser();
        fechaSalida.setBounds(630, y, 150, 30);
        add(fechaSalida);

        y += 60;

        // hora de salida
        JLabel lblHoraSalida = new JLabel("Hora de salida:");
        lblHoraSalida.setBounds(90, y, 150, 25);
        add(lblHoraSalida);

        horaSalida = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(horaSalida, "HH:mm");
        horaSalida.setEditor(timeEditor2);
        horaSalida.setValue(new Date());
        horaSalida.setBounds(230, y, 150, 30);
        add(horaSalida);

        // seleccione el vehiculo
        JLabel lblVehiculo = new JLabel("Seleccione el vehículo:");
        lblVehiculo.setBounds(480, y, 150, 25);
        add(lblVehiculo);

        cbSeleccionVehiculo = new JComboBox<>();
        cbSeleccionVehiculo.setBounds(630, y, 150, 30);
        add(cbSeleccionVehiculo);

        y += 60;

        // lugar a reserva
        JLabel lblLugar = new JLabel("Lugar a reserva:");
        lblLugar.setBounds(90, y, 150, 25);
        add(lblLugar);

        cbLugar = new JComboBox<>();
        cbLugar.setBounds(230, y, 90, 30);
        add(cbLugar);

        // boton para registrar
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(51, 102, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBounds(360, 410, 220, 45);
        add(btnRegistrar);

        // mapa
        JLabel lblMapa = new JLabel("Mapa del parqueadero");
        lblMapa.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMapa.setBounds(100, 470, 200, 25);
        add(lblMapa);

        panelMapa = new JPanel(new GridLayout(3, 4, 30, 20));
        panelMapa.setBackground(new Color(204, 229, 255));
        panelMapa.setBounds(160, 510, 600, 200);
        add(panelMapa);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrearReserva vista = new CrearReserva();
            vista.setVisible(true);
        });
    }
}