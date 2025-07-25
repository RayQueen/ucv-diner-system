package controllers;

import view.CostsView;
import view.LogInView;

import models.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CostsController {
    private CostsView costsView;
    private RegisteredUser registeredUser;
    public String lastMessage;

    public CostsController(CostsView costsView, RegisteredUser registeredUser) {
    this.costsView = costsView;
    this.registeredUser = registeredUser;
    this.costsView.saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveCosts();
        }
    });
    this.costsView.cancelButton.addActionListener(e -> {
        costsView.dispose();
        view.AdminFeedView adminView = new view.AdminFeedView();
        adminView.updateUser(registeredUser.getFullName());
        new controllers.AdminFeedController(adminView, registeredUser);
        adminView.setVisible(true);
    });
    this.costsView.homeButton.addActionListener(e -> {
        costsView.dispose();
        view.AdminFeedView adminView = new view.AdminFeedView();
        adminView.updateUser(registeredUser.getFullName());
        new controllers.AdminFeedController(adminView, registeredUser);
        adminView.setVisible(true);
    });
    this.costsView.logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costsView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
    });
    this.costsView.fixedCostField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costsView.variableCostField.requestFocus();
            }
    });
    this.costsView.variableCostField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costsView.plateNumberField.requestFocus();
            }
    });
    this.costsView.plateNumberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costsView.shrinkageField.requestFocus();
            }
    });
    this.costsView.shrinkageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                costsView.saveButton.doClick();
            }
    });
}

    private void saveCosts() {
        String fixedCost = costsView.fixedCostField.getText();
        String variableCost = costsView.variableCostField.getText();
        if (fixedCost.isEmpty() || variableCost.isEmpty()) {
            lastMessage = "Por favor complete todos los campos obligatorios";
            JOptionPane.showMessageDialog(costsView, "Por favor complete todos los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double fixedCostValue = Double.parseDouble(fixedCost);
            double variableCostValue = Double.parseDouble(variableCost);
            if (fixedCostValue < 0 || variableCostValue < 0) {
                lastMessage = "Los costos no pueden ser negativos.";
                JOptionPane.showMessageDialog(costsView, "Los costos no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (java.io.FileWriter writer = new java.io.FileWriter("src/models/costs.txt", false)) {
                writer.write(fixedCostValue + "\n");
                writer.write(variableCostValue + "\n");
                lastMessage = "Costos guardados correctamente.";
                JOptionPane.showMessageDialog(costsView,
                        "Datos guardados correctamente",
                        "Guardado Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                costsView.dispose();
                view.AdminFeedView adminFeedView = new view.AdminFeedView();
                adminFeedView.updateUser(registeredUser.getFullName());
                new controllers.AdminFeedController(adminFeedView, registeredUser);
                adminFeedView.setVisible(true);
                adminFeedView.setBounds(0, 0, 800, 600);
                adminFeedView.setResizable(false);
                adminFeedView.setLocationRelativeTo(null);
            } catch (java.io.IOException ex) {
                lastMessage = "Ocurrió un error al guardar el archivo.";
                JOptionPane.showMessageDialog(costsView,
                        "Ocurrió un error al guardar el archivo.",
                        "Error de Guardado",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            lastMessage = "Ingrese valores numéricos válidos.";
            JOptionPane.showMessageDialog(costsView, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
