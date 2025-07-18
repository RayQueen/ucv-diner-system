package view;

import javax.swing.*;
import java.awt.*;

public class PantallaInicioAdminView extends JFrame {
    // ...existing code...
    // Método para actualizar el nombre del usuario admin
    public void actualizarUsuario(String nombreCompleto) {
        etiquetaBienvenida.setText("BIENVENIDO/A " + nombreCompleto);
    }
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

    public PantallaInicioAdminView() {
        setTitle("Sabor Central UCV - Admin");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        etiquetaBienvenida = new JLabel("BIENVENIDO/A");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel etiquetaTitulo = new JLabel("Panel de Administración");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(etiquetaTitulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel etiquetaSubtitulo = new JLabel("Gestione las operaciones del comedor universitario.");
        etiquetaSubtitulo.setFont(new Font("Arial", Font.ITALIC, 14));
        etiquetaSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.add(etiquetaSubtitulo);

        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(220, 0));
        botonGestionarMenu = new JButton("Gestionar menú");
        botonCalcularCCB = new JButton("Calcular CCB");
        botonEstablecerTarifas = new JButton("Establecer tarifas");
        botonIngresarConsumo = new JButton("Ingresar consumo diario");
        botonGenerarReportes = new JButton("Generar reportes");
        panelLateral.add(new JLabel("Panel de Administración"));
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
}
