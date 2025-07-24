package controllers;

import models.ValidUsers;
import models.RegisteredUser;
import models.ValidRegisters;

import view.LogInView;
import view.CredentialRecoveryView;
import view.IDValidationView;
import view.FeedView;

import javax.swing.*;
import java.awt.event.*;

public class LogInController implements ActionListener {
    private LogInView logInView;
    private ValidUsers validUsersModel = new ValidUsers("src/models/validUsers.txt");
    private ValidRegisters identificationModel = new ValidRegisters("src/models/validRegisters.txt");
    public String lastMessage;

    public LogInController(LogInView logInView, ValidUsers validUsersModel) {
        this.logInView = logInView;
        this.validUsersModel = validUsersModel;
        this.logInView.logInButton.addActionListener(this);
        this.logInView.forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                logInView.dispose();
                CredentialRecoveryView passwordRecoveryView = new CredentialRecoveryView();
                new CredentialRecoveryController(passwordRecoveryView);
                passwordRecoveryView.setVisible(true);
            }
        });
        this.logInView.userField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInView.passwordField.requestFocus();
            }
        });
        this.logInView.passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logInView.logInButton.doClick();
            }
        });
        this.logInView.registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logInView.dispose();
                IDValidationView IDValidationView = new IDValidationView();
                new IDValidationController(IDValidationView, identificationModel);
                IDValidationView.setVisible(true);                
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logInView.logInButton) {
            String logInUser = logInView.userField.getText().trim();
            String logInPassword = new String(logInView.passwordField.getPassword()).trim();

            if (logInUser.isEmpty() || logInPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Usuario y contraseña son obligatorios",
                    "Error", JOptionPane.WARNING_MESSAGE);
                lastMessage = "Usuario y contraseña son obligatorios";
                return;
            }

            boolean correctCredentials = validUsersModel.isUserValid(logInUser, logInPassword);
            if (correctCredentials) {
                RegisteredUser userField = validUsersModel.findRegisteredUser(logInUser);
                lastMessage = "Acceso exitoso";
                logInView.dispose();
                if (userField.isAdmin()) {
                    view.AdminFeedView adminView = new view.AdminFeedView();
                    adminView.updateUser(userField.getFullName());
                    new controllers.AdminFeedController(adminView, userField);
                    adminView.setVisible(true);
                } else {
                    FeedView userView = new FeedView();
                    new FeedController(userView, userField);
                    userView.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                    "Usuario o contraseña incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "Usuario o contraseña incorrectos";
            }
        }
    }
}
