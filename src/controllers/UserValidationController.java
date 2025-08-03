package controllers;

import models.RegisteredUser;
import models.ValidUsers;
import view.UserValidationView;
import view.PayView;

import javax.swing.*;
import java.awt.event.*;

public class UserValidationController implements ActionListener {
    private UserValidationView userValidationView;
    private ValidUsers validUsersModel;
    private RegisteredUser registeredAdmin;
    public String lastMessage;

    public UserValidationController(UserValidationView userValidationView, RegisteredUser registeredAdmin) {
        this.userValidationView = userValidationView;
        this.validUsersModel = new ValidUsers();
        this.registeredAdmin = registeredAdmin;
        this.userValidationView.userField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userValidationView.sendButton.doClick();
            }
        });
        this.userValidationView.sendButton.addActionListener(this);
        this.userValidationView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userValidationView.dispose();
                 view.AdminFeedView adminView = new view.AdminFeedView();
                adminView.updateUser(registeredAdmin.getFullName());
                new controllers.AdminFeedController(adminView, registeredAdmin);
                adminView.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userValidationView.sendButton) {
            String enteredUser = userValidationView.userField.getText().trim();
            if (enteredUser.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Campo de usuario vacío. Por favor, ingrese un usuario.",
                    "Error", JOptionPane.WARNING_MESSAGE);
                    lastMessage = "Campo de usuario vacío. Por favor, ingrese un usuario.";
                return;
            }
            RegisteredUser user = validUsersModel.findRegisteredUser(enteredUser);
            if (user != null) {
                lastMessage = "Usuario válido.";
                userValidationView.dispose();
                PayView payView = new PayView();
                payView.updatePrice(user, new models.Pricing());
                payView.updateBalance(user);
                new PayController(payView, user, registeredAdmin);
                payView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Usuario inválido. Por favor, intente nuevamente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                    lastMessage = "Usuario inválido. Por favor, intente nuevamente.";
            }
        }
    }
}
