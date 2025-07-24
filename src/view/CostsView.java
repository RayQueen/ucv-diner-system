package view;

import static view.TemplateView.*;

import javax.swing.*;
import java.awt.*;

public class CostsView extends JFrame {
    public JFormattedTextField fixedCostField;
    public JFormattedTextField variableCostField;
    public JFormattedTextField plateNumberField;
    public JFormattedTextField shrinkageField;
    public JButton saveButton;
    public JButton cancelButton;
    public JButton homeButton;
    public JButton logOutButton;
    public JLabel periodLabel;

    public CostsView() {
        setTitle("Configuración de Costos - Sabor Central UCV");
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
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fixed Cost label and field
        JLabel fixedCostLabel = templateLabel("Costo Fijo", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        fixedCostField = templateNumericTextField();

        // Variable Cost label and field
        JLabel variableCostLabel = templateLabel("Costo Variable", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        variableCostField = templateNumericTextField();

        // Plate Number label and field
        JLabel plateNumberLabel = templateLabel("Número de Bandejas", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        plateNumberField = templateNumericTextField();

        // Shrinkage label and field
        JLabel shrinkageLabel = templateLabel("Merma (%)", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);
        shrinkageField = templateNumericTextField();

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Save button
        saveButton = templateButton("Guardar", B_TEXT, SECONDARY_COLOR_DARK, WHITE, 140);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cancel button
        cancelButton = templateButton("Cancelar", B_TEXT, SECONDARY_COLOR_LIGHT, SECONDARY_COLOR_DARK, 140);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to period panel
        periodPanel.add(periodLabel);
        topPanel.add(periodPanel, BorderLayout.WEST);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);
        iconButtonPanel.add(homeButton);
        iconButtonPanel.add(logOutButton);
        topPanel.add(iconButtonPanel, BorderLayout.EAST);
        
        // Add buttons to button panel
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);

        // Add components to form panel
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(fixedCostLabel);
        formPanel.add(fixedCostField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(variableCostLabel);
        formPanel.add(variableCostField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(plateNumberLabel);
        formPanel.add(plateNumberField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(shrinkageLabel);
        formPanel.add(shrinkageField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(buttonPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
}
