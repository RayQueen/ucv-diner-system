package view;
import javax.swing.*;

public class RegistroView extends JFrame {
    public JLabel label1, label2, label3, label4, label5, label6, label7;
    public JTextField textfield1, textfield2, textfield3, textfield4, textfield5, textfield6;
    public JButton boton1;

    public RegistroView() {
        setLayout(null);
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Información Personal");
        label1.setBounds(150, 5, 200, 30);
        add(label1);

        label2 = new JLabel("Nombre:");
        label2.setBounds(35, 40, 100, 30);
        add(label2);
        textfield1 = new JTextField();
        textfield1.setBounds(150, 40, 150, 25);
        add(textfield1);

        label3 = new JLabel("Apellido:");
        label3.setBounds(35, 80, 100, 30);
        add(label3);
        textfield2 = new JTextField();
        textfield2.setBounds(150, 80, 150, 25);
        add(textfield2);

        label4 = new JLabel("Correo UCV:");
        label4.setBounds(35, 120, 100, 30);
        add(label4);
        textfield3 = new JTextField();
        textfield3.setBounds(150, 120, 150, 25);
        add(textfield3);

        label5 = new JLabel("Teléfono:");
        label5.setBounds(35, 160, 100, 30);
        add(label5);
        textfield4 = new JTextField();
        textfield4.setBounds(150, 160, 150, 25);
        add(textfield4);

        label6 = new JLabel("Rol:");
        label6.setBounds(35, 200, 100, 30);
        add(label6);
        textfield5 = new JTextField();
        textfield5.setBounds(150, 200, 150, 25);
        add(textfield5);

        label7 = new JLabel("Carrera/Departamento:");
        label7.setBounds(35, 240, 150, 30);
        add(label7);
        textfield6 = new JTextField();
        textfield6.setBounds(150, 240, 150, 25);
        add(textfield6);

        boton1 = new JButton("COMPLETAR REGISTRO");
        boton1.setBounds(100, 300, 200, 30);
        add(boton1);
    }
}
