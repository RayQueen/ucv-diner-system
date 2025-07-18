package view;
import javax.swing.*;

public class RegistroView extends JFrame {
    public JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9;
    public JTextField nombre, apellido, correo, telefono, usuario;
    public JPasswordField contrasena;
    public JComboBox<String> rol;
    public JButton registrarse;

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
        nombre = new JTextField();
        nombre.setBounds(150, 40, 150, 25);
        add(nombre);

        label3 = new JLabel("Apellido:");
        label3.setBounds(35, 80, 100, 30);
        add(label3);
        apellido = new JTextField();
        apellido.setBounds(150, 80, 150, 25);
        add(apellido);

        label4 = new JLabel("Correo:");
        label4.setBounds(35, 120, 100, 30);
        add(label4);
        correo = new JTextField();
        correo.setBounds(150, 120, 150, 25);
        add(correo);

        label5 = new JLabel("Teléfono:");
        label5.setBounds(35, 160, 100, 30);
        add(label5);
        telefono = new JTextField();
        telefono.setBounds(150, 160, 150, 25);
        add(telefono);

        label6 = new JLabel("Usuario:");
        label6.setBounds(35, 200, 100, 30);
        add(label6);
        usuario = new JTextField();
        usuario.setBounds(150, 200, 150, 25);
        add(usuario);

        label7 = new JLabel("Contraseña:");
        label7.setBounds(35, 240, 100, 30);
        add(label7);
        contrasena = new JPasswordField();
        contrasena.setBounds(150, 240, 150, 25);
        add(contrasena);

        label8 = new JLabel("Rol:");
        label8.setBounds(35, 280, 100, 30);
        add(label8);
        rol = new JComboBox<>(new String[]{"estudiante", "empleado", "profesor", "admin"});
        rol.setBounds(150, 280, 150, 25);
        add(rol);

        registrarse = new JButton("COMPLETAR REGISTRO");
        registrarse.setBounds(100, 320, 200, 30);
        add(registrarse);
    }
}
