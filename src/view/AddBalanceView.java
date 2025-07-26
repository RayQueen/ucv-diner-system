package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;

public class AddBalanceView extends JFrame {
    public int bankField;
    public int phoneCode;
    public JTextField phoneField;
    public JTextField dateField;
    public JTextField digitsField;
    public JButton validateButton;
    public JButton cancelButton;
    public JLabel balanceValueLabel;

    public AddBalanceView() {
        setTitle("Agregar Saldo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // Background panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(SECONDARY_COLOR_DARK);

        //Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(SECONDARY_COLOR_DARK);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // Title label
        JLabel titleLabel = templateLabel("Recargar Saldo", TITLE1, WHITE, Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(SECONDARY_COLOR_DARK);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(SECONDARY_COLOR_DARK);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        // Form panel
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
            }
        };
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        formPanel.setMaximumSize(new Dimension(375, 600));
        formPanel.setPreferredSize(new Dimension(375, 600));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form label
        JLabel formLabel = templateLabel("Introduzca los datos del pago", B_TEXT, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);
        formLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bank label and selection
        JLabel bankLabel = templateLabel("Banco emisor", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        JComboBox<String> bankCombo = new JComboBox<>(new String[]{"Banco de Venezuela", "Banesco", "Mercantil", "Provincial", "Bancamiga", "Bancaribe", "Banco del Tesoro"});
        bankCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        bankCombo.setMaximumSize(new Dimension(320, 45));
        bankCombo.setPreferredSize(new Dimension(320, 45));
        bankCombo.addActionListener(e -> {
            bankField = bankCombo.getSelectedIndex();
        });

        // Phone panel
        JPanel phonePanel = new JPanel();
        phonePanel.setBackground(Color.WHITE);
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.X_AXIS));

        // Phone label
        JLabel phoneLabel = templateLabel("Teléfono", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        
        // Phone code selection
        JComboBox<String> phoneCodeCombo = new JComboBox<>(new String[]{"0412", "0414", "0416", "0422", "0424", "0426"});
        phoneCodeCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        phoneCodeCombo.setMaximumSize(new Dimension(100, 45));
        phoneCodeCombo.setPreferredSize(new Dimension(100, 45));
        phoneCodeCombo.addActionListener(e -> {
            phoneCode = phoneCodeCombo.getSelectedIndex();
        });
        
        // Phone field
        phoneField = templateNumericTextField();
        phoneField.setMaximumSize(new Dimension(220, 45));
        phoneField.setPreferredSize(new Dimension(220, 45));
        phoneField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date label and field
        JLabel dateLabel = templateLabel("Fecha de Pago", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        JFormattedTextField formattedDateField;
        try {
            javax.swing.text.MaskFormatter dateMask = new javax.swing.text.MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter(' ');
            formattedDateField = new JFormattedTextField(dateMask);
            formattedDateField.setMaximumSize(new Dimension(220, 45));
            formattedDateField.setPreferredSize(new Dimension(220, 45));
            formattedDateField.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch (java.text.ParseException e) {
            formattedDateField = new JFormattedTextField();
        }
        dateField = formattedDateField;

        // Digits label and field
        JLabel digitsLabel = templateLabel("Últimos Dígitos de la Cuenta", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        digitsField = templateNumericTextField();
        digitsField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Validate button
        validateButton = templateButton("Validar", B_TEXT, PRINCIPAL_COLOR, WHITE, 150, 45);
        
        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, PRINCIPAL_COLOR, 150, 45);

        // Payment info panel
        JPanel paymentInfoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
            }
        };
        paymentInfoPanel.setOpaque(false);
        paymentInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        paymentInfoPanel.setMaximumSize(new Dimension(250, 150));
        paymentInfoPanel.setPreferredSize(new Dimension(250, 150));
        paymentInfoPanel.setLayout(new BoxLayout(paymentInfoPanel, BoxLayout.Y_AXIS));

        // Info labels
        JLabel infoTitle = templateLabel("Realiza tu pago móvil a:", B_TEXT, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Subinfo panel
        JPanel subInfoPanel = new JPanel();
        subInfoPanel.setBackground(WHITE);
        subInfoPanel.setLayout(new BoxLayout(subInfoPanel, BoxLayout.X_AXIS));

        // Type panel
        JPanel typePanel = new JPanel();
        typePanel.setBackground(WHITE);
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
        JLabel typeBankLabel = templateLabel("Banco:", B_TEXT, BLACK, Component.LEFT_ALIGNMENT);
        JLabel typeIDLabel = templateLabel("C.I.:", B_TEXT, BLACK, Component.LEFT_ALIGNMENT);
        JLabel typePhoneLabel = templateLabel("Teléfono:", B_TEXT, BLACK, Component.LEFT_ALIGNMENT);

        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel contentBankLabel = templateLabel("Banesco", B_TEXT, BLACK, Component.LEFT_ALIGNMENT);
        JLabel contentIDLabel = templateLabel("12.345.678", B_TEXT, BLACK, Component.LEFT_ALIGNMENT);
        JLabel contentPhoneLabel = templateLabel("0414-0123456", B_TEXT, BLACK, Component.LEFT_ALIGNMENT);

        // Balance panel
        JPanel balancePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
            }
        };
        balancePanel.setOpaque(false);
        balancePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        balancePanel.setMaximumSize(new Dimension(250, 150));
        balancePanel.setPreferredSize(new Dimension(250, 150));
        balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));

        // Balance label
        JLabel balanceLabel = templateLabel("Saldo actual", B_TEXT, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Balance value label
        balanceValueLabel = templateLabel("Bs. 0.00", TITLE1, BLACK, Component.CENTER_ALIGNMENT);

        // Add components to phone panel
        phonePanel.add(phoneCodeCombo);
        phonePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        phonePanel.add(phoneField);

        // Add buttons to button panel
        buttonPanel.add(validateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);

        // Add components to the form panel
        formPanel.add(formLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(bankLabel);
        formPanel.add(bankCombo);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(phoneLabel);
        formPanel.add(phonePanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(dateLabel);
        formPanel.add(dateField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(digitsLabel);
        formPanel.add(digitsField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(buttonPanel);

        // Add components to the payment info panel
        contentPanel.add(contentBankLabel);
        contentPanel.add(contentIDLabel);
        contentPanel.add(contentPhoneLabel);
        typePanel.add(typeBankLabel);
        typePanel.add(typeIDLabel);
        typePanel.add(typePhoneLabel);
        subInfoPanel.add(typePanel);
        subInfoPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        subInfoPanel.add(contentPanel);
        paymentInfoPanel.add(infoTitle);
        paymentInfoPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        paymentInfoPanel.add(subInfoPanel);

        // Add components to the balance panel
        balancePanel.add(balanceLabel);
        balancePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        balancePanel.add(balanceValueLabel);

        //Add components to left panel
        leftPanel.add(balancePanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(paymentInfoPanel);

        // Add components to right panel
        rightPanel.add(formPanel);

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
    }
}

