package view;

import static view.TemplateView.*;

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
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;

        // Form panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        // Title
        JLabel titleLabel = templateLabel("INICIAR SESION", TITLE1, SECONDARY_COLOR_DARK, Component.CENTER_ALIGNMENT);

        // User label
        JLabel userLabel = templateLabel("Usuario", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        //User field
        userField = templateTextField();

        // Password label
        JLabel passwordLabel = templateLabel("Contraseña", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // Password field
        passwordField = templatePasswordField();

        // Forgot password label
        forgotPassword = templateLabel("Olvidé mi contraseña", TEXT, SECONDARY_COLOR_DARK, Component.LEFT_ALIGNMENT);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Log In button
        logInButton = templateButton("Iniciar sesión", B_TEXT, SECONDARY_COLOR_DARK, WHITE);
        logInButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register button
        registerButton = templateButton("Registrarse", B_TEXT, SECONDARY_COLOR_LIGHT, PRINCIPAL_COLOR);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to left panel
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        leftPanel.add(userLabel);
        leftPanel.add(userField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 14)));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(forgotPassword);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(logInButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        leftPanel.add(registerButton);

        gbc.gridx = 0;
        mainPanel.add(leftPanel, gbc);

        // Logo panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        rightPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        // Load and scale logo
        ImageIcon originalIcon = new ImageIcon("src/view/assets/logoWhite.png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Cambia el tamaño aquí
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to right panel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(logoLabel);
        rightPanel.add(Box.createVerticalGlue());

        gbc.gridx = 1;
        mainPanel.add(rightPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}
