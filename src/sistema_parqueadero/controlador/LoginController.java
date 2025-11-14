package controlador;

import modelo.Usuario;
import modelo.UsuarioDao;
import modelo.Globales;
import vista.Login;
import vista.Principal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private final Login vista;
    private final UsuarioDao usuarioDao;

    public LoginController(Login vista) {
        this.vista = vista;
        this.usuarioDao = new UsuarioDao();

        // Asignar acción al botón
        this.vista.getBtnLogin().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnLogin()) {
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        String cedula = vista.getTxtDocumento().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword()).trim();

        if (cedula.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Por favor ingrese su documento y contraseña.",
                    "Campos vacíos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDao.iniciarSesion(cedula, contrasena);

        if (usuario != null) {
            JOptionPane.showMessageDialog(vista,
                    "Bienvenido, " + usuario.getNombre() + " (" + usuario.getRol() + ")");

            // 🔹 Guardar variables globales
            Globales.global_id_usuario = usuario.getId();
            Globales.global_nombre_usuario = usuario.getNombre() + " " + usuario.getApellido();
            Globales.global_rol_usuario = usuario.getRol();

            vista.dispose(); // Cierra la ventana de login

            // 🔹 Abrir la ventana principal e inicializar su controlador
            SwingUtilities.invokeLater(() -> {
                Principal principal = new Principal();
                new PrincipalController(principal); // 💡 importante: inicializa el controlador
                principal.setVisible(true);
            });

        } else {
            JOptionPane.showMessageDialog(vista,
                    "Documento o contraseña incorrectos o usuario inactivo.",
                    "Error de inicio de sesión",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
