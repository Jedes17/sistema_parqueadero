package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Globales;

public class Login extends JFrame {

    private JTextField txtDocumento;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    public Login() {
        setTitle("PARQUEAPP - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel lblTitulo = new JLabel("PARQUEAPP", SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(51, 102, 255));

        JLabel lblIcono = new JLabel(new ImageIcon("src/img/logo_3.jpg"));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblIcono.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JLabel lblDocumento = new JLabel("Documento");
        lblDocumento.setAlignmentX(CENTER_ALIGNMENT);
        txtDocumento = new JTextField();
        txtDocumento.setMaximumSize(new Dimension(250, 35));

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setAlignmentX(CENTER_ALIGNMENT);
        txtContrasena = new JPasswordField();
        txtContrasena.setMaximumSize(new Dimension(250, 35));

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBackground(new Color(51, 102, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(180, 40));

        JLabel lblRegistro = new JLabel("¿NO TIENES CUENTA?");
        lblRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel linkRegistro = new JLabel("Registrarte");
        linkRegistro.setForeground(new Color(51, 102, 255));
        linkRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(lblIcono);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(lblDocumento);
        panelPrincipal.add(txtDocumento);
        panelPrincipal.add(Box.createVerticalStrut(35));
        panelPrincipal.add(lblContrasena);
        panelPrincipal.add(txtContrasena);
        panelPrincipal.add(Box.createVerticalStrut(55));
        panelPrincipal.add(btnLogin);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(lblRegistro);
        panelPrincipal.add(linkRegistro);

        add(panelPrincipal);
    }

    // getters para el controlador
    public JTextField getTxtDocumento() {
        return txtDocumento;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public static void main(String[] args) {
        Login vista = new Login();
        controlador.LoginController controlador = new controlador.LoginController(vista);
        vista.setVisible(true);
    }
}
