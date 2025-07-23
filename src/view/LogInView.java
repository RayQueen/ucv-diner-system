package view;

import controllers.LogInController;

import javax.swing.*;
import java.awt.*;

public class LogInView extends JFrame {
    public JTextField userField;
    public JPasswordField passwordField;
    public JButton logInButton;
    public JButton registerButton;
    public JLabel forgotPassword;
    public LogInController logInController;

    public LogInView() {
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);

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
        usuarioLabel.setFont(new Font("Arial", Font.BOLD, 13));
        usuarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(usuarioLabel);
        userField = new JTextField();
        userField.setMaximumSize(new Dimension(800, 45));
        userField.setPreferredSize(new Dimension(800, 45));
        userField.setFont(new Font("Arial", Font.PLAIN, 16));
        userField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(userField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 14)));

        // Contraseña
        JLabel contrasenaLabel = new JLabel("Contraseña");
        contrasenaLabel.setFont(new Font("Arial", Font.BOLD, 13));
        contrasenaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(contrasenaLabel);
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(800, 45));
        passwordField.setPreferredSize(new Dimension(800, 45));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(passwordField);
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
        logInButton = new JButton("Iniciar sesión");
        logInButton.setFont(new Font("Arial", Font.BOLD, 13));
        logInButton.setBackground(new Color(70, 110, 150));
        logInButton.setForeground(Color.WHITE);
        logInButton.setFocusPainted(false);
        logInButton.setPreferredSize(new Dimension(800, 40));
        logInButton.setMaximumSize(new Dimension(800, 40));
        logInButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        logInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(logInButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        // Botón Registrarse debajo con colores claros
        registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 13));
        registerButton.setBackground(new Color(220, 230, 245));
        registerButton.setForeground(new Color(70, 110, 150));
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(800, 40));
        registerButton.setMaximumSize(new Dimension(800, 40));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(registerButton);

        mainPanel.add(leftPanel);

        // Panel derecho (logo y texto)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(100, 140, 180));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        rightPanel.add(Box.createVerticalGlue());
        JLabel logoLabel = new JLabel("Universidad Central Caracas - Venezuela");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 13));
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
        lemaLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        lemaLabel.setForeground(new Color(40, 70, 110));
        lemaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(lemaLabel);
        rightPanel.add(Box.createVerticalGlue());

        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

    }
}
