package controllers;

import view.RecuperacionCredencialesView;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;
import models.Usuario;
import models.UsuarioRegistrado;

public class RecuperacionCredencialesController implements ActionListener {
    private RecuperacionCredencialesView view;
    public String ultimoMensaje;

    private Usuario usuarioModel = new Usuario("src/models/usuarios.txt");

    public RecuperacionCredencialesController(RecuperacionCredencialesView view) {
        this.view = view;
        this.view.enviar.addActionListener(this);
        this.view.correo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.enviar.doClick();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.enviar) {
            String correo = view.correo.getText().trim();
            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Ingrese su correo",
                    "Error", JOptionPane.WARNING_MESSAGE);
                ultimoMensaje = "Ingrese su correo";
                return;
            }
            if (!isValidEmail(correo)) {
                JOptionPane.showMessageDialog(view,
                    "El correo electrónico no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "El correo electrónico no es válido";
                return;
            }
            UsuarioRegistrado usuario = buscarUsuarioPorCorreo(correo);
            if (usuario == null) {
                JOptionPane.showMessageDialog(view,
                    "No existe ningún usuario asociado a ese correo electrónico",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "No existe ningún usuario asociado a ese correo electrónico";
                return;
            }
            if (enviarCorreo(correo, usuario.getUsuario(), usuario.getContrasena())) {
                JOptionPane.showMessageDialog(view,
                    "Se ha enviado un correo con sus credenciales",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                ultimoMensaje = "Se ha enviado un correo con sus credenciales";
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view,
                    "Error al enviar el correo electrónico",
                    "Error", JOptionPane.ERROR_MESSAGE);
                ultimoMensaje = "Error al enviar el correo electrónico";
            }
        }
    }

    private UsuarioRegistrado buscarUsuarioPorCorreo(String correo) {
        for (int i = 0; i < usuarioModel.usuariosValidos.length; i++) {
            UsuarioRegistrado u = usuarioModel.usuariosValidos[i];
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null;
    }

    private boolean enviarCorreo(String toEmail, String usuario, String contrasena) {
        String host = "smtp.gmail.com";
        String from = "reinamlaura@gmail.com"; // Cambia esto por tu correo
        String password = "Vic12345"; // Cambia esto por tu contraseña de aplicación

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(from, password);
                }
            });

        try {
            javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
            message.setFrom(new javax.mail.internet.InternetAddress(from));
            message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(toEmail));
            message.setSubject("Recuperación de Credenciales");
            message.setText("Estimado usuario,\n\n"
                         + "Su usuario es: " + usuario + "\n"
                         + "Su contraseña es: " + contrasena + "\n\n"
                         + "Por favor, cámbiela después de iniciar sesión si lo desea.\n\n"
                         + "Atentamente,\nEl equipo de soporte");

            javax.mail.Transport.send(message);
            return true;
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
