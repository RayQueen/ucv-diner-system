package view;

import javax.swing.*;
import java.awt.*;

public class ConsultMenuView extends JFrame {
    // ...existing code...
    public JPanel sideMenuPanel;
    public JPanel topPanel;
    public JPanel contentPanel;
    public JButton menuButton;
    public JLabel welcomeLabel;
    public JButton addBalanceButton;
    public JButton homeButton;
    public JButton prePayButton;

    // Método para actualizar la pantalla según el usuario
    public void updateUser(models.RegisteredUser usuario) {
        welcomeLabel.setText("BIENVENIDO/A " + usuario.getFullName());
        if (usuario.isAdmin()) {
            addBalanceButton.setVisible(false);
            prePayButton.setVisible(false);
        } else {
            addBalanceButton.setVisible(true);
            prePayButton.setVisible(true);
        }
    }

    public ConsultMenuView() {
        setTitle("Consultar Menús - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Paleta de colores
        Color azulOscuro = new Color(32, 61, 112);
        Color blanco = Color.WHITE;
        Color grisClaro = new Color(240, 240, 240);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        topPanel.setBackground(azulOscuro);

        welcomeLabel = new JLabel("BIENVENIDO Nombre Apellido");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(blanco);

        menuButton = new JButton("☰");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 20));
        menuButton.setForeground(blanco);
        menuButton.setBackground(azulOscuro);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);

        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(menuButton, BorderLayout.EAST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        contentPanel.setBackground(grisClaro);

        JLabel tituloPantalla = new JLabel("Consultar menús");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        tituloPantalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloPantalla.setForeground(azulOscuro);

        contentPanel.add(tituloPantalla);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(crearPanelTurno(
            "Turno Desayuno (7:00 AM - 11:00 AM)",
            "Sopa: Crema de auyama",
            "Seco: Pabellón criollo",
            "Jugo: Jugo de papelón con limón",
            "Postre: Manzana y durazno"
        ));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(crearPanelTurno(
            "Turno Almuerzo (12:00 PM - 5:00 PM)",
            "Sopa: Sopa de lentejas",
            "Seco: Pabellón criollo",
            "Jugo: Papelón con limón",
            "Postre: Quesillo"
        ));

        sideMenuPanel = new JPanel();
        sideMenuPanel.setLayout(new BoxLayout(sideMenuPanel, BoxLayout.Y_AXIS));
        sideMenuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sideMenuPanel.setPreferredSize(new Dimension(200, 0));
        sideMenuPanel.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Menú Principal");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        homeButton = new JButton("Inicio");
        homeButton.setBackground(blanco);
        homeButton.setForeground(azulOscuro);

        addBalanceButton = new JButton("Recargar wallet");
        addBalanceButton.setBackground(blanco);
        addBalanceButton.setForeground(azulOscuro);

        prePayButton = new JButton("Pre-pagar plato");
        prePayButton.setBackground(blanco);
        prePayButton.setForeground(azulOscuro);

        sideMenuPanel.add(menuTitulo);
        sideMenuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sideMenuPanel.add(addBalanceButton);
        sideMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sideMenuPanel.add(homeButton);
        sideMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sideMenuPanel.add(prePayButton);
        sideMenuPanel.setVisible(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        add(sideMenuPanel, BorderLayout.EAST);
    }

    private JPanel crearPanelTurno(String titulo, String sopa, String seco, String jugo, String postre) {
        JPanel panelTurno = new JPanel(new BorderLayout(10, 10));
        panelTurno.setBorder(BorderFactory.createTitledBorder(titulo));
        panelTurno.setBackground(Color.WHITE);

        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.setBackground(Color.WHITE);

        JLabel labelSopa = new JLabel(sopa);
        JLabel labelSeco = new JLabel(seco);
        JLabel labelJugo = new JLabel(jugo);
        JLabel labelPostre = new JLabel(postre);

        labelSopa.setFont(new Font("Arial", Font.PLAIN, 16));
        labelSeco.setFont(new Font("Arial", Font.PLAIN, 16));
        labelJugo.setFont(new Font("Arial", Font.PLAIN, 16));
        labelPostre.setFont(new Font("Arial", Font.PLAIN, 16));

        panelDetalles.add(labelSopa);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(labelSeco);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(labelJugo);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(labelPostre);

        JPanel panelBoton = new JPanel(new GridBagLayout());
        panelBoton.setBackground(Color.WHITE);
        JButton botonPrepagar = new JButton("Prepagar");
        panelBoton.add(botonPrepagar);

        panelTurno.add(panelDetalles, BorderLayout.CENTER);
        panelTurno.add(panelBoton, BorderLayout.EAST);
        return panelTurno;
    }
}
