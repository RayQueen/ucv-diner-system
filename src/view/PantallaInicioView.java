package view;

import javax.swing.*;
import java.awt.*;

import models.UsuarioRegistrado;

public class PantallaInicioView extends JFrame {
    public JPanel panelLateral;
    public JPanel panelSuperior;
    public JPanel panelContenido;
    public JButton botonMenu;
    public JLabel etiquetaBienvenida;
    public JPanel panelWallet;
    public JLabel etiquetaSaldo;
    public JButton botonMovimientos;
    public JLabel etiquetaTitulo;
    public JLabel etiquetaSubtitulo;
    public JButton botonRecargar;
    public JButton botonConsultar;
    public JButton botonPrePagar;

    // Paleta de colores basada en la imagen
    private final Color azulOscuro = new Color(32, 61, 112);
    private final Color blanco = Color.WHITE;
    private final Color grisClaro = new Color(240, 240, 240);

    public PantallaInicioView() {
        setTitle("Sabor Central UCV");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(grisClaro);

        // Panel superior
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelSuperior.setBackground(azulOscuro);

        etiquetaBienvenida = new JLabel("BIENVENIDO");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 32));
        etiquetaBienvenida.setForeground(blanco);

        // Icono usuario (puedes cambiar por un icono real si lo tienes)
        // JLabel iconoUsuario = new JLabel("\uD83D\uDC64");
        // iconoUsuario.setFont(new Font("Arial", Font.PLAIN, 32));
        // iconoUsuario.setForeground(blanco);

        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(azulOscuro);
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        // panelBienvenida.add(iconoUsuario);
        panelBienvenida.add(etiquetaBienvenida);

        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 28));
        botonMenu.setForeground(blanco);
        botonMenu.setBackground(azulOscuro);
        botonMenu.setBorderPainted(false);
        botonMenu.setFocusPainted(false);

        panelSuperior.add(panelBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        // Panel wallet
        panelWallet = new JPanel();
        panelWallet.setLayout(new BoxLayout(panelWallet, BoxLayout.Y_AXIS));
        panelWallet.setBackground(azulOscuro);
        panelWallet.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelWallet.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel walletTitulo = new JLabel("WALLET");
        walletTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        walletTitulo.setForeground(blanco);
        walletTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        etiquetaSaldo = new JLabel("Saldo actual: BS 180,07");
        etiquetaSaldo.setFont(new Font("Arial", Font.BOLD, 18));
        etiquetaSaldo.setForeground(blanco);
        etiquetaSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonMovimientos = new JButton("Ver últimos movimientos");
        botonMovimientos.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMovimientos.setBackground(blanco);
        botonMovimientos.setForeground(azulOscuro);
        botonMovimientos.setFocusPainted(false);

        panelWallet.add(walletTitulo);
        panelWallet.add(Box.createRigidArea(new Dimension(0, 10)));
        panelWallet.add(etiquetaSaldo);
        panelWallet.add(Box.createRigidArea(new Dimension(0, 10)));
        panelWallet.add(botonMovimientos);

        // Panel contenido
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(grisClaro);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);

        etiquetaTitulo = new JLabel("Sabor Central UCV");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTitulo.setForeground(azulOscuro);

        etiquetaSubtitulo = new JLabel("Nutriendo mentes, deleitando paladares.");
        etiquetaSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        etiquetaSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaSubtitulo.setForeground(Color.GRAY);

        panelContenido.add(etiquetaTitulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 5)));
        panelContenido.add(etiquetaSubtitulo);
        panelContenido.add(Box.createVerticalGlue());
        panelContenido.add(panelWallet);

        // Panel lateral (menú)
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));
        panelLateral.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Menú Principal");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        botonRecargar = new JButton("Recargar wallet");
        botonConsultar = new JButton("Consultar menús");
        botonPrePagar = new JButton("Pre-pagar plato");

        for (JButton btn : new JButton[]{botonRecargar, botonConsultar, botonPrePagar}) {
            btn.setBackground(blanco);
            btn.setForeground(azulOscuro);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        panelLateral.add(menuTitulo);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonRecargar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonConsultar);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonPrePagar);
        panelLateral.setVisible(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }

    // Método para actualizar la pantalla según el usuario
    public void actualizarUsuario(UsuarioRegistrado usuario) {
        etiquetaBienvenida.setText("BIENVENIDO/A " + usuario.getNombreCompleto());
        etiquetaSaldo.setText("Saldo actual: Bs. " + usuario.getSaldo());
        // Ejemplo: mostrar/ocultar botones según el tipo de usuario
        if (usuario.esAdmin()) {
            botonRecargar.setVisible(false);
            botonPrePagar.setVisible(false);
        } else {
            botonRecargar.setVisible(true);
            botonPrePagar.setVisible(true);
        }
        // Puedes agregar más personalización aquí
    }
}