package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;
import models.RegisteredUser;

public class FeedView extends JFrame {
    public JPanel topPanel;
    public JPanel menuPanel;
    public JPanel leftPanel;
    public JPanel breakfastPanel;
    public JPanel lunchPanel;
    public JLabel welcomeLabel;
    public JLabel balanceLabel;
    public JLabel balanceValueLabel;
    public JButton addBalanceButton;
    public JButton payLunchButton;
    public JButton payBreakfastButton;
    public JButton logOutButton;
    public JButton homeButton;

    public FeedView() {
        setTitle("Sabor Central UCV");
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
        
        // Left panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, -20, 100, 20));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Wallet label
        JPanel walletPanel = new JPanel() {
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
        walletPanel.setOpaque(false);
        walletPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        walletPanel.setMaximumSize(new Dimension(500, 350));
        walletPanel.setPreferredSize(new Dimension(500, 350));
        walletPanel.setLayout(new BoxLayout(walletPanel, BoxLayout.Y_AXIS));
        
        // Wallet label
        JLabel walletLabel = templateLabel("Monedero virtual", TITLE2, WHITE, Component.CENTER_ALIGNMENT);
        
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
        balanceLabel = templateLabel("Saldo actual", B_TEXT, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Balance value label
        balanceValueLabel = templateLabel("Bs. 0.00", TITLE1, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        // Balance button
        addBalanceButton = templateButton("Recargar saldo", B_TEXT, PRINCIPAL_COLOR, WHITE, 200, 45);

        // Menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 80));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pre-pay buttons
        payLunchButton = templateButton("Pre-pagar", B_TEXT, PRINCIPAL_COLOR, WHITE, 150);
        payBreakfastButton = templateButton("Pre-pagar", B_TEXT, PRINCIPAL_COLOR, WHITE, 150);

        // Breakfast panel
        JPanel menu1Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(SECONDARY_COLOR_LIGHT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
            }
        };
        menu1Panel.setOpaque(false);
        menu1Panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        menu1Panel.setMaximumSize(new Dimension(600, 225));
        menu1Panel.setPreferredSize(new Dimension(600, 225));
        menu1Panel.setLayout(new BoxLayout(menu1Panel, BoxLayout.Y_AXIS));
        menu1Panel.add(updateMenu(
            "Desayuno (7:00 AM - 11:00 AM)",
            "Sopa: Crema de auyama",
            "Seco: Pabellón criollo",
            "Jugo: Jugo de papelón con limón",
            "Postre: Manzana y durazno",
            payBreakfastButton
        ));

        // Lunch panel
        JPanel menu2Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(SECONDARY_COLOR_LIGHT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
            }
        };
        menu2Panel.setOpaque(false);
        menu2Panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        menu2Panel.setMaximumSize(new Dimension(600, 225));
        menu2Panel.setPreferredSize(new Dimension(600, 225));
        menu2Panel.setLayout(new BoxLayout(menu2Panel, BoxLayout.Y_AXIS));
        menu2Panel.add(updateMenu(
            "Almuerzo (12:00 PM - 5:00 PM)",
            "Sopa: Sopa de lentejas",
            "Seco: Pabellón criollo",
            "Jugo: Papelón con limón",
            "Postre: Quesillo",
            payLunchButton
        ));

        // Add components to menu panel
        menuPanel.add(menu1Panel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        menuPanel.add(menu2Panel);

        // Add components to wallet panel
        walletLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        balancePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBalanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        balancePanel.add(balanceLabel);
        balancePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        balancePanel.add(balanceValueLabel);

        walletPanel.setLayout(new BoxLayout(walletPanel, BoxLayout.Y_AXIS));
        walletPanel.add(walletLabel);
        walletPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        walletPanel.add(balancePanel);
        walletPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        walletPanel.add(addBalanceButton);

        leftPanel.add(walletPanel);

        // Add components to welcome panel
        welcomePanel.add(welcomeLabel);
        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);

        iconButtonPanel.add(homeButton);
        iconButtonPanel.add(logOutButton);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);
    }

    // Método para actualizar la pantalla según el usuario
    public void updateUser(RegisteredUser usuario) {
        welcomeLabel.setText("BIENVENIDO/A " + usuario.getFullName());
        balanceLabel.setText("Saldo actual: Bs. " + usuario.getBalance());
        addBalanceButton.setVisible(true);
        payLunchButton.setVisible(true);
    }

    private JPanel updateMenu(String turn, String soup, String dry, String juice, String dessert, JButton prePayButton) {
        JPanel turnPanel = new JPanel();
        turnPanel.setBackground(SECONDARY_COLOR_LIGHT);
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.Y_AXIS));
        turnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        turnPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JLabel turnLabel = templateLabel(turn, TITLE2, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);

        JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelsPanel.setBackground(SECONDARY_COLOR_LIGHT);

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBackground(SECONDARY_COLOR_LIGHT);

        JLabel labelSoup = templateLabel(soup, B_TEXT, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);
        JLabel labelDry = templateLabel(dry, B_TEXT, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);
        JLabel labelJuice = templateLabel(juice, B_TEXT, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);
        JLabel labelDessert = templateLabel(dessert, B_TEXT, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);

        labelSoup.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDry.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelJuice.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDessert.setAlignmentX(Component.LEFT_ALIGNMENT);

        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelSoup);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelDry);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelJuice);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelDessert);

        JPanel horizontalPanel = new JPanel(new GridLayout(1, 2));
        horizontalPanel.setBackground(SECONDARY_COLOR_LIGHT);

        // Label panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(SECONDARY_COLOR_LIGHT);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(verticalPanel);
        leftPanel.add(Box.createVerticalGlue());

        // Button panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(SECONDARY_COLOR_LIGHT);
        rightPanel.add(Box.createVerticalGlue());

        JPanel buttonFlowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonFlowPanel.setBackground(SECONDARY_COLOR_LIGHT);
        buttonFlowPanel.add(prePayButton);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(buttonFlowPanel);
        rightPanel.add(Box.createVerticalGlue());

        horizontalPanel.add(leftPanel);
        horizontalPanel.add(rightPanel);

        turnPanel.add(turnLabel);
        turnPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        turnPanel.add(horizontalPanel);

        return turnPanel;
    }
}