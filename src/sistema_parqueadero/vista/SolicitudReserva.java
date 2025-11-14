package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SolicitudReserva extends JFrame {

    // variables 
    private JButton btnVolver;
    private JButton btnFiltros;
    private JTextField txtTipo;
    private JTextField txtPlaca;
    private JTextField txtIdentificacion; 
    private JTable table;

    public SolicitudReserva() {
        setTitle("Solicitud De Reserva - ParqueAPP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // navbar
        NavbarPanel navbar = new NavbarPanel(this);
        add(navbar, BorderLayout.NORTH);

        // panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // titulo
        JLabel lblTituloSeccion = new JLabel("Solicitud De Reserva", SwingConstants.CENTER);
        lblTituloSeccion.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloSeccion.setOpaque(true);
        lblTituloSeccion.setBackground(new Color(180, 255, 180));
        lblTituloSeccion.setBounds(400, 60, 350, 35);
        mainPanel.add(lblTituloSeccion);

        // boton
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setBackground(new Color(180, 255, 180));
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(920, 60, 100, 35);
        mainPanel.add(btnVolver);

        

        // identificacion 
        JLabel lblIdentificacion = new JLabel("Identificación del cliente");
        lblIdentificacion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblIdentificacion.setBounds(60, 110, 200, 25);
        mainPanel.add(lblIdentificacion);

        txtIdentificacion = new JTextField();
        txtIdentificacion.setBounds(60, 135, 200, 25);
        txtIdentificacion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtIdentificacion);

        // tipo de vehículo
        JLabel lblTipo = new JLabel("Tipo de vehículo");
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTipo.setBounds(60, 175, 150, 25);
        mainPanel.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(60, 200, 200, 25);
        txtTipo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtTipo);

        // placa de vehiculo
        JLabel lblPlaca = new JLabel("Placa de vehículo");
        lblPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPlaca.setBounds(60, 240, 150, 25);
        mainPanel.add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(60, 265, 200, 25);
        txtPlaca.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtPlaca);

        // boton filtro
        btnFiltros = new JButton("Aplicar Filtros");
        btnFiltros.setBounds(60, 310, 140, 30);
        btnFiltros.setBackground(new Color(0, 68, 255));
        btnFiltros.setForeground(Color.WHITE);
        btnFiltros.setFocusPainted(false);
        mainPanel.add(btnFiltros);

        // tabla
        String[] columnas = {
            "Fecha de entrada", "Hora de entrada", "Fecha de salida",
            "Hora de salida", "Cedula del cliente", "Tipo de vehículo",
            "Estado de reserva", "Consultar"
        };

        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        header.setFont(new Font("Arial", Font.BOLD, 13));

        int[] anchos = {140, 120, 140, 120, 140, 140, 120, 100};
        for (int i = 0; i < anchos.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 150, 700, 400);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPanel.add(scrollPane);

        // evento de click en la tabla
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());

                if (col == table.getColumnCount() - 1) { 
                    String tipoVehiculo = (String) table.getValueAt(row, 5);
                    String placaVehiculo = (String) table.getValueAt(row, 4);

                    System.out.println("Tipo: " + tipoVehiculo);
                    System.out.println("Placa: " + placaVehiculo);
                }
            }
        });

        add(mainPanel, BorderLayout.CENTER);
    }

    // getters para el controlador
    public JButton getBtnVolver() { 
        return btnVolver;
    }
    public JButton getBtnFiltros() { 
        return btnFiltros; 
    }
    public JTextField getTxtTipo() { 
        return txtTipo;
    }
    public JTextField getTxtPlaca() { 
        return txtPlaca;
    }
    public JTextField getTxtIdentificacion() {
        return txtIdentificacion; 
    } 
    public JTable getTable() { 
        return table;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SolicitudReserva().setVisible(true));
    }
}
