package view;

import javax.swing.*;
import java.awt.*;

public class GeneralView extends JPanel {

    // Guide colors
    public static final Color PRINCIPAL_COLOR = new Color(42, 71, 113);
    public static final Color SECONDARY_COLOR_LIGHT = new Color(101, 141, 192);
    public static final Color SECONDARY_COLOR_DARK = new Color(76, 109, 153);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color LIGHT_GRAY = new Color(104, 103, 103);
    public static final Color DARK_GRAY = new Color(44, 39, 40);
    public static final Color BACKGROUND_GRAY = new Color(217, 217, 217);

    // Fonts
    public static final Font TITLE1 = new Font("Arial", Font.BOLD, 32);
    public static final Font TITLE2 = new Font("Arial", Font.BOLD, 24);
    public static final Font TEXT = new Font("Arial", Font.PLAIN, 16);

    public GeneralView() {
        setBackground(BACKGROUND_GRAY);
        setLayout(new BorderLayout());

        // Panel de ejemplo con títulos y botón
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel titulo1 = new JLabel("TITULO 1");
        titulo1.setFont(TITLE1);
        titulo1.setForeground(PRINCIPAL_COLOR);

        JLabel titulo2 = new JLabel("TITULO 2");
        titulo2.setFont(TITLE2);
        titulo2.setForeground(SECONDARY_COLOR_DARK);

        JLabel texto = new JLabel("Cuerpo de texto");
        texto.setFont(TEXT);
        texto.setForeground(DARK_GRAY);

        JButton boton1 = createStyledButton("BOTON 1");
        JButton boton2 = createStyledButton("BOTON 2");
        boton2.setBackground(SECONDARY_COLOR_LIGHT);

        JPanel botones = new JPanel();
        botones.setOpaque(false);
        botones.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        botones.add(boton1);
        botones.add(boton2);

        content.add(titulo1);
        content.add(Box.createVerticalStrut(10));
        content.add(titulo2);
        content.add(Box.createVerticalStrut(10));
        content.add(texto);
        content.add(Box.createVerticalStrut(30));
        content.add(botones);

        add(content, BorderLayout.CENTER);
    }

    // Método para crear botones con estilo
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(TITLE2);
        button.setBackground(PRINCIPAL_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("General View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GeneralView());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
