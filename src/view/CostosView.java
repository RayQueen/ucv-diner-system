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

    // Método para actualizar el mensaje de bienvenida
    public void actualizarUsuario(String nombreCompleto) {
        etiquetaBienvenida.setText("BIENVENIDO/A " + nombreCompleto);
    }

    public CostosView() {
        setTitle("Configuración de Costos - Sabor Central UCV");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        etiquetaBienvenida = new JLabel("BIENVENIDO");
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSuperior.add(etiquetaBienvenida, BorderLayout.WEST);
        panelSuperior.add(botonMenu, BorderLayout.EAST);

        JPanel panelContenido = new JPanel(new GridBagLayout());
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloPantalla = new JLabel("Configuración de Costos");
        tituloPantalla.setFont(new Font("Arial", Font.BOLD, 24));

        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        formatoNumero.setGroupingUsed(false);
        NumberFormatter formatoSoloNumeros = new NumberFormatter(formatoNumero);
        formatoSoloNumeros.setValueClass(Double.class);
        formatoSoloNumeros.setAllowsInvalid(false);

        JLabel etiquetaCostoFijo = new JLabel("Costo Fijo:");
        campoCostoFijo = new JFormattedTextField(formatoSoloNumeros);
        campoCostoFijo.setColumns(15);

        JLabel etiquetaCostoVariable = new JLabel("Costo Variable:");
        campoCostoVariable = new JFormattedTextField(formatoSoloNumeros);
        campoCostoVariable.setColumns(15);

        botonGuardar = new JButton("Guardar");

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelContenido.add(tituloPantalla, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        panelContenido.add(etiquetaCostoFijo, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(campoCostoFijo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelContenido.add(etiquetaCostoVariable, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelContenido.add(campoCostoVariable, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelContenido.add(botonGuardar, gbc);

        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelLateral.setPreferredSize(new Dimension(200, 0));

        botonInicio = new JButton("Inicio");
        botonGestionMenu = new JButton("Gestión menú");
        botonTarifas = new JButton("Establecer tarifas");
        botonConsumo = new JButton("Ingresar consumo diario");
        botonReporte = new JButton("Generar reportes");

        panelLateral.add(new JLabel("Menú Principal"));
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
        add(panelContenido, BorderLayout.CENTER);
        add(panelLateral, BorderLayout.EAST);
    }
}
