package view;
import javax.swing.*;
import java.awt.*;

public class RecuperacionCredencialesView extends JFrame {
    public JTextField correo;
    public JButton enviar;
    public JButton cancelar;

    public RecuperacionCredencialesView() {
        setTitle("Recuperar Credenciales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(70, 110, 150));
        mainPanel.setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel correoLabel = new JLabel("Correo");
        correoLabel.setFont(new Font("Serif", Font.BOLD, 13));
        correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(correoLabel);
        correo = new JTextField();
        correo.setMaximumSize(new Dimension(320, 45));
        correo.setPreferredSize(new Dimension(320, 45));
        correo.setFont(new Font("Serif", Font.PLAIN, 16));
        correo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        correo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(correo);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel mensajeLabel = new JLabel("Recibirás un correo para reestablecer tu contraseña");
        mensajeLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        mensajeLabel.setForeground(new Color(70, 110, 150));
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(mensajeLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));
        botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        enviar = new JButton("Recuperar");
        enviar.setFont(new Font("Arial", Font.BOLD, 13));
        enviar.setBackground(new Color(70, 110, 150));
        enviar.setForeground(Color.WHITE);
        enviar.setFocusPainted(false);
        enviar.setPreferredSize(new Dimension(150, 40));
        enviar.setMaximumSize(new Dimension(150, 40));
        enviar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        botonesPanel.add(enviar);
        botonesPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        cancelar = new JButton("Cancelar");
        cancelar.setFont(new Font("Arial", Font.BOLD, 13));
        cancelar.setBackground(new Color(220, 230, 245));
        cancelar.setForeground(new Color(70, 110, 150));
        cancelar.setFocusPainted(false);
        cancelar.setPreferredSize(new Dimension(150, 40));
        cancelar.setMaximumSize(new Dimension(150, 40));
        cancelar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        botonesPanel.add(cancelar);

        formPanel.add(botonesPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(formPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}
