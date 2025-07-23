package view;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class CostsView extends JFrame {
    public JFormattedTextField fixedCostField;
    public JFormattedTextField variableCostField;
    public JButton saveButton;
    public JButton menuButton;
    public JPanel sidePanel;
    public JButton homeButton;
    public JButton menuManagementButton;
    public JButton setPricingButton;
    public JButton submitConsumptionButton;
    public JButton generateReportsButton;
    public JLabel welcomeLabel;

    public CostsView() {
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

        welcomeLabel = new JLabel("Preparación de Costos: Periodo I-2025");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(blanco);

        menuButton = new JButton("☰");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 20));
        menuButton.setForeground(blanco);
        menuButton.setBackground(azulOscuro);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);

        panelSuperior.add(welcomeLabel, BorderLayout.WEST);
        panelSuperior.add(menuButton, BorderLayout.EAST);

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

        fixedCostField = new JFormattedTextField(formatoSoloNumeros);
        fixedCostField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        fixedCostField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etiquetaCostoVariable = new JLabel("Costo Variable:");
        etiquetaCostoVariable.setFont(new Font("Arial", Font.PLAIN, 16));
        etiquetaCostoVariable.setForeground(azulOscuro);
        etiquetaCostoVariable.setAlignmentX(Component.CENTER_ALIGNMENT);

        variableCostField = new JFormattedTextField(formatoSoloNumeros);
        variableCostField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        variableCostField.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(etiquetaCostoFijo);
        panelPrincipal.add(fixedCostField);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(etiquetaCostoVariable);
        panelPrincipal.add(variableCostField);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        saveButton = new JButton("Guardar");
        saveButton.setBackground(azulOscuro);
        saveButton.setForeground(blanco);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(saveButton);

        // Panel lateral
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(200, 0));
        sidePanel.setBackground(azulOscuro);

        JLabel menuTitulo = new JLabel("Menú Principal");
        menuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        menuTitulo.setForeground(blanco);

        homeButton = new JButton("Inicio");
        homeButton.setBackground(blanco);
        homeButton.setForeground(azulOscuro);

        menuManagementButton = new JButton("Gestión menú");
        menuManagementButton.setBackground(blanco);
        menuManagementButton.setForeground(azulOscuro);

        setPricingButton = new JButton("Establecer tarifas");
        setPricingButton.setBackground(blanco);
        setPricingButton.setForeground(azulOscuro);

        submitConsumptionButton = new JButton("Ingresar consumo diario");
        submitConsumptionButton.setBackground(blanco);
        submitConsumptionButton.setForeground(azulOscuro);

        generateReportsButton = new JButton("Generar reportes");
        generateReportsButton.setBackground(blanco);
        generateReportsButton.setForeground(azulOscuro);

        sidePanel.add(menuTitulo);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        sidePanel.add(homeButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(menuManagementButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(setPricingButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(submitConsumptionButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(generateReportsButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.setVisible(false);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
    }
}
