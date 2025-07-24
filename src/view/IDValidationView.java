package view;

import static view.TemplateView.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.text.NumberFormat;

public class IDValidationView extends JFrame {
    public JTextField IDField;
    public JButton sendButton;
    public JButton cancelButton;

    public IDValidationView() {
        setTitle("Validar CI");
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

        // ID label
        JLabel IDLabel = templateLabel("Cédula", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        
        // ID field with NumberFormatter for only numbers
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Long.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0L);

        IDField = new JFormattedTextField(formatter);
        IDField.setMaximumSize(new Dimension(320, 45));
        IDField.setPreferredSize(new Dimension(320, 45));
        IDField.setFont(TEXT);
        IDField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        IDField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Message label
        JLabel messageLabel = templateLabel("Ingrese su cédula para validar su identidad", TEXT, BLACK, Component.CENTER_ALIGNMENT);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Send button
        sendButton = templateButton("Registrarse", B_TEXT, SECONDARY_COLOR_DARK, WHITE, 150);
        
        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, SECONDARY_COLOR_DARK, 150);
        
        // Add buttons to button panel
        buttonPanel.add(sendButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);

        // Add components to form panel
        formPanel.add(IDLabel);
        formPanel.add(IDField);
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
