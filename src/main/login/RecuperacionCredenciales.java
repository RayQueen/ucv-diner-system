import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;

public class RecuperacionCredenciales extends JFrame implements ActionListener {

    private JTextField textfield1;
    private JLabel label1, label2, label3;
    private JButton boton1;

    public RecuperacionCredenciales() {
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
        label3.setBounds(10, 110, 300, 30); // Cambié la posición Y para evitar superposición
        add(label3);

        boton1 = new JButton("ENVIAR");
        boton1.setBounds(10, 150, 200, 30); // Cambié la posición Y para evitar superposición
        boton1.addActionListener(this);
        add(boton1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String correo = textfield1.getText().trim();

            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Ingrese su correo", 
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!isValidEmail(correo)) {
                JOptionPane.showMessageDialog(this, 
                    "El correo electrónico no es válido", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aquí podrías implementar la lógica para enviar el correo
            JOptionPane.showMessageDialog(this, 
                "Se ha enviado un correo para restablecer su contraseña", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            textfield1.setText("");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static void main(String[] args) {
        RecuperacionCredenciales ventana = new RecuperacionCredenciales();
        ventana.setBounds(0, 0, 500, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}

