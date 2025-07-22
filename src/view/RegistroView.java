package view;
import javax.swing.*;
import java.awt.*;

public class RegistroView extends JFrame {
    public JTextField nombre;
    public JTextField apellido;
    public JTextField usuario;
    public JTextField correo;
    public JTextField telefono;
    public JPasswordField contrasena;
    public JComboBox<String> rol;
    public JButton registrarse;
    public JButton cancelar;

    public RegistroView() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
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

        JLabel nombreLabel = new JLabel("Nombre");
        nombreLabel.setFont(new Font("Serif", Font.BOLD, 13));
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(nombreLabel);
        nombre = new JTextField();
        nombre.setMaximumSize(new Dimension(320, 28));
        nombre.setPreferredSize(new Dimension(320, 28));
        nombre.setFont(new Font("Serif", Font.PLAIN, 16));
        nombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(nombre);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel apellidoLabel = new JLabel("Apellido");
        apellidoLabel.setFont(new Font("Serif", Font.BOLD, 13));
        apellidoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(apellidoLabel);
        apellido = new JTextField();
        apellido.setMaximumSize(new Dimension(320, 28));
        apellido.setPreferredSize(new Dimension(320, 28));
        apellido.setFont(new Font("Serif", Font.PLAIN, 16));
        apellido.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        apellido.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(apellido);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel usuarioLabel = new JLabel("Usuario");
        usuarioLabel.setFont(new Font("Serif", Font.BOLD, 13));
        usuarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usuarioLabel);
        usuario = new JTextField();
        usuario.setMaximumSize(new Dimension(320, 28));
        usuario.setPreferredSize(new Dimension(320, 28));
        usuario.setFont(new Font("Serif", Font.PLAIN, 16));
        usuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        usuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usuario);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel correoLabel = new JLabel("Correo");
        correoLabel.setFont(new Font("Serif", Font.BOLD, 13));
        correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(correoLabel);
        correo = new JTextField();
        correo.setMaximumSize(new Dimension(320, 28));
        correo.setPreferredSize(new Dimension(320, 28));
        correo.setFont(new Font("Serif", Font.PLAIN, 16));
        correo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        correo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(correo);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel contrasenaLabel = new JLabel("Contraseña");
        contrasenaLabel.setFont(new Font("Serif", Font.BOLD, 13));
        contrasenaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(contrasenaLabel);
        contrasena = new JPasswordField();
        contrasena.setMaximumSize(new Dimension(320, 28));
        contrasena.setPreferredSize(new Dimension(320, 28));
        contrasena.setFont(new Font("Serif", Font.PLAIN, 16));
        contrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(contrasena);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));
        botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        registrarse = new JButton("Registrarse");
        registrarse.setFont(new Font("Arial", Font.BOLD, 13));
        registrarse.setBackground(new Color(70, 110, 150));
        registrarse.setForeground(Color.WHITE);
        registrarse.setFocusPainted(false);
        registrarse.setPreferredSize(new Dimension(150, 40));
        registrarse.setMaximumSize(new Dimension(150, 40));
        registrarse.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        botonesPanel.add(registrarse);
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
