package controlador;

import modelo.CuadranteLugar;
import modelo.CuadranteLugarDao;
import vista.Principal;
import vista.ConsultaUsuario;
import vista.RegistrarVehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import vista.CrearReserva;

public class PrincipalController {

    private final Principal vista;
    private final CuadranteLugarDao dao;

    public PrincipalController(Principal vista) {
        this.vista = vista;
        this.dao = new CuadranteLugarDao();

        inicializarVista();
        configurarEventos();
        configurarVistaSegunRol();
    }

    private void inicializarVista() {
        List<CuadranteLugar> lista = dao.listarCuadrantesYLugares();

        int total = lista.size();
        int ocupados = (int) lista.stream().filter(l -> !l.isDisponible()).count();
        int disponibles = total - ocupados;

        vista.setEstadisticas(total, ocupados, disponibles);
        vista.setMapaPanel(generarMapa(lista));
    }

    private void configurarEventos() {
        List<JButton> botones = vista.getBotonesAccion();
        // ✅ "Reservar espacio" → abre CrearReserva
        vista.btnReservarEspacio.addActionListener(e -> {
            vista.dispose();
            CrearReserva ventana = new CrearReserva();
            new CrearReservaController(ventana);
            ventana.setVisible(true);
        });

        // "Consultar Usuarios"
        botones.get(1).addActionListener(e -> {
            vista.dispose();
            ConsultaUsuario ventana = new ConsultaUsuario();
            new ControladorConsultaUsuario(ventana);
            ventana.setVisible(true);
        });

        // ✅ "Registrar Vehículo" → abre RegistrarVehiculo
        botones.get(2).addActionListener(e -> {
            vista.dispose();
            RegistrarVehiculo ventana = new RegistrarVehiculo();
            new RegistrarVehiculoController(ventana);
            ventana.setVisible(true);
        });

        // ✅ "Mi información" → abre InformacionUsuario
        vista.btnMiInformacion.addActionListener(e -> {
            vista.dispose();
            vista.InformacionUsuario ventana = new vista.InformacionUsuario();
            new controlador.InformacionUsuarioController(ventana);
            ventana.setVisible(true);
        });
        // ✅ "Consultar facturas de pago" → abre FacturaConFiltro
        botones.get(3).addActionListener(e -> {
            vista.dispose();
            vista.FacturaConFiltro ventana = new vista.FacturaConFiltro();
            new controlador.FacturaConFiltroController(ventana);
            ventana.setVisible(true);
        });
        // ✅ "Consultar registros de estados vehicular" → abre ConsultarEstadoVehicular
        botones.get(6).addActionListener(e -> {
            vista.dispose();
            vista.ConsultarEstadoVehicular ventana = new vista.ConsultarEstadoVehicular();
            new controlador.ConsultarEstadoVehicularController(ventana);
            ventana.setVisible(true);
        });

        // ✅ "Consultar solicitudes de reserva" → abre SolicitudReserva
        botones.get(7).addActionListener(e -> {
            vista.dispose();
            vista.SolicitudReserva ventana = new vista.SolicitudReserva();
            new controlador.SolicitudReservaController(ventana);
            ventana.setVisible(true);
        });
    }

    private void configurarVistaSegunRol() {
        String rol = modelo.Globales.global_rol_usuario;

        JButton btnMiInfo = vista.btnMiInformacion;
        JButton btnReservar = vista.btnReservarEspacio;
        List<JButton> botones = vista.getBotonesAccion();

        // Índices actualizados (sin “Ver reportes generales”)
        JButton btnConsultarUsos = botones.get(0);
        JButton btnConsultarUsuarios = botones.get(1);
        JButton btnRegistrarVehiculo = botones.get(2);
        JButton btnConsultarFacturas = botones.get(3);
        JButton btnRegistrarEntrada = botones.get(4);
        JButton btnRegistrarSalida = botones.get(5);
        JButton btnConsultarEstados = botones.get(6);
        JButton btnConsultarSolicitudes = botones.get(7);

        // Mostrar todo inicialmente
        btnMiInfo.setVisible(true);
        btnReservar.setVisible(true);
        botones.forEach(b -> b.setVisible(true));

        switch (rol.toLowerCase()) {
            case "cliente" -> {
                btnRegistrarVehiculo.setVisible(true);
                btnConsultarFacturas.setVisible(true);
                btnConsultarEstados.setVisible(true);
                btnMiInfo.setVisible(true);
                btnReservar.setVisible(true);

                btnConsultarUsos.setVisible(false);
                btnConsultarUsuarios.setVisible(false);
                btnRegistrarEntrada.setVisible(false);
                btnRegistrarSalida.setVisible(false);
                btnConsultarSolicitudes.setVisible(false);

                // ✅ Reorganizar los botones visibles (3 en fila)
                List<JButton> visibles = new ArrayList<>();
                visibles.add(btnRegistrarVehiculo);
                visibles.add(btnConsultarFacturas);
                visibles.add(btnConsultarEstados);

                vista.reorganizarAccionesCliente(visibles);
            }

            case "administrador" -> {
                btnMiInfo.setVisible(false);
                btnReservar.setVisible(false);
            }

            case "operador" -> {
                btnMiInfo.setVisible(false);
                btnReservar.setVisible(false);
                btnConsultarUsos.setVisible(false);
            }

            default -> System.out.println("Rol no reconocido: " + rol);
        }
    }

    // --- MAPA ---
    private JPanel generarMapa(List<CuadranteLugar> lista) {
        JPanel panelMapa = new JPanel();
        panelMapa.setLayout(new BoxLayout(panelMapa, BoxLayout.Y_AXIS));
        panelMapa.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Mapa del parqueadero");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMapa.add(titulo);

        JPanel leyenda = new JPanel();
        leyenda.setBackground(Color.WHITE);
        leyenda.add(crearEtiquetaColor("Disponible", Color.GREEN));
        leyenda.add(crearEtiquetaColor("Ocupado", Color.RED));
        panelMapa.add(leyenda);
        panelMapa.add(Box.createVerticalStrut(10));

        Map<String, List<CuadranteLugar>> agrupado = lista.stream()
                .collect(Collectors.groupingBy(CuadranteLugar::getNombre, LinkedHashMap::new, Collectors.toList()));

        for (String nombreCuadrante : agrupado.keySet()) {
            JPanel panelCuadrante = crearPanelCuadrante(nombreCuadrante, agrupado.get(nombreCuadrante));
            panelMapa.add(panelCuadrante);
            panelMapa.add(Box.createVerticalStrut(15));
        }

        return panelMapa;
    }

    private JPanel crearPanelCuadrante(String nombre, List<CuadranteLugar> lugares) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblTitulo = new JLabel(nombre + " (" + lugares.get(0).getTipoVehiculo() + ")");
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(lblTitulo, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(3, 4, 5, 5));
        grid.setBackground(Color.WHITE);
        for (CuadranteLugar lugar : lugares) {
            JButton btn = new JButton(lugar.getNumero());
            btn.setOpaque(true);
            btn.setBackground(lugar.isDisponible() ? Color.GREEN : Color.RED);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setPreferredSize(new Dimension(50, 30));
            grid.add(btn);
        }

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    private JLabel crearEtiquetaColor(String texto, Color color) {
        JLabel label = new JLabel(texto);
        label.setOpaque(true);
        label.setBackground(color);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setPreferredSize(new Dimension(80, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}
