package vista;

import javax.swing.*;
import java.awt.*;

public class NavbarPanel extends JPanel {

    public NavbarPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(25, 82, 240));
        setPreferredSize(new Dimension(900, 50));

        // panel izquierdo (nombre de app y tipo de usuario)
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        leftPanel.setOpaque(false);

        JLabel lblTitulo = new JLabel("ParqueAPP");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblCliente = new JLabel("Administrador");
        lblCliente.setForeground(Color.WHITE);
        lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
        lblCliente.setOpaque(true);
        lblCliente.setBackground(new Color(80, 130, 255));
        lblCliente.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        leftPanel.add(lblTitulo);
        leftPanel.add(lblCliente);
        add(leftPanel, BorderLayout.WEST);

        // panel derecho (icono, usuario, boton cerrar)
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        rightPanel.setOpaque(false);

        JLabel lblCampana = new JLabel();
        lblCampana.setPreferredSize(new Dimension(24, 24));
        lblCampana.setIcon(new ImageIcon("src/img/ring.png"));

        JLabel lblUsuario = new JLabel("Administrador: DEMO");
        lblUsuario.setForeground(Color.WHITE);

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCerrar.setBackground(new Color(25, 82, 240));
        btnCerrar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(15, 65, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(25, 82, 240));
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int confirm = JOptionPane.showConfirmDialog(parentFrame,
                        "¿Desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    parentFrame.dispose();
                   
                }
            }
        });

        rightPanel.add(lblCampana);
        rightPanel.add(lblUsuario);
        rightPanel.add(btnCerrar);
        add(rightPanel, BorderLayout.EAST);
    }
}
