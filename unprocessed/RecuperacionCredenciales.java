import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.List; // Ensure this is included

public class RecuperacionCredenciales extends JFrame implements ActionListener {

    private JTextField textfield1;
    private JLabel label1, label2, label3;
    private JButton boton1;
    private final String USERS_FILE = "usuarios.txt"; // Archivo de usuarios

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
        label3.setBounds(10, 110, 300, 30);
        add(label3);

        boton1 = new JButton("ENVIAR");
        boton1.setBounds(10, 150, 200, 30);
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

            // Generar una nueva contraseña temporal
            String nuevaContraseña = generarContraseñaTemporal();

            // Actualizar la contraseña en el archivo
            try {
                if (actualizarContraseña(correo, nuevaContraseña)) {
                    // Enviar el correo con la nueva contraseña
                    if (enviarCorreo(correo, nuevaContraseña)) {
                        JOptionPane.showMessageDialog(this, 
                            "Se ha enviado un correo para restablecer su contraseña", 
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Error al enviar el correo electrónico", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "El correo electrónico no está registrado", 
                        "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al procesar el archivo de usuarios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            textfield1.setText("");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private String generarContraseñaTemporal() {
        // Genera una contraseña temporal de 10 caracteres alfanuméricos
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return sb.toString();
    }

    private boolean actualizarContraseña(String email, String nuevaContraseña) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(USERS_FILE));
        boolean usuarioEncontrado = false;

        for (int i = 0; i < lineas.size(); i++) {
            String[] partes = lineas.get(i).split(",");
            if (partes.length >= 2 && partes[0].trim().equalsIgnoreCase(email)) {
                lineas.set(i, partes[0].trim() + ", " + nuevaContraseña);
                usuarioEncontrado = true;
                break;
            }
        }

        if (usuarioEncontrado) {
            Files.write(Paths.get(USERS_FILE), lineas);
            return true;
        }
        return false;
    }

    private boolean enviarCorreo(String toEmail, String nuevaContraseña) {
        // Configuración SMTP
        String host = "smtp.gmail.com";
        String from = "Soporte.CentralUCV@gmail.com"; // Cambia esto por tu correo
        String password = "Vic12345"; // Cambia esto por tu contraseña de aplicación

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Recuperación de Contraseña");
            message.setText("Estimado usuario,\n\n"
                         + "Tu nueva contraseña temporal es: " + nuevaContraseña + "\n\n"
                         + "Por favor, cámbiala después de iniciar sesión.\n\n"
                         + "Atentamente,\nEl equipo de soporte");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
       RecuperacionCredenciales ventana = new RecuperacionCredenciales();
        ventana.setBounds(0, 0, 500, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}

