package view;

import javax.swing.*;
import java.awt.*;

public class CredentialRecoveryView extends JFrame {
    public JTextField emailField;
    public JButton sendButton;
    public JButton cancelButton;

    public CredentialRecoveryView() {
        setTitle("Recuperar Credenciales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
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

        JLabel emailLabel = new JLabel("Correo");
        emailLabel.setFont(new Font("Serif", Font.BOLD, 13));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(emailLabel);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(320, 45));
        emailField.setPreferredSize(new Dimension(320, 45));
        emailField.setFont(new Font("Serif", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel messageLabel = new JLabel("Recibirás un correo para reestablecer tu contraseña");
        messageLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        messageLabel.setForeground(new Color(70, 110, 150));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(messageLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sendButton = new JButton("Recuperar");
        sendButton.setFont(new Font("Arial", Font.BOLD, 13));
        sendButton.setBackground(new Color(70, 110, 150));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(150, 40));
        sendButton.setMaximumSize(new Dimension(150, 40));
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttonPanel.add(sendButton);
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
