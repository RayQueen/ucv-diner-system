package view;

import static view.TemplateView.*;

import models.RegisteredUser;
import models.Menu;
import models.Turn;

import javax.swing.*;
import java.awt.*;

public class FeedView extends JFrame {
    public JPanel topPanel;
    public JPanel menu1Panel;
    public JPanel menu2Panel;
    public JPanel leftPanel;
    public JPanel breakfastPanel;
    public JPanel lunchPanel;
    public JLabel welcomeLabel;
    public JLabel balanceLabel;
    public JLabel balanceValueLabel;
    public JButton addBalanceButton;
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
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 80));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Breakfast panel
        menu1Panel = new JPanel() {
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

        // Lunch panel
        menu2Panel = new JPanel() {
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

    public void updateUser(RegisteredUser usuario) {
        welcomeLabel.setText("BIENVENIDO/A " + usuario.getFullName());
        balanceValueLabel.setText("Bs. " + usuario.getBalance());
    }

    private JPanel getTurnPanel(Turn menu) {
        JPanel turnPanel = new JPanel(new BorderLayout());
        turnPanel.setBackground(SECONDARY_COLOR_LIGHT);
        turnPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(SECONDARY_COLOR_LIGHT);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JLabel turnLabel = templateLabel(menu.getTurn(), TITLE2, PRINCIPAL_COLOR, Component.CENTER_ALIGNMENT);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBackground(SECONDARY_COLOR_LIGHT);
        verticalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelSoup = templateLabel(menu.getSoup(), TITLE3, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);
        JLabel labelDry = templateLabel(menu.getDry(), TITLE3, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);
        JLabel labelDrink = templateLabel(menu.getDrink(), TITLE3, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);
        JLabel labelDessert = templateLabel(menu.getDessert(), TITLE3, PRINCIPAL_COLOR, Component.LEFT_ALIGNMENT);

        labelSoup.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDry.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDrink.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDessert.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(turnLabel);
        centerPanel.add(Box.createVerticalGlue());

        verticalPanel.add(labelSoup);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelDry);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelDrink);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(labelDessert);

        turnPanel.add(centerPanel, BorderLayout.NORTH);
        turnPanel.add(verticalPanel, BorderLayout.WEST);
        return turnPanel;
    }

    public void updateMenu(Menu menu) {
        this.menu1Panel.removeAll();
        this.menu1Panel.add(getTurnPanel(menu.getBreakfast()));
        this.menu2Panel.removeAll();
        this.menu2Panel.add(getTurnPanel(menu.getLunch()));
    }
}