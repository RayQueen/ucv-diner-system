import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class Registro extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, label4, label5, label6, label7;
    private JTextField textfield1, textfield2, textfield3, textfield4, textfield5, textfield6;
    private JButton boton1;

    public Registro() {
        setLayout(null);
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Título
        label1 = new JLabel("Información Personal");
        label1.setBounds(150, 5, 200, 30);
        add(label1);

        // Nombre
        label2 = new JLabel("Nombre:");
        label2.setBounds(35, 40, 100, 30);
        add(label2);
        textfield1 = new JTextField();
        textfield1.setBounds(150, 40, 150, 25);
        add(textfield1);

        // Apellido
        label3 = new JLabel("Apellido:");
        label3.setBounds(35, 80, 100, 30);
        add(label3);
        textfield2 = new JTextField();
        textfield2.setBounds(150, 80, 150, 25);
        add(textfield2);

        // Correo UCV
        label4 = new JLabel("Correo UCV:");
        label4.setBounds(35, 120, 100, 30);
        add(label4);
        textfield3 = new JTextField();
        textfield3.setBounds(150, 120, 150, 25);
        add(textfield3);

        // Teléfono
        label5 = new JLabel("Teléfono:");
        label5.setBounds(35, 160, 100, 30);
        add(label5);
        textfield4 = new JTextField();
        textfield4.setBounds(150, 160, 150, 25);
        add(textfield4);

        // Rol
        label6 = new JLabel("Rol:");
        label6.setBounds(35, 200, 100, 30);
        add(label6);
        textfield5 = new JTextField();
        textfield5.setBounds(150, 200, 150, 25);
        add(textfield5);

        // Carrera/Departamento
        label7 = new JLabel("Carrera/Departamento:");
        label7.setBounds(35, 240, 150, 30);
        add(label7);
        textfield6 = new JTextField();
        textfield6.setBounds(150, 240, 150, 25);
        add(textfield6);

        // Botón de registro
        boton1 = new JButton("COMPLETAR REGISTRO");
        boton1.setBounds(100, 300, 200, 30);
        boton1.addActionListener(this);
        add(boton1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            // Validar campos vacíos
            if (textfield1.getText().isEmpty() || 
                textfield2.getText().isEmpty() ||
                textfield3.getText().isEmpty() ||
                textfield4.getText().isEmpty() ||
                textfield5.getText().isEmpty() ||
                textfield6.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete todos los campos obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar nombre y apellido
            if (!isValidName(textfield1.getText()) || !isValidName(textfield2.getText())) {
                JOptionPane.showMessageDialog(this, 
                    "El nombre y el apellido deben contener solo letras", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar correo
            if (!isValidEmail(textfield3.getText())) {
                JOptionPane.showMessageDialog(this, 
                    "El correo electrónico no es válido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar teléfono (solo números)
            if (!isValidPhone(textfield4.getText())) {
                JOptionPane.showMessageDialog(this, 
                    "El teléfono debe contener solo números", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, 
                "Registro completado exitosamente", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar campos después del registro
            textfield1.setText("");
            textfield2.setText("");
            textfield3.setText("");
            textfield4.setText("");
            textfield5.setText("");
            textfield6.setText("");
        }
    }

    // Método para validar nombres
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    // Método para validar correos electrónicos
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Método para validar teléfonos (solo números)
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d+"); // Solo números
    }

    public static void main(String[] args) {
        Registro ventana = new Registro();
        ventana.setBounds(0, 0, 500, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}

