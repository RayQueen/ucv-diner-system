package controllers;

import view.LogInView;
import view.CredentialRecoveryView;

import models.ValidUsers;
import models.RegisteredUser;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;

public class CredentialRecoveryController implements ActionListener {
    private CredentialRecoveryView CredentialRecoveryView;
    private ValidUsers validUserModel = new ValidUsers("src/models/validUsers.txt");
    public String lastMessage;

    public CredentialRecoveryController(CredentialRecoveryView CredentialRecoveryView) {
        this.CredentialRecoveryView = CredentialRecoveryView;
        this.CredentialRecoveryView.sendButton.addActionListener(this);
        this.CredentialRecoveryView.emailField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CredentialRecoveryView.sendButton.doClick();
            }
        });
        this.CredentialRecoveryView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CredentialRecoveryView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CredentialRecoveryView.sendButton) {
            String email = CredentialRecoveryView.emailField.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Ingrese su correo",
                    "Error", JOptionPane.WARNING_MESSAGE);
                lastMessage = "Ingrese su correo";
                return;
            }
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(CredentialRecoveryView,
                    "El correo electrónico no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "El correo electrónico no es válido";
                return;
            }
            RegisteredUser user = searchByEmail(email);
            if (user == null) {
                JOptionPane.showMessageDialog(CredentialRecoveryView,
                    "No existe ningún usuario asociado a ese correo electrónico",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "No existe ningún usuario asociado a ese correo electrónico";
                return;
            }
            if (sendEmail(email, user.getUser(), user.getPassword())) {
                JOptionPane.showMessageDialog(CredentialRecoveryView,
                    "Se ha enviado un correo con sus credenciales",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                lastMessage = "Se ha enviado un correo con sus credenciales";
                CredentialRecoveryView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(CredentialRecoveryView,
                    "Error al enviar el correo electrónico",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "Error al enviar el correo electrónico";
            }
        }
    }

    private RegisteredUser searchByEmail(String email) {
        for (int i = 0; i < validUserModel.validUsers.length; i++) {
            RegisteredUser u = validUserModel.validUsers[i];
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    private boolean sendEmail(String toEmail, String user, String password) {
        String from = "victoriaruza@gmail.com"; // Cambia esto por tu correo
        String appPassword = "fold pfjd uqrg oxca"; // Cambia esto por tu contraseña de aplicación

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(from, appPassword);
                }
            });

        try {
            javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
            message.setFrom(new javax.mail.internet.InternetAddress(from));
            message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(toEmail));
            message.setSubject("Recuperación de Credenciales");
            message.setText("Estimado usuario,\n\n"
                         + "Su usuario es: " + user + "\n"
                         + "Su contraseña es: " + password + "\n\n"
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
