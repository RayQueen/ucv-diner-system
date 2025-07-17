import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ValidacionCI extends JFrame implements ActionListener{

    private JLabel label1;
    private JTextField textfield1;
    private JButton boton1;
    private String[] CIValidas;

    public ValidacionCI(){
        setLayout(null);
        setTitle("Validación de credenciales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cargar usuarios válidos desde archivo
        cargarCI("CI_validas.txt");

        label1 = new JLabel("Ingresa tu cédula de identidad");
        label1.setBounds(35, 5, 300, 30);
        add(label1);

        textfield1 = new JTextField();
        textfield1.setBounds(10, 70, 200, 30);
        add(textfield1);

        boton1 = new JButton("ENVIAR");
        boton1.setBounds(10, 120, 200, 30);
        boton1.addActionListener(this);
        add(boton1);
    }

    private void cargarCI(String rutaArchivo) {
        try {
            // Contar líneas para saber cuántos usuarios hay
            BufferedReader brCount = new BufferedReader(new FileReader(rutaArchivo));
            int totalUsuarios = 0;
            while (brCount.readLine() != null) {
                totalUsuarios++;
            }
            brCount.close();
            
            // Inicializar el array con el tamaño correcto
            CIValidas = new String[totalUsuarios];
            
            // Leer el archivo nuevamente para almacenar los datos
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int index = 0;
            while ((linea = br.readLine()) != null) {
                    CIValidas[index] = linea.trim(); // Usuario
                    index++;
            }
            br.close();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al leer el archivo de usuarios: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            String CI_ingresada = textfield1.getText().trim();

            if (CI_ingresada.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Campo de cédula vacío", 
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean credencialCorrecta = false;
            for (int i = 0; i < CIValidas.length; i++) {
                if (CIValidas[i].equals(CI_ingresada)) {
                    credencialCorrecta = true;
                    break;
                }
            }

            if (credencialCorrecta) {
                Registro ventana = new Registro();
                ventana.setBounds(0, 0, 500, 400);
                ventana.setVisible(true);
                ventana.setResizable(false);
                ventana.setLocationRelativeTo(null);
                this.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, 
                    "No estás autorizado para registrarte. Contácta a Secretaría", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            textfield1.setText("");
        }
    }

    public static void main(String[] args) {
        ValidacionCI ventana = new ValidacionCI();
        ventana.setBounds(0, 0, 500, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}
