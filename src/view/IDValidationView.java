package view;

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
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(70, 110, 150));
        mainPanel.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel IDLabel = new JLabel("Cédula");
        IDLabel.setFont(new Font("Arial", Font.BOLD, 13));
        IDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(IDLabel);
        
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Long.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0L);

        IDField = new JFormattedTextField(formatter);
        IDField.setMaximumSize(new Dimension(320, 45));
        IDField.setPreferredSize(new Dimension(320, 45));
        IDField.setFont(new Font("Arial", Font.PLAIN, 16));
        IDField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        IDField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(IDField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel messageLabel = new JLabel("Ingrese su cédula para validar su identidad");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        messageLabel.setForeground(new Color(70, 110, 150));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(messageLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sendButton = new JButton("Registrarse");
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
