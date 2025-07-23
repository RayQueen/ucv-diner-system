package view;

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
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(70, 110, 150));
        mainPanel.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel firstNameLabel = new JLabel("Nombre");
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        firstNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setMaximumSize(new Dimension(320, 28));
        firstNameField.setPreferredSize(new Dimension(320, 28));
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        firstNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        firstNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(firstNameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lastNameLabel = new JLabel("Apellido");
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lastNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setMaximumSize(new Dimension(320, 28));
        lastNameField.setPreferredSize(new Dimension(320, 28));
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        lastNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        lastNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(lastNameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setFont(new Font("Arial", Font.BOLD, 13));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(userLabel);
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(320, 28));
        usernameField.setPreferredSize(new Dimension(320, 28));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel emailLabel = new JLabel("Correo");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 13));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(320, 28));
        emailField.setPreferredSize(new Dimension(320, 28));
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(320, 28));
        passwordField.setPreferredSize(new Dimension(320, 28));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 13));
        registerButton.setBackground(new Color(70, 110, 150));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.setMaximumSize(new Dimension(150, 40));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 13));
        cancelButton.setBackground(new Color(220, 230, 245));
        cancelButton.setForeground(new Color(70, 110, 150));
        cancelButton.setFocusPainted(false);
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setMaximumSize(new Dimension(150, 40));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttonPanel.add(cancelButton);

        formPanel.add(buttonPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(formPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}
