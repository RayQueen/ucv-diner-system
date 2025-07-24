package controllers;

import models.ValidRegisters;

import view.IDValidationView;
import view.LogInView;
import view.RegisterView;

import javax.swing.*;
import java.awt.event.*;

public class IDValidationController implements ActionListener {
    private IDValidationView IDValidationView;
    private ValidRegisters validRegistersModel;
    public String lastMessage;

    public IDValidationController(IDValidationView IDValidationView, ValidRegisters validRegistersModel) {
        this.IDValidationView = IDValidationView;
        this.validRegistersModel = validRegistersModel;
        this.IDValidationView.sendButton.addActionListener(this);
        this.IDValidationView.IDField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDValidationView.sendButton.doClick();
            }
        });
        this.IDValidationView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDValidationView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == IDValidationView.sendButton) {
            String enteredID = IDValidationView.IDField.getText().trim();
            if (enteredID.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Campo de cédula vacío",
                    "Error", JOptionPane.WARNING_MESSAGE);
                    lastMessage = "Campo de cédula vacío";
                return;
            }
            boolean correctCredential = validRegistersModel.isIDValid(enteredID);
            if (correctCredential) {
                lastMessage = "CI válida. Proceda al registro.";
                IDValidationView.dispose();
                RegisterView registerView = new RegisterView();
                new RegisterController(registerView, enteredID);
                registerView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                    "No estás autorizado para registrarte. Contácta a Secretaría",
                    "Error", JOptionPane.ERROR_MESSAGE);
                    lastMessage = "No estás autorizado para registrarte. Contácta a Secretaría";
            }
        }
    }
}
