package view;

import javax.swing.*;
import java.awt.*;

import models.RegisteredUser;

public class FeedView extends JFrame {
    public JPanel sidePanel;
    public JPanel topPanel;
    public JPanel contentPanel;
    public JButton menuButton;
    public JLabel welcomeLabel;
    public JPanel walletPanel;
    public JLabel balanceLabel;
    public JButton movementsButton;
    public JLabel titleLabel;
    public JLabel subtitleLabel;
    public JButton addBalanceButton;
    public JButton consultButton;
    public JButton prePayButton;

    // Paleta de colores basada en la imagen
    private final Color azulOscuro = new Color(32, 61, 112);
    private final Color blanco = Color.WHITE;
    private final Color grisClaro = new Color(240, 240, 240);

    public FeedView() {
        setTitle("Sabor Central UCV");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(grisClaro);

        // Panel superior
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        topPanel.setBackground(azulOscuro);

        welcomeLabel = new JLabel("BIENVENIDO");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(blanco);

        // Icono usuario (puedes cambiar por un icono real si lo tienes)
        // JLabel iconoUsuario = new JLabel("\uD83D\uDC64");
        // iconoUsuario.setFont(new Font("Arial", Font.PLAIN, 32));
        // iconoUsuario.setForeground(blanco);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(azulOscuro);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        // panelBienvenida.add(iconoUsuario);
        welcomePanel.add(welcomeLabel);

        menuButton = new JButton("☰");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 28));
        menuButton.setForeground(blanco);
        menuButton.setBackground(azulOscuro);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);

        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(menuButton, BorderLayout.EAST);

        // Panel wallet
        walletPanel = new JPanel();
        walletPanel.setLayout(new BoxLayout(walletPanel, BoxLayout.Y_AXIS));
        walletPanel.setBackground(azulOscuro);
        walletPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        walletPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel walletTitle = new JLabel("Monedero virtual");
        walletTitle.setFont(new Font("Arial", Font.BOLD, 20));
        walletTitle.setForeground(blanco);
        walletTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        balanceLabel = new JLabel("Saldo actual: BS 180,07");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        balanceLabel.setForeground(blanco);
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        movementsButton = new JButton("Ver últimos movimientos");
        movementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        movementsButton.setBackground(blanco);
        movementsButton.setForeground(azulOscuro);
        movementsButton.setFocusPainted(false);

        walletPanel.add(walletTitle);
        walletPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        walletPanel.add(balanceLabel);
        walletPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        walletPanel.add(movementsButton);

        // Panel contenido
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(grisClaro);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel = new JLabel("Sabor Central UCV");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(azulOscuro);

        subtitleLabel = new JLabel("Nutriendo mentes, deleitando paladares.");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setForeground(Color.GRAY);

        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(subtitleLabel);
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(walletPanel);

        // Panel lateral (menú)
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(200, 0));
        sidePanel.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Menú Principal");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        addBalanceButton = new JButton("Recargar wallet");
        consultButton = new JButton("Consultar menús");
        prePayButton = new JButton("Pre-pagar plato");

        for (JButton btn : new JButton[]{addBalanceButton, consultButton, prePayButton}) {
            btn.setBackground(blanco);
            btn.setForeground(azulOscuro);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        sidePanel.add(menuTitulo);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sidePanel.add(addBalanceButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(consultButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(prePayButton);
        sidePanel.setVisible(false);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
    }

    // Método para actualizar la pantalla según el usuario
    public void updateUser(RegisteredUser usuario) {
        welcomeLabel.setText("BIENVENIDO/A " + usuario.getFullName());
        balanceLabel.setText("Saldo actual: Bs. " + usuario.getBalance());
        addBalanceButton.setVisible(true);
        prePayButton.setVisible(true);
    }
}