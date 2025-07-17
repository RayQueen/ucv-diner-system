package view;
import javax.swing.*;

public class ValidacionCIView extends JFrame {
    public JLabel label1;
    public JTextField textfield1;
    public JButton boton1;

    public ValidacionCIView() {
        setLayout(null);
        setTitle("Validación de credenciales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Ingresa tu cédula de identidad");
        label1.setBounds(35, 5, 300, 30);
        add(label1);

        textfield1 = new JTextField();
        textfield1.setBounds(10, 70, 200, 30);
        add(textfield1);

        boton1 = new JButton("ENVIAR");
        boton1.setBounds(10, 120, 200, 30);
        add(boton1);
    }
}
