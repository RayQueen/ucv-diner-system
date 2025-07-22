package view;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class CostosView extends JFrame {
    public JFormattedTextField campoCostoFijo;
    public JFormattedTextField campoCostoVariable;
    public JButton botonGuardar;
    public JButton botonMenu;
    public JPanel panelLateral;
    public JButton botonInicio;
    public JButton botonGestionMenu;
    public JButton botonTarifas;
    public JButton botonConsumo;
    public JButton botonReporte;

    public JLabel etiquetaBienvenida;

    public CostosView() {
        setTitle("Configuración de Costos - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Paleta de colores
        Color azulOscuro = new Color(32, 61, 112);
        Color blanco = Color.WHITE;
        Color grisClaro = new Color(240, 240, 240);

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelSuperior.setBackground(azulOscuro);

        etiquetaBienvenida = new JLabel("Preparación de Costos: Periodo I-2025");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaBienvenida.setForeground(blanco);

        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        botonMenu.setForeground(blanco);
        botonMenu.setBackground(azulOscuro);
        botonMenu.setBorderPainted(false);
        botonMenu.setFocusPainted(false);

        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        // Panel principal tipo registro
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(grisClaro);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        JLabel tituloPantalla = new JLabel("Configuración de Costos");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        tituloPantalla.setForeground(azulOscuro);
        tituloPantalla.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(tituloPantalla);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        formatoNumero.setGroupingUsed(false);
        NumberFormatter formatoSoloNumeros = new NumberFormatter(formatoNumero);
        formatoSoloNumeros.setValueClass(Double.class);
        formatoSoloNumeros.setAllowsInvalid(false);

        JLabel etiquetaCostoFijo = new JLabel("Costo Fijo:");
        etiquetaCostoFijo.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaCostoFijo.setForeground(azulOscuro);
        etiquetaCostoFijo.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoCostoFijo = new JFormattedTextField(formatoSoloNumeros);
        campoCostoFijo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campoCostoFijo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etiquetaCostoVariable = new JLabel("Costo Variable:");
        etiquetaCostoVariable.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaCostoVariable.setForeground(azulOscuro);
        etiquetaCostoVariable.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoCostoVariable = new JFormattedTextField(formatoSoloNumeros);
        campoCostoVariable.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campoCostoVariable.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(etiquetaCostoFijo);
        panelPrincipal.add(campoCostoFijo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(etiquetaCostoVariable);
        panelPrincipal.add(campoCostoVariable);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        botonGuardar = new JButton("Guardar");
        botonGuardar.setBackground(azulOscuro);
        botonGuardar.setForeground(blanco);
        botonGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(botonGuardar);

        // Panel lateral
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));
        panelLateral.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Menú Principal");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        botonInicio = new JButton("Inicio");
        botonInicio.setBackground(blanco);
        botonInicio.setForeground(azulOscuro);

        botonGestionMenu = new JButton("Gestión menú");
        botonGestionMenu.setBackground(blanco);
        botonGestionMenu.setForeground(azulOscuro);

        botonTarifas = new JButton("Establecer tarifas");
        botonTarifas.setBackground(blanco);
        botonTarifas.setForeground(azulOscuro);

        botonConsumo = new JButton("Ingresar consumo diario");
        botonConsumo.setBackground(blanco);
        botonConsumo.setForeground(azulOscuro);

        botonReporte = new JButton("Generar reportes");
        botonReporte.setBackground(blanco);
        botonReporte.setForeground(azulOscuro);

        panelLateral.add(menuTitulo);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 15)));
        panelLateral.add(botonInicio);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonGestionMenu);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonTarifas);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonConsumo);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.add(botonReporte);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelLateral.setVisible(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }
}
