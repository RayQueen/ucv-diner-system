package view;
import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import controllers.IniciarSesionController;

public class IniciarSesionView extends JFrame {
    public JTextField usuario;
    public JPasswordField contrasena;
    public JButton boton1;
public JButton register;
    public JLabel forgotPassword;
    public IniciarSesionController controller;

    public IniciarSesionView() {
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(new Color(70, 110, 150));

        // Panel izquierdo (formulario)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("INICIAR SESIÓN");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(70, 110, 150));
        leftPanel.add(titulo);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Usuario
        JLabel usuarioLabel = new JLabel("Usuario");
        usuarioLabel.setFont(new Font("Serif", Font.BOLD, 13));
        usuarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(usuarioLabel);
        usuario = new JTextField();
        usuario.setMaximumSize(new Dimension(800, 45));
        usuario.setPreferredSize(new Dimension(800, 45));
        usuario.setFont(new Font("Serif", Font.PLAIN, 16));
        usuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        usuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(usuario);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 14)));

        // Contraseña
        JLabel contrasenaLabel = new JLabel("Contraseña");
        contrasenaLabel.setFont(new Font("Serif", Font.BOLD, 13));
        contrasenaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(contrasenaLabel);
        contrasena = new JPasswordField();
        contrasena.setMaximumSize(new Dimension(800, 45));
        contrasena.setPreferredSize(new Dimension(800, 45));
        contrasena.setFont(new Font("Serif", Font.PLAIN, 16));
        contrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(contrasena);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Olvidé mi contraseña
        JPanel opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new BoxLayout(opcionesPanel, BoxLayout.X_AXIS));
        opcionesPanel.setBackground(Color.WHITE);
        opcionesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPassword = new JLabel("Olvidé mi contraseña");
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 11));
        forgotPassword.setForeground(new Color(70, 110, 150));
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        opcionesPanel.add(Box.createHorizontalGlue());
        opcionesPanel.add(forgotPassword);
        leftPanel.add(opcionesPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Botón Iniciar Sesión
        boton1 = new JButton("Iniciar sesión");
        boton1.setFont(new Font("Arial", Font.BOLD, 13));
        boton1.setBackground(new Color(70, 110, 150));
        boton1.setForeground(Color.WHITE);
        boton1.setFocusPainted(false);
        boton1.setPreferredSize(new Dimension(800, 40));
        boton1.setMaximumSize(new Dimension(800, 40));
        boton1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(boton1);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Botón Registrarse debajo con colores claros
        register = new JButton("Registrarse");
        register.setFont(new Font("Arial", Font.BOLD, 13));
        register.setBackground(new Color(220, 230, 245));
        register.setForeground(new Color(70, 110, 150));
        register.setFocusPainted(false);
        register.setPreferredSize(new Dimension(800, 40));
        register.setMaximumSize(new Dimension(800, 40));
        register.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(register);

        mainPanel.add(leftPanel);

        // Panel derecho (logo y texto)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(100, 140, 180));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        rightPanel.add(Box.createVerticalGlue());
        JLabel logoLabel = new JLabel("Universidad Central Caracas - Venezuela");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 13));
        logoLabel.setForeground(new Color(40, 70, 110));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(logoLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel saborLabel = new JLabel("Sabor Central UCV");
        saborLabel.setFont(new Font("Arial", Font.BOLD, 12));
        saborLabel.setForeground(new Color(40, 70, 110));
        saborLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(saborLabel);

        JLabel lemaLabel = new JLabel("Nutriendo mentes, deleitando paladares.");
        lemaLabel.setFont(new Font("Serif", Font.PLAIN, 11));
        lemaLabel.setForeground(new Color(40, 70, 110));
        lemaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(lemaLabel);
        rightPanel.add(Box.createVerticalGlue());

        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

    }
}
