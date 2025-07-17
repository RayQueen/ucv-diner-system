package view;
import javax.swing.*;
import java.awt.*;

public class IniciarSesionView extends JFrame {
    public JTextField textfield1;
    public JPasswordField textfield2;
    public JLabel label1, label2, label3;
    public JButton boton1;
    public JLabel forgotPassword;

    public IniciarSesionView() {
        setLayout(null);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Iniciar Sesión");
        label1.setBounds(35, 5, 300, 30);
        label1.setFont(new Font("Andale Mono", Font.BOLD, 18));
        add(label1);

        label2 = new JLabel("Usuario:");
        label2.setBounds(10, 40, 200, 30);
        add(label2);

        textfield1 = new JTextField();
        textfield1.setBounds(10, 70, 200, 30);
        add(textfield1);

        label3 = new JLabel("Contraseña:");
        label3.setBounds(10, 110, 200, 30);
        add(label3);

        textfield2 = new JPasswordField();
        textfield2.setBounds(10, 140, 200, 30);
        add(textfield2);

        forgotPassword = new JLabel("Olvidé mi contraseña");
        forgotPassword.setForeground(new Color(0, 102, 204));
        forgotPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassword.setBounds(10, 180, 200, 30);
        add(forgotPassword);

        boton1 = new JButton("INICIAR SESIÓN");
        boton1.setBounds(10, 220, 200, 30);
        add(boton1);
    }
}
