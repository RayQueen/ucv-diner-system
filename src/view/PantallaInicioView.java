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

    public PantallaInicioView() {
        setTitle("Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        etiquetaBienvenida = new JLabel("BIENVENIDO/A Nombre Apellido");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelWallet = new JPanel();
        panelWallet.setLayout(new BoxLayout(panelWallet, BoxLayout.Y_AXIS));
        panelWallet.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelWallet.setBorder(BorderFactory.createTitledBorder("WALLET"));
        etiquetaSaldo = new JLabel("Saldo actual: BS 180,07");
        etiquetaSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMovimientos = new JButton("Ver últimos movimientos");
        botonMovimientos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelWallet.add(etiquetaSaldo);
        panelWallet.add(Box.createRigidArea(new Dimension(0, 10)));
        panelWallet.add(botonMovimientos);
        etiquetaTitulo = new JLabel("Sabor Central UCV");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaSubtitulo = new JLabel("Nutriendo mentes, deleitando paladares.");
        etiquetaSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        etiquetaSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(etiquetaTitulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 5)));
        panelContenido.add(etiquetaSubtitulo);
        panelContenido.add(Box.createVerticalGlue());
        panelContenido.add(panelWallet);

        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));
        botonRecargar = new JButton("Recargar wallet");
        botonConsultar = new JButton("Consultar menús");
        botonPrePagar = new JButton("Pre-pagar plato");
        panelLateral.add(new JLabel("Menú Principal"));
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
