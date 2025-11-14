package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultarEstadoVehicular extends JFrame {

    // Variable
    private JButton btnVolver;
    private JButton btnFiltros;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTextField txtTipo;
    private JTextField txtPlaca;
    private JTextField txtCliente;
    private JTextField txtOperador;
    private JTable tabla;

    public ConsultarEstadoVehicular() {
        setTitle("ParqueAPP - Consultar Estado Vehicular");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // nabart genera
        NavbarPanel navbar = new NavbarPanel(this);
        add(navbar, BorderLayout.NORTH);

        // panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        // titulo
        JLabel lblTitulo = new JLabel("Consultar registro de estado vehicular", SwingConstants.CENTER);
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
        btnVolver.setBounds(950, 20, 100, 35);
        mainPanel.add(btnVolver);

        // filtros
        JLabel lblTipo = new JLabel("Tipo de vehículo");
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTipo.setBounds(80, 120, 150, 20);
        mainPanel.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(80, 145, 200, 25);
        txtTipo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtTipo);

        JLabel lblPlaca = new JLabel("Placa de vehículo");
        lblPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPlaca.setBounds(80, 190, 150, 20);
        mainPanel.add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(80, 215, 200, 25);
        txtPlaca.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtPlaca);

        
        JLabel lblCliente = new JLabel("Identificación del cliente");
        lblCliente.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCliente.setBounds(80, 260, 180, 20);
        mainPanel.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(80, 285, 200, 25);
        txtCliente.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtCliente);

        JLabel lblOperador = new JLabel("Identificación del operador");
        lblOperador.setFont(new Font("Arial", Font.PLAIN, 14));
        lblOperador.setBounds(80, 330, 200, 20);
        mainPanel.add(lblOperador);

        txtOperador = new JTextField();
        txtOperador.setBounds(80, 355, 200, 25);
        txtOperador.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        mainPanel.add(txtOperador);

        // boton filtros
        btnFiltros = new JButton("Aplicar Filtros");
        btnFiltros.setFont(new Font("Arial", Font.BOLD, 12));
        btnFiltros.setBackground(new Color(0, 60, 255));
        btnFiltros.setForeground(Color.WHITE);
        btnFiltros.setFocusPainted(false);
        btnFiltros.setBounds(100, 400, 150, 35);
        mainPanel.add(btnFiltros);

        // ocultar botones
        btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEditar.setBackground(new Color(0, 150, 0));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.setBounds(70, 450, 100, 35);
        btnEditar.setVisible(false);
        mainPanel.add(btnEditar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar.setBackground(new Color(200, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBounds(190, 450, 100, 35);
        btnCancelar.setVisible(false); 
        mainPanel.add(btnCancelar);

        // titulos
        String[] columnas = {
            "Fecha de entrada", "Hora de entrada",
            "Fecha de salida", "Hora de salida",
            "Placa del vehículo", "Tipo de vehículo",
            "Identificación cliente","Lugar reservado", 
            "Estado de reserva", "Consultar"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        tabla.getTableHeader().setReorderingAllowed(false);

        int[] anchos = {150, 120, 150, 120, 150, 150, 180, 180, 150, 150};
        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        JScrollPane scroll = new JScrollPane(tabla,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(360, 100, 645, 450);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        mainPanel.add(scroll);
    }

    public JButton getBtnVolver() { 
        return btnVolver; 
    }
    public JButton getBtnFiltros() { 
        return btnFiltros; 
    }
    public JButton getBtnEditar() { 
        return btnEditar; 
    }
    public JButton getBtnCancelar() { 
        return btnCancelar; 
    }
    public JTextField getTxtTipo() { 
        return txtTipo; 
    }
    public JTextField getTxtPlaca() { 
        return txtPlaca; 
    }
    public JTextField getTxtCliente() { 
        return txtCliente; 
    }
    public JTextField getTxtOperador() { 
        return txtOperador; 
    }
    public JTable getTabla() { 
        return tabla; 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarEstadoVehicular().setVisible(true));
    }
}
