package view;

import javax.swing.*;
import java.awt.*;

public class AdminFeedView extends JFrame {
    public JPanel sidePanel;
    public JPanel topPanel;
    public JPanel contentPanel;
    public JButton menuButton;
    public JLabel welcomeLabel;
    public JButton menuManagementButton;
    public JButton calculateCCBButton;
    public JButton setPricingButton;
    public JButton submitConsumptionButton;
    public JButton generateReportsButton;

    // Paleta de colores basada en la imagen de usuario
    private final Color azulOscuro = new Color(32, 61, 112);
    private final Color blanco = Color.WHITE;
    private final Color grisClaro = new Color(240, 240, 240);

    public AdminFeedView() {
        setTitle("Sabor Central UCV - Admin");
        setSize(1500, 900); // Tamaño grande como el usuario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(grisClaro);

        // Panel superior
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        topPanel.setBackground(azulOscuro);

        welcomeLabel = new JLabel("BIENVENIDO/A");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(blanco);

        JLabel iconoUsuario = new JLabel("\uD83D\uDC64");
        iconoUsuario.setFont(new Font("Arial", Font.PLAIN, 32));
        iconoUsuario.setForeground(blanco);

        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(azulOscuro);
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        panelBienvenida.add(iconoUsuario);
        panelBienvenida.add(welcomeLabel);

        menuButton = new JButton("☰");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 28));
        menuButton.setForeground(blanco);
        menuButton.setBackground(azulOscuro);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);

        topPanel.add(panelBienvenida, BorderLayout.WEST);
        topPanel.add(menuButton, BorderLayout.EAST);

        // Panel contenido
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(grisClaro);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etiquetaTitulo = new JLabel("Panel de Administración");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTitulo.setForeground(azulOscuro);

        JLabel etiquetaSubtitulo = new JLabel("Gestione las operaciones del comedor universitario.");
        etiquetaSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        etiquetaSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaSubtitulo.setForeground(Color.GRAY);

        contentPanel.add(etiquetaTitulo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(etiquetaSubtitulo);
        contentPanel.add(Box.createVerticalGlue());

        // Panel lateral (menú)
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(220, 0));
        sidePanel.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Panel de Administración");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        menuManagementButton = new JButton("Gestionar menú");
        calculateCCBButton = new JButton("Calcular CCB");
        setPricingButton = new JButton("Establecer tarifas");
        submitConsumptionButton = new JButton("Ingresar consumo diario");
        generateReportsButton = new JButton("Generar reportes");

        for (JButton btn : new JButton[]{
                menuManagementButton,
                calculateCCBButton,
                setPricingButton,
                submitConsumptionButton,
                generateReportsButton
        }) {
            btn.setBackground(blanco);
            btn.setForeground(azulOscuro);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        sidePanel.add(menuTitulo);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sidePanel.add(menuManagementButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(calculateCCBButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(setPricingButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(submitConsumptionButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(generateReportsButton);
        sidePanel.setVisible(false);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
    }

    // Método para actualizar el nombre del usuario admin en la vista
    public void updateUser(String nombreCompleto) {
        welcomeLabel.setText("BIENVENIDO/A " + nombreCompleto);
    }
}
