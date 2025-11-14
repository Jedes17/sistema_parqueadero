package vista;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class EditarReserva extends JFrame {

    public JDateChooser dateEntrada, dateSalida;
    public JSpinner spnHoraEntrada, spnHoraSalida;
    public JButton btnRegistrar, btnVolver;

    public EditarReserva() {
        setTitle("Edición de reserva - ParqueAPP");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);

        // navbar
        NavbarPanel navbar = new NavbarPanel(this);
        panelPrincipal.add(navbar, BorderLayout.NORTH);

        // panel central
        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTituloFormulario = new JLabel("Edición de reserva", SwingConstants.CENTER);
        lblTituloFormulario.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloFormulario.setOpaque(true);
        lblTituloFormulario.setBackground(new Color(153, 255, 204));
        lblTituloFormulario.setPreferredSize(new Dimension(300, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panelCentro.add(lblTituloFormulario, gbc);
        gbc.gridwidth = 1;

        // componentes con JCalendar y Spinner de hora
        JLabel lblFechaEntrada = new JLabel("Fecha de entrada:");
        dateEntrada = new JDateChooser();
        dateEntrada.setDateFormatString("dd/MM/yyyy");
        dateEntrada.setPreferredSize(new Dimension(150, 30));

        JLabel lblHoraEntrada = new JLabel("Hora de entrada:");
        SpinnerDateModel modelHoraEntrada = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        spnHoraEntrada = new JSpinner(modelHoraEntrada);
        JSpinner.DateEditor editorEntrada = new JSpinner.DateEditor(spnHoraEntrada, "HH:mm");
        spnHoraEntrada.setEditor(editorEntrada);

        JLabel lblFechaSalida = new JLabel("Fecha de salida:");
        dateSalida = new JDateChooser();
        dateSalida.setDateFormatString("dd/MM/yyyy");
        dateSalida.setPreferredSize(new Dimension(150, 30));

        JLabel lblHoraSalida = new JLabel("Hora de salida:");
        SpinnerDateModel modelHoraSalida = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        spnHoraSalida = new JSpinner(modelHoraSalida);
        JSpinner.DateEditor editorSalida = new JSpinner.DateEditor(spnHoraSalida, "HH:mm");
        spnHoraSalida.setEditor(editorSalida);

        // fila 1
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelCentro.add(lblFechaEntrada, gbc);
        gbc.gridx = 1;
        panelCentro.add(dateEntrada, gbc);
        gbc.gridx = 2;
        panelCentro.add(lblHoraEntrada, gbc);
        gbc.gridx = 3;
        panelCentro.add(spnHoraEntrada, gbc);

        // fila 2
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelCentro.add(lblFechaSalida, gbc);
        gbc.gridx = 1;
        panelCentro.add(dateSalida, gbc);
        gbc.gridx = 2;
        panelCentro.add(lblHoraSalida, gbc);
        gbc.gridx = 3;
        panelCentro.add(spnHoraSalida, gbc);

        // botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        panelBotones.setBackground(Color.WHITE);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(0, 51, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setPreferredSize(new Dimension(120, 35));

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(153, 255, 204));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setPreferredSize(new Dimension(120, 35));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        panelCentro.add(panelBotones, gbc);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditarReserva().setVisible(true));
    }
}
