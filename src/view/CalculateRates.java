package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;

public class CalculateRates extends JFrame {

    public JFormattedTextField rateField;
    public JButton homeButton;
    public JButton logOutButton;
    public JButton saveButton;
    public JButton cancelButton;
    public JLabel periodLabel;

    public CalculateRates() {
        setTitle("Calcular Tarifas - Sabor Central UCV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(WHITE);

        // Background panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        topPanel.setBackground(PRINCIPAL_COLOR);

        // Period label
        periodLabel = templateLabel("Calculo CBB", TITLE1, WHITE, Component.LEFT_ALIGNMENT);

        // Period panel
        JPanel periodPanel = new JPanel();
        periodPanel.setBackground(PRINCIPAL_COLOR);
        periodPanel.setLayout(new BoxLayout(periodPanel, BoxLayout.Y_AXIS));

        // Icon panel for home and logout buttons
        JPanel iconButtonPanel = new JPanel();
        iconButtonPanel.setBackground(PRINCIPAL_COLOR);
        iconButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        // Load icons for home and logout buttons
        ImageIcon homeIcon = null;
        ImageIcon logoutIcon = null;
        try {
            java.net.URL homeUrl = getClass().getResource("/view/assets/home.png");
            java.net.URL logoutUrl = getClass().getResource("/view/assets/logout.png");
            if (homeUrl != null) {
                Image img = new ImageIcon(homeUrl).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                homeIcon = new ImageIcon(img);
            } else {
                System.err.println("No se encontró el icono: /view/assets/home.png");
                homeIcon = new ImageIcon();
            }
            if (logoutUrl != null) {
                Image img = new ImageIcon(logoutUrl).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                logoutIcon = new ImageIcon(img);
            } else {
                System.err.println("No se encontró el icono: /view/assets/logout.png");
                logoutIcon = new ImageIcon();
            }
        } catch (Exception e) {
            System.err.println("Error cargando iconos: " + e.getMessage());
            homeIcon = new ImageIcon();
            logoutIcon = new ImageIcon();
        }

        // Home button
        homeButton = templateButton(homeIcon, PRINCIPAL_COLOR, null, 48, 48);

        // Logout button
        logOutButton = templateButton(logoutIcon, PRINCIPAL_COLOR, null, 48, 48);
    
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80)); // Más espacio alrededor

        // ComboBox para tipo de usuario
        String[] userTypes = {"Estudiante", "Profesor", "Empleado"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setMaximumSize(new Dimension(300, 30));
        userTypeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Rate label and field
        JLabel rateLabel = templateLabel("Tarifa:", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        rateField = templateNumericTextField();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Save button
        saveButton = templateButton("Guardar", B_TEXT, SECONDARY_COLOR_LIGHT, PRINCIPAL_COLOR, 140);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, PRINCIPAL_COLOR, 140);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to period panel
        periodPanel.add(periodLabel);
        topPanel.add(periodPanel, BorderLayout.WEST);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);
        iconButtonPanel.add(homeButton);
        iconButtonPanel.add(logOutButton);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);

        // Add components for form panel
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        formPanel.add(userTypeComboBox); 
        formPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        formPanel.add(rateLabel);
        formPanel.add(rateField);

        // Add buttons to the button panel
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);

        // Agrega los paneles al JFrame
        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        formPanel.add(Box.createVerticalStrut(30)); // Espacio entre campo y botones
        formPanel.add(buttonPanel);
    }

    public static void main(String[] args) {
        CalculateRates calculateRates = new CalculateRates();
        calculateRates.setVisible(true);
        // Assuming you have a controller to handle the logic
        // new controllers.CalculateRatesController(calculateRates);
    }
}
