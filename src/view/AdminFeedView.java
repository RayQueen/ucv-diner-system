package view;

import static view.TemplateView.*;

import models.Pricing;

import javax.swing.*;
import java.awt.*;

public class AdminFeedView extends JFrame {
    public JPanel topPanel;
    public JPanel contentPanel;
    public JLabel welcomeLabel;
    public JLabel fixedCostLabel;
    public JLabel variableCostLabel;
    public JLabel plateNumberLabel;
    public JLabel shrinkageLabel;
    public JLabel ccbLabel;
    public JLabel studentRateLabel;
    public JLabel teacherRateLabel;
    public JLabel employeeRateLabel;
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
        setResizable(false);
        
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
        dataPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 30));
        dataPanel.setMaximumSize(new Dimension(600, 425));
        dataPanel.setPreferredSize(new Dimension(600, 425));
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

        // Data title label
        JLabel dataTitleLabel = templateLabel("Datos actuales", TITLE1, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Costs panel
        JPanel costsPanel = new JPanel();
        costsPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        costsPanel.setLayout(new BoxLayout(costsPanel, BoxLayout.X_AXIS));

        // Costs left panel
        JPanel leftCostPanel = new JPanel();
        leftCostPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        leftCostPanel.setLayout(new BoxLayout(leftCostPanel, BoxLayout.Y_AXIS));

        // Costs left panel
        JPanel rightCostPanel = new JPanel();
        rightCostPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        rightCostPanel.setLayout(new BoxLayout(rightCostPanel, BoxLayout.Y_AXIS));

        // Fixed Cost label
        fixedCostLabel = templateLabel("Costo Fijo: ", TITLE3, WHITE, Component.LEFT_ALIGNMENT);

        // Variable Cost label
        variableCostLabel = templateLabel("Costo Variable: ", TITLE3, WHITE, Component.LEFT_ALIGNMENT);

        // Plate Number label
        plateNumberLabel = templateLabel("Número de Bandejas: ", TITLE3, WHITE, Component.LEFT_ALIGNMENT);

        // Shrinkage label
        shrinkageLabel = templateLabel("Merma (%): ", TITLE3, WHITE, Component.LEFT_ALIGNMENT);

        // CCB label
        ccbLabel = templateLabel("CCB: ", TITLE2, WHITE, Component.CENTER_ALIGNMENT);

        // Rates label
        JLabel ratesLabel = templateLabel("Tarifas", TITLE1, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Rates panel
        JPanel ratesPanel = new JPanel();
        ratesPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        ratesPanel.setLayout(new BoxLayout(ratesPanel, BoxLayout.X_AXIS));

        // Student rate label
        studentRateLabel = templateLabel("Estudiante: ", TITLE3, WHITE, Component.LEFT_ALIGNMENT);

        // Teacher rate label
        teacherRateLabel = templateLabel("Profesor: ", TITLE3, WHITE, Component.CENTER_ALIGNMENT);

        // Employee rate label
        employeeRateLabel = templateLabel("Empleado: ", TITLE3, WHITE, Component.RIGHT_ALIGNMENT);

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

        // Add components to costs panel
        leftCostPanel.add(fixedCostLabel);
        leftCostPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftCostPanel.add(variableCostLabel);
        rightCostPanel.add(plateNumberLabel);
        rightCostPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightCostPanel.add(shrinkageLabel);
        costsPanel.add(leftCostPanel, BorderLayout.WEST);
        costsPanel.add(Box.createRigidArea(new Dimension(80, 0)));
        costsPanel.add(rightCostPanel, BorderLayout.EAST);

        // Add components to rates panel
        ratesPanel.add(studentRateLabel);
        ratesPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        ratesPanel.add(teacherRateLabel);
        ratesPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        ratesPanel.add(employeeRateLabel);

        // Add components to data panel
        dataPanel.add(dataTitleLabel);
        dataPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dataPanel.add(costsPanel);
        dataPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dataPanel.add(ccbLabel);
        dataPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        dataPanel.add(ratesLabel);
        dataPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dataPanel.add(ratesPanel);

        // Add components to left panel
        leftPanel.add(dataPanel);
        

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);
    }

    public void updateUser(String nombreCompleto) {
        welcomeLabel.setText("BIENVENIDO/A " + nombreCompleto);
    }

    public void updateCosts(Pricing pricingModel) {
        pricingModel.loadCosts();
        fixedCostLabel.setText("Costo Fijo: " + pricingModel.getFixedCost());
        variableCostLabel.setText("Costo Variable: " + pricingModel.getVariableCost());
        plateNumberLabel.setText("Número de Bandejas: " + pricingModel.getPlateNumber());
        shrinkageLabel.setText("Merma: " + pricingModel.getShrinkage() + "%");
        ccbLabel.setText("CCB: " + pricingModel.getCCB());
        studentRateLabel.setText("Estudiante: " + pricingModel.getRate(0) + "%");
        teacherRateLabel.setText("Profesor: " + pricingModel.getRate(1) + "%");
        employeeRateLabel.setText("Empleado: " + pricingModel.getRate(2) + "%");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminFeedView adminFeedView = new AdminFeedView();
            adminFeedView.setVisible(true);
            // Example usage with dummy data
            models.RegisteredUser user = new models.RegisteredUser("John", "Doe", "johndoe", 0.0, 3, "johndoe@example.com");
            adminFeedView.updateUser(user.getFullName());
            adminFeedView.updateCosts(new Pricing());
        });
    }
}
