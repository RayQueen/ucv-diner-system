package view;

import static view.TemplateView.*;

import models.Pricing;
import models.RegisteredUser;

import javax.swing.*;
import java.awt.*;

public class PayView extends JFrame {
    public JTextField imagePathField;
    public JButton payButton;
    public JButton cancelButton;
    public JLabel balanceValueLabel;
    public JLabel priceValueLabel;

    public PayView() {
        setTitle("Pagar");
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
        JLabel titleLabel = templateLabel("Pagar turno", TITLE1, WHITE, Component.CENTER_ALIGNMENT);
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
        JLabel formLabel = templateLabel("Ingrese aquí su foto", TITLE3, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);
        formLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Upload image button
        JButton uploadImageButton = templateButton("Subir imagen", B_TEXT, PRINCIPAL_COLOR, WHITE);
        uploadImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePathField = new JTextField(30);
        imagePathField.setEditable(false);

        uploadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes PNG y JPG", "png", "jpg"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        // Image info label
        JLabel imageInfoLabel = templateLabel("La imagen debe ser de tipo PNG o JPG", B_TEXT, SECONDARY_COLOR_DARK, Component.CENTER_ALIGNMENT);
        imageInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Pay button
        payButton = templateButton("Pagar", B_TEXT, PRINCIPAL_COLOR, WHITE, 150, 45);
        
        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, PRINCIPAL_COLOR, 150, 45);

        // Price panel
        JPanel pricePanel = new JPanel() {
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
        pricePanel.setOpaque(false);
        pricePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pricePanel.setMaximumSize(new Dimension(250, 150));
        pricePanel.setPreferredSize(new Dimension(250, 150));
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));

        // Price label
        JLabel priceLabel = templateLabel("Precio del turno", B_TEXT, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Price value label
        priceValueLabel = templateLabel("Bs. 0.00", TITLE1, BLACK, Component.CENTER_ALIGNMENT);

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

        // Add buttons to button panel
        buttonPanel.add(payButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);

        // Add components to the form panel
        formPanel.add(formLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(imagePathField);
        formPanel.add(uploadImageButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(imageInfoLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(buttonPanel);

        // Add components to the payment info panel
        pricePanel.add(priceLabel);
        pricePanel.add(Box.createRigidArea(new Dimension(0, 25)));
        pricePanel.add(priceValueLabel);

        // Add components to the price panel
        balancePanel.add(balanceLabel);
        balancePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        balancePanel.add(balanceValueLabel);

        //Add components to left panel
        leftPanel.add(balancePanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(pricePanel);

        // Add components to right panel
        rightPanel.add(formPanel);

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void updateBalance(RegisteredUser usuario) {
        balanceValueLabel.setText("Bs. " + usuario.getBalance());
    }

    public void updatePrice(RegisteredUser usuario, Pricing price) {
        int userType = usuario.getUserType();
        priceValueLabel.setText("Bs. " + price.getPricing(userType));
    }
}
