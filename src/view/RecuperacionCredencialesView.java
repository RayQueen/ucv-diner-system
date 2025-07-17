package view;
import javax.swing.*;
import java.awt.*;

public class RecuperacionCredencialesView extends JFrame {
    public JTextField textfield1;
    public JLabel label1, label2, label3;
    public JButton boton1;

    public RecuperacionCredencialesView() {
        setLayout(null);
        setTitle("Recuperación de contraseña");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Olvidé mi contraseña");
        label1.setBounds(35, 5, 300, 30);
        label1.setFont(new Font("Andale Mono", Font.BOLD, 18));
        add(label1);

        label2 = new JLabel("Ingresa tu correo:");
        label2.setBounds(10, 40, 200, 30);
        add(label2);

        textfield1 = new JTextField();
        textfield1.setBounds(10, 70, 200, 30);
        add(textfield1);

        label3 = new JLabel("Recibirás un correo para restablecer tu contraseña");
        label3.setBounds(10, 110, 300, 30);
        add(label3);

        boton1 = new JButton("ENVIAR");
        boton1.setBounds(10, 150, 200, 30);
        add(boton1);
    }
}
