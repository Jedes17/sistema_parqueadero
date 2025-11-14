package vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Globales;

public class Principal extends JFrame {

    private JLabel lblTotal;
    private JLabel lblOcupados;
    private JLabel lblDisponibles;
    private JPanel mapa;

    private List<JButton> botonesAccion;

    public JButton btnMiInformacion;
    public JButton btnReservarEspacio;

    public Principal() {
        setTitle("ParqueAPP - Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // navbar
        NavbarPanel navbar = new NavbarPanel(this);
        add(navbar, BorderLayout.NORTH);

        // panel central
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.WHITE);
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // panel de estadisticas
        JPanel panelStats = new JPanel(new GridLayout(1, 2, 5, 10));
        panelStats.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelStats.setBackground(new Color(240, 240, 240));
        panelStats.setPreferredSize(new Dimension(850, 280));

        JPanel estadisticas = new JPanel();
        estadisticas.setLayout(new BoxLayout(estadisticas, BoxLayout.Y_AXIS));
        estadisticas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        estadisticas.setBackground(new Color(240, 240, 240));

        JLabel lblTituloEst = new JLabel("ESTADÍSTICAS");
        lblTituloEst.setFont(new Font("Arial", Font.BOLD, 14));
        estadisticas.add(lblTituloEst);
        estadisticas.add(Box.createVerticalStrut(10));

        lblTotal = crearEtiquetaValor("ESPACIOS TOTALES:", Color.LIGHT_GRAY);
        estadisticas.add(lblTotal);
        estadisticas.add(Box.createVerticalStrut(8));

        lblOcupados = crearEtiquetaValor("ESPACIOS OCUPADOS:", new Color(255, 102, 102));
        estadisticas.add(lblOcupados);
        estadisticas.add(Box.createVerticalStrut(8));

        lblDisponibles = crearEtiquetaValor("ESPACIOS DISPONIBLES:", new Color(102, 255, 102));
        estadisticas.add(lblDisponibles);

        // panel botones usuario
        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelUsuario.setBackground(Color.WHITE);

        btnMiInformacion = crearBotonAzul("Mi información");
        btnReservarEspacio = crearBotonAzul("Reservar espacio");

        panelUsuario.add(btnMiInformacion);
        panelUsuario.add(btnReservarEspacio);

        // panel de acciones
        JPanel acciones = new JPanel(new GridLayout(3, 3, 12, 12));
        acciones.setBackground(new Color(240, 240, 240));
        acciones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] nombresBotones = {
                "Consultar usos de parqueadero",
                "Consultar Usuarios",
                "Registrar vehículo",
                "Consultar facturas de pago",
                "Registrar estado vehicular de entrada",
                "Registrar estado vehicular de salida",
                "Consultar registros de estados vehicular",
                "Consultar solicitudes de reserva"
        };

        botonesAccion = new ArrayList<>();
        for (String texto : nombresBotones) {
            JButton boton = crearBotonVerde(texto);
            botonesAccion.add(boton);
            acciones.add(boton);
        }

        panelStats.add(estadisticas);
        panelStats.add(acciones);
        centro.add(panelUsuario);
        centro.add(panelStats);
        centro.add(Box.createVerticalStrut(25));

        // mapa del parqueadero
        mapa = new JPanel();
        mapa.setBackground(Color.WHITE);
        centro.add(mapa);

        // scroll
        JScrollPane scroll = new JScrollPane(centro);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);
    }

    // metodos auxiliares
    private JLabel crearEtiquetaValor(String titulo, Color color) {
        JLabel label = new JLabel(titulo + " 0");
        label.setOpaque(true);
        label.setBackground(color);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }

    private JButton crearBotonVerde(String texto) {
        JButton btn = new JButton("<html><center>" + texto + "</center></html>");
        btn.setBackground(new Color(179, 255, 179));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 255, 150), 1, true));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(260, 60));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        return btn;
    }

    private JButton crearBotonAzul(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(100, 149, 237));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(180, 40));
        return btn;
    }

    // metodo para reorganizar botones visibles en una fila (rol cliente)
    public void reorganizarAccionesCliente(List<JButton> botonesVisibles) {
        JPanel nuevoPanel = new JPanel(new GridLayout(1, 3, 20, 10));
        nuevoPanel.setBackground(new Color(240, 240, 240));
        nuevoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (JButton boton : botonesVisibles) {
            nuevoPanel.add(boton);
        }
        
        // panelStats
        Container contenedor = lblTotal.getParent().getParent(); 
        if (contenedor instanceof JPanel panelStats) {
            panelStats.remove(1); 
            panelStats.add(nuevoPanel);
            panelStats.revalidate();
            panelStats.repaint();
        }
    }

    public List<JButton> getBotonesAccion() {
        return botonesAccion;
    }

    public void setEstadisticas(int total, int ocupados, int disponibles) {
        lblTotal.setText("ESPACIOS TOTALES: " + total);
        lblOcupados.setText("ESPACIOS OCUPADOS: " + ocupados);
        lblDisponibles.setText("ESPACIOS DISPONIBLES: " + disponibles);
    }

    public void setMapaPanel(JPanel nuevoMapa) {
        mapa.removeAll();
        mapa.setLayout(new BorderLayout());
        mapa.add(nuevoMapa, BorderLayout.CENTER);
        mapa.revalidate();
        mapa.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Principal vista = new Principal();
            new controlador.PrincipalController(vista);
            vista.setVisible(true);
        });
    }
}
