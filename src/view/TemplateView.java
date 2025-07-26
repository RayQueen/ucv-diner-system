package view;

import javax.swing.*;

import controllers.LogInController;

import java.awt.*;

public class TemplateView extends JPanel {
    // Guide colors
    public static final Color PRINCIPAL_COLOR = new Color(42, 71, 113);
    public static final Color SECONDARY_COLOR_LIGHT = new Color(220, 230, 245);
    public static final Color SECONDARY_COLOR_MEDIUM = new Color(101, 141, 192);
    public static final Color SECONDARY_COLOR_DARK = new Color(76, 109, 153);
    public static final Color BLACK = new Color(50, 50, 50);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color LIGHT_GRAY = new Color(104, 103, 103);
    public static final Color DARK_GRAY = new Color(44, 39, 40);
    public static final Color BACKGROUND_GRAY = new Color(217, 217, 217);

    // Fonts
    public static final Font TITLE1 = new Font("Arial", Font.BOLD, 32);
    public static final Font TITLE2 = new Font("Arial", Font.BOLD, 24);
    public static final Font TITLE3 = new Font("Arial", Font.BOLD, 20);
    public static final Font B_TEXT = new Font("Arial", Font.BOLD, 16);
    public static final Font TEXT = new Font("Arial", Font.PLAIN, 16);

    // Preview
    public JTextField userField;
    public JPasswordField passwordField;
    public JButton logInButton;
    public JButton registerButton;
    public JLabel forgotPassword;
    public LogInController logInController;

    public TemplateView() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;

        // Form panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        // Title
        JLabel titleLabel = templateLabel("INICIAR SESION", TITLE1, SECONDARY_COLOR_DARK, Component.CENTER_ALIGNMENT);

        // User label
        JLabel userLabel = templateLabel("Usuario", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        //User field
        userField = templateTextField();

        // Password label
        JLabel passwordLabel = templateLabel("Contraseña", B_TEXT, BLACK, Component.CENTER_ALIGNMENT);

        // Password field
        passwordField = templatePasswordField();

        // Forgot password label
        forgotPassword = templateLabel("Olvidé mi contraseña", TEXT, SECONDARY_COLOR_DARK, Component.LEFT_ALIGNMENT);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Log In button
        logInButton = templateButton("Iniciar sesión", B_TEXT, SECONDARY_COLOR_DARK, WHITE);
        logInButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Register button
        registerButton = templateButton("Registrarse", B_TEXT, SECONDARY_COLOR_LIGHT, PRINCIPAL_COLOR);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to left panel
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        leftPanel.add(userLabel);
        leftPanel.add(userField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 14)));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(forgotPassword);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(logInButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        leftPanel.add(registerButton);

        gbc.gridx = 0;
        mainPanel.add(leftPanel, gbc);

        // Logo panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(SECONDARY_COLOR_MEDIUM);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        rightPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        // Load and scale logo
        ImageIcon originalIcon = new ImageIcon("src/view/assets/logoWhite.png");
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Cambia el tamaño aquí
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to right panel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(logoLabel);
        rightPanel.add(Box.createVerticalGlue());

        gbc.gridx = 1;
        mainPanel.add(rightPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Template methods

    // Label template
    public static JLabel templateLabel(String text, Font font, Color color, float alignment) {
        JLabel templateLabel = new JLabel(text);
        templateLabel.setFont(font);
        templateLabel.setForeground(color);
        templateLabel.setAlignmentX(alignment);
        return templateLabel;
    }

    // Text field template
    public static JTextField templateTextField(Font font, int width, int height) {
        JTextField templateTextField = new JTextField();
        templateTextField.setMaximumSize(new Dimension(width, height));
        templateTextField.setPreferredSize(new Dimension(width, height));
        templateTextField.setFont(font);
        templateTextField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        return templateTextField;
    }

    public static JTextField templateTextField() {
        return templateTextField(TEXT, 320, 45);
    }


    // Numeric text field template
    public static JTextField templateNumericTextField() {
        JTextField numericField = new JTextField();
        numericField.setMaximumSize(new Dimension(320, 45));
        numericField.setPreferredSize(new Dimension(320, 45));
        numericField.setFont(TEXT);
        numericField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        numericField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DocumentFilter para solo dígitos o vacío
        ((javax.swing.text.AbstractDocument) numericField.getDocument()).setDocumentFilter(new javax.swing.text.DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                if (string == null || string.isEmpty() || string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                if (text == null || text.isEmpty() || text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        return numericField;
    }

    // Decimal text field template
    public static JTextField templateDecimalTextField() {
        JTextField decimalField = new JTextField();
        decimalField.setMaximumSize(new Dimension(320, 45));
        decimalField.setPreferredSize(new Dimension(320, 45));
        decimalField.setFont(TEXT);
        decimalField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        decimalField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // DocumentFilter para solo dígitos, un punto y vacío
        ((javax.swing.text.AbstractDocument) decimalField.getDocument()).setDocumentFilter(new javax.swing.text.DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                if (isValidDecimal(fb.getDocument().getText(0, fb.getDocument().getLength()), string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                StringBuilder sb = new StringBuilder(currentText);
                sb.replace(offset, offset + length, text == null ? "" : text);
                if (isValidDecimal("", sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
            private boolean isValidDecimal(String before, String after) {
                if (after.isEmpty()) return true;
                // Solo dígitos y un punto, no dos puntos, no punto al inicio
                return after.matches("\\d*\\.?\\d*") && after.chars().filter(ch -> ch == '.').count() <= 1 && !after.startsWith(".");
            }
        });

        return decimalField;
    }

    // Password field template
    public static JPasswordField templatePasswordField(Font font, int width, int height) {
        JPasswordField templatePasswordField = new JPasswordField();
        templatePasswordField.setMaximumSize(new Dimension(width, height));
        templatePasswordField.setPreferredSize(new Dimension(width, height));
        templatePasswordField.setFont(font);
        templatePasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        return templatePasswordField;
    }

    public static JPasswordField templatePasswordField() {
        return templatePasswordField(TEXT, 320, 45);
    }

    // Button template
    public static JButton templateButton(String text, Font font, Color background, Color foreground, int width, int height) {
        JButton templateButton = new JButton(text);
        templateButton.setFont(font);
        templateButton.setBackground(background);
        templateButton.setForeground(foreground);
        templateButton.setFocusPainted(false);
        templateButton.setPreferredSize(new Dimension(width, height));
        templateButton.setMaximumSize(new Dimension(width, height));
        templateButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        return templateButton;
    }

    public static JButton templateButton(ImageIcon icon, Color background, Color foreground, int width, int height) {
        JButton templateButton = new JButton(icon);
        templateButton.setBackground(background);
        templateButton.setForeground(foreground);
        templateButton.setFocusPainted(false);
        templateButton.setPreferredSize(new Dimension(width, height));
        templateButton.setMaximumSize(new Dimension(width, height));
        templateButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        return templateButton;
    }

    public static JButton templateButton(String text, Font font, Color background, Color foreground, int width) {
        return templateButton(text, font, background, foreground, width, 45);
    }

    public static JButton templateButton(String text, Font font, Color background, Color foreground) {
        return templateButton(text, font, background, foreground, 320, 45);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("General View Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new TemplateView());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
