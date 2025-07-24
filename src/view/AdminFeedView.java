package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;

public class AdminFeedView extends JFrame {
    public JPanel topPanel;
    public JPanel contentPanel;
    public JLabel welcomeLabel;
    public JButton menuManagementButton;
    public JButton calculateCCBButton;
    public JButton setPricingButton;
    public JButton submitConsumptionButton;
    public JButton generateReportsButton;
    public JButton logOutButton;
    public JButton homeButton;

    public AdminFeedView() {
        setTitle("Sabor Central UCV - Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(WHITE);

        // Background panel
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        topPanel.setBackground(PRINCIPAL_COLOR);

        // Welcome label
        welcomeLabel = templateLabel("BIENVENIDO", TITLE1, WHITE, Component.LEFT_ALIGNMENT);
        
        // Welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(PRINCIPAL_COLOR);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        // Icon panel for home and logout buttons
        JPanel iconButtonPanel = new JPanel();
        iconButtonPanel.setBackground(PRINCIPAL_COLOR);
        iconButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        // Load icons for home and logout buttons
        ImageIcon homeIcon = null;
        ImageIcon logoutIcon = null;
        try {
            java.net.URL homeUrl = getClass().getResource("/view/assets/home.png");
            java.net.URL logoutUrl = getClass().getResource("/view/assets/logout.png");
            if (homeUrl != null) {
                Image img = new ImageIcon(homeUrl).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                homeIcon = new ImageIcon(img);
            } else {
                System.err.println("No se encontró el icono: /view/assets/home.png");
                homeIcon = new ImageIcon();
            }
            if (logoutUrl != null) {
                Image img = new ImageIcon(logoutUrl).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                logoutIcon = new ImageIcon(img);
            } else {
                System.err.println("No se encontró el icono: /view/assets/logout.png");
                logoutIcon = new ImageIcon();
            }
        } catch (Exception e) {
            System.err.println("Error cargando iconos: " + e.getMessage());
            homeIcon = new ImageIcon();
            logoutIcon = new ImageIcon();
        }

        // Home button
        homeButton = templateButton(homeIcon, PRINCIPAL_COLOR, null, 48, 48);

        // Logout button
        logOutButton = templateButton(logoutIcon, PRINCIPAL_COLOR, null, 48, 48);
        
        // Right panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 120));

        // CCB button
        calculateCCBButton = templateButton("Calcular CCB", TITLE2, PRINCIPAL_COLOR, WHITE, 300, 60);
        
        // Pricing rates button
        setPricingButton = templateButton("Establecer Tarifas", TITLE2, PRINCIPAL_COLOR, WHITE, 300, 60);
        
        // Menu management button
        menuManagementButton = templateButton("Gestionar Menú", TITLE2, PRINCIPAL_COLOR, WHITE, 300, 60);
        
        // Consumption submission button
        submitConsumptionButton = templateButton("Registrar Consumo", TITLE2, PRINCIPAL_COLOR, WHITE, 300, 60);
        
        // Reports generation button
        generateReportsButton = templateButton("Generar Reportes", TITLE2, PRINCIPAL_COLOR, WHITE, 300, 60);
        
        // Left panel for data display
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 120, 20, 20));
        
        // Data panel for data display
        JPanel dataPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(SECONDARY_COLOR_MEDIUM);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
            }
        };
        dataPanel.setOpaque(false);
        dataPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        dataPanel.setMaximumSize(new Dimension(600, 425));
        dataPanel.setPreferredSize(new Dimension(600, 425));
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        
        // Add components to welcome panel
        welcomePanel.add(welcomeLabel);
        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);
        iconButtonPanel.add(homeButton);
        iconButtonPanel.add(logOutButton);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);

        // Add buttons to right panel
        buttonPanel.add(calculateCCBButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(setPricingButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(menuManagementButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(submitConsumptionButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(generateReportsButton);

        // Add components to left panel
        leftPanel.add(dataPanel);
        

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);
    }

    public void updateUser(String nombreCompleto) {
        welcomeLabel.setText("BIENVENIDO/A " + nombreCompleto);
    }
}
