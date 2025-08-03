package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;

public class UserValidationView extends JFrame{
    public JTextField userField;
    public JButton sendButton;
    public JButton cancelButton;

    public UserValidationView() {
        setTitle("Validar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(SECONDARY_COLOR_DARK);
        mainPanel.setLayout(new GridBagLayout());

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // User label
        JLabel userLabel = templateLabel("Usuario", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // User field
        userField = templateTextField();
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Message label
        JLabel messageLabel = templateLabel("Ingrese su usuario para validar su identidad", TEXT, BLACK, Component.CENTER_ALIGNMENT);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Send button
        sendButton = templateButton("Continuar", B_TEXT, SECONDARY_COLOR_DARK, WHITE, 150);
        
        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, SECONDARY_COLOR_DARK, 150);
        
        // Add buttons to button panel
        buttonPanel.add(sendButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);

        // Add components to form panel
        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(messageLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        formPanel.add(buttonPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(formPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}
