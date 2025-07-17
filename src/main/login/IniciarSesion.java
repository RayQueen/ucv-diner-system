import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class IniciarSesion extends JFrame implements ActionListener {
    
    private JTextField textfield1;
    private JPasswordField textfield2;
    private JLabel label1, label2, label3;
    private JButton boton1;
    private String[][] usuariosValidos; // Almacenará usuario y contraseña
    
    public IniciarSesion() {
        setLayout(null);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Cargar usuarios válidos desde archivo
        cargarUsuarios("usuarios.txt"); // << Ajusta la ruta según tu archivo
        
        
        // Interfaz gráfica
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

        // Botón de "Olvidé mi contraseña"
        JLabel forgotPassword = new JLabel("Olvidé mi contraseña");
        forgotPassword.setForeground(new Color(0, 102, 204));
        forgotPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassword.setBounds(10, 180, 200, 30);
        forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Aquí puedes agregar la lógica para recuperar la contraseña
                RecuperacionCredenciales ventana = new RecuperacionCredenciales();
                ventana.setBounds(0, 0, 500, 400);
                ventana.setVisible(true);
                ventana.setResizable(false);
                ventana.setLocationRelativeTo(null);
            }
        });
        add(forgotPassword);

        boton1 = new JButton("INICIAR SESIÓN");
        boton1.setBounds(10, 220, 200, 30);
        boton1.addActionListener(this);
        add(boton1);
    }

    private void cargarUsuarios(String rutaArchivo) {
        try {
            // Contar líneas para saber cuántos usuarios hay
            BufferedReader brCount = new BufferedReader(new FileReader(rutaArchivo));
            int totalUsuarios = 0;
            while (brCount.readLine() != null) {
                totalUsuarios++;
            }
            brCount.close();
            
            // Inicializar el array con el tamaño correcto
            usuariosValidos = new String[totalUsuarios][2];
            
            // Leer el archivo nuevamente para almacenar los datos
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int index = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(","); // Suponiendo que usa "," como separador
                if (partes.length == 2) {
                    usuariosValidos[index][0] = partes[0].trim(); // Usuario
                    usuariosValidos[index][1] = partes[1].trim(); // Contraseña
                    index++;
                }
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
            String usuarioIngresado = textfield1.getText().trim();
            String contrasenaIngresada = new String(textfield2.getPassword()).trim();

            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Usuario y contraseña son obligatorios", 
                    "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean credencialCorrecta = false;
            for (int i = 0; i < usuariosValidos.length; i++) {
                if (usuariosValidos[i][0].equals(usuarioIngresado) 
                    && usuariosValidos[i][1].equals(contrasenaIngresada)) {
                    credencialCorrecta = true;
                    break;
                }
            }

            if (credencialCorrecta) {
                JOptionPane.showMessageDialog(null, 
                    "¡Bienvenido, " + usuarioIngresado + "!", 
                    "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Usuario o contraseña incorrectos", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            textfield1.setText("");
            textfield2.setText("");
        }
    }

    public static void main(String[] args) {
        IniciarSesion ventana = new IniciarSesion();
        ventana.setBounds(0, 0, 500, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}


