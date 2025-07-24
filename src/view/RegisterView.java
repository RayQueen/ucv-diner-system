package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    public JTextField firstNameField;
    public JTextField lastNameField;
    public JTextField usernameField;
    public JTextField emailField;
    public JPasswordField passwordField;
    public JButton registerButton;
    public JButton cancelButton;

    public RegisterView() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // Background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        mainPanel.setLayout(new GridBagLayout());

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(80, 75, 80, 75));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // First Name label
        JLabel firstNameLabel = templateLabel("Nombre", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // First Name field
        firstNameField = templateTextField();
        firstNameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Last Name label
        JLabel lastNameLabel = templateLabel("Apellido", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // Last Name field
        lastNameField = templateTextField();
        lastNameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username label
        JLabel userLabel = templateLabel("Usuario", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // Username field
        usernameField = templateTextField();
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email label
        JLabel emailLabel = templateLabel("Correo", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // Email field
        emailField = templateTextField();
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Password label
        JLabel passwordLabel = templateLabel("Contraseña", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // Password field
        passwordField = templatePasswordField();
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register button
        registerButton = templateButton("Registrarse", B_TEXT, SECONDARY_COLOR_DARK, WHITE, 140);

        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, SECONDARY_COLOR_DARK, 140);

        // Add buttons to button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(cancelButton);

        // Add components to form panel
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(userLabel);
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(buttonPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(formPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}
