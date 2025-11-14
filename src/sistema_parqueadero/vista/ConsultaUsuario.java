package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultaUsuario extends JFrame {

    // variables 
    private JButton btnVolver;
    private JButton btnFiltros;
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtPlaca; 
    private JTable tabla;

    public ConsultaUsuario() {
        setTitle("ParqueAPP - Consulta de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // navbar 
        NavbarPanel navbar = new NavbarPanel(this);
        add(navbar, BorderLayout.NORTH);

        //  panel princiapl
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        // titulo
        JLabel lblTitulo = new JLabel("Consultar Usuarios Registrados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(180, 255, 200));
        lblTitulo.setBounds(400, 20, 400, 40);
        mainPanel.add(lblTitulo);

        // boton volver
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setBackground(new Color(180, 255, 200));
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(980, 20, 100, 35);
        mainPanel.add(btnVolver);

        // filtros
        JLabel lblCedula = new JLabel("Identificación (Cédula)");
        lblCedula.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCedula.setBounds(80, 100, 200, 20);
        mainPanel.add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(80, 125, 200, 25);
        txtCedula.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtCedula);

        // nombre
        JLabel lblNombre = new JLabel("Nombre completo");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNombre.setBounds(80, 170, 200, 20);
        mainPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 195, 200, 25);
        txtNombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtNombre);

        // placa
        JLabel lblPlaca = new JLabel("Placa del vehículo");
        lblPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPlaca.setBounds(80, 240, 200, 20);
        mainPanel.add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(80, 265, 200, 25);
        txtPlaca.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtPlaca);

        // boton de filtro
        btnFiltros = new JButton("Aplicar Filtros");
        btnFiltros.setFont(new Font("Arial", Font.BOLD, 12));
        btnFiltros.setBackground(new Color(0, 60, 255));
        btnFiltros.setForeground(Color.WHITE);
        btnFiltros.setFocusPainted(false);
        btnFiltros.setBounds(100, 310, 150, 35);
        mainPanel.add(btnFiltros);

        // nombre tabla
        String[] columnas = {
            "Identificación",
            "Nombre completo",
            "Correo electrónico",
            "N° vehículos registrados",
            "Estado",
            "Acciones"
        };

        // no edita
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(false);

        int[] anchos = {150, 220, 220, 180, 120, 100};
        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        JScrollPane scroll = new JScrollPane(tabla,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(360, 100, 690, 450);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        mainPanel.add(scroll);
    }

    // getters
    public JButton getBtnVolver() { 
        return btnVolver; 
    }
    public JButton getBtnFiltros() { 
        return btnFiltros; 
    }
    public JTextField getTxtCedula() { 
        return txtCedula; 
    }
    public JTextField getTxtNombre() { 
        return txtNombre; 
    }
    public JTextField getTxtPlaca() { 
        return txtPlaca; 
    } 
    public JTable getTabla() { 
        return tabla; 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultaUsuario vista = new ConsultaUsuario();
            new controlador.ControladorConsultaUsuario(vista);
            vista.setVisible(true);
        });
    }
}