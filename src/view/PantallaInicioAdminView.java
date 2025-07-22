package view;

import javax.swing.*;
import java.awt.*;

public class PantallaInicioAdminView extends JFrame {
    public JPanel panelLateral;
    public JPanel panelSuperior;
    public JPanel panelContenido;
    public JButton botonMenu;
    public JLabel etiquetaBienvenida;
    public JButton botonGestionarMenu;
    public JButton botonCalcularCCB;
    public JButton botonEstablecerTarifas;
    public JButton botonIngresarConsumo;
    public JButton botonGenerarReportes;

    // Paleta de colores basada en la imagen de usuario
    private final Color azulOscuro = new Color(32, 61, 112);
    private final Color blanco = Color.WHITE;
    private final Color grisClaro = new Color(240, 240, 240);

    public PantallaInicioAdminView() {
        setTitle("Sabor Central UCV - Admin");
        setSize(1500, 900); // Tamaño grande como el usuario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(grisClaro);

        // Panel superior
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelSuperior.setBackground(azulOscuro);

        etiquetaBienvenida = new JLabel("BIENVENIDO/A");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 32));
        etiquetaBienvenida.setForeground(blanco);

        JLabel iconoUsuario = new JLabel("\uD83D\uDC64");
        iconoUsuario.setFont(new Font("Arial", Font.PLAIN, 32));
        iconoUsuario.setForeground(blanco);

        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(azulOscuro);
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        panelBienvenida.add(iconoUsuario);
        panelBienvenida.add(etiquetaBienvenida);

        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 28));
        botonMenu.setForeground(blanco);
        botonMenu.setBackground(azulOscuro);
        botonMenu.setBorderPainted(false);
        botonMenu.setFocusPainted(false);

        panelSuperior.add(panelBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        // Panel contenido
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(grisClaro);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etiquetaTitulo = new JLabel("Panel de Administración");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTitulo.setForeground(azulOscuro);

        JLabel etiquetaSubtitulo = new JLabel("Gestione las operaciones del comedor universitario.");
        etiquetaSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        etiquetaSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaSubtitulo.setForeground(Color.GRAY);

        panelContenido.add(etiquetaTitulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));
        panelContenido.add(etiquetaSubtitulo);
        panelContenido.add(Box.createVerticalGlue());

        // Panel lateral (menú)
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(220, 0));
        panelLateral.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Panel de Administración");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        botonGestionarMenu = new JButton("Gestionar menú");
        botonCalcularCCB = new JButton("Calcular CCB");
        botonEstablecerTarifas = new JButton("Establecer tarifas");
        botonIngresarConsumo = new JButton("Ingresar consumo diario");
        botonGenerarReportes = new JButton("Generar reportes");

        for (JButton btn : new JButton[]{
                botonGestionarMenu,
                botonCalcularCCB,
                botonEstablecerTarifas,
                botonIngresarConsumo,
                botonGenerarReportes
        }) {
            btn.setBackground(blanco);
            btn.setForeground(azulOscuro);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        panelLateral.add(menuTitulo);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonGestionarMenu);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonCalcularCCB);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonEstablecerTarifas);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonIngresarConsumo);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonGenerarReportes);
        panelLateral.setVisible(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }

    // Método para actualizar el nombre del usuario admin en la vista
    public void actualizarUsuario(String nombreCompleto) {
        etiquetaBienvenida.setText("BIENVENIDO/A " + nombreCompleto);
    }
}
