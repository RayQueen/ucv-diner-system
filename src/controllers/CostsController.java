package controllers;

import view.CostsView;
import view.LogInView;

import models.RegisteredUser;
import models.Pricing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CostsController {
    private CostsView costsView;
    private RegisteredUser registeredUser;
    private Pricing pricingModel;
    public String lastMessage;

    public CostsController(CostsView costsView, RegisteredUser registeredUser) {
    this.costsView = costsView;
    this.registeredUser = registeredUser;
    this.pricingModel = new Pricing();
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
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/data/validUsers.txt");
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
        String plateNumber = costsView.plateNumberField.getText();
        String shrinkage = costsView.shrinkageField.getText();
        if (fixedCost.isEmpty() || variableCost.isEmpty() || plateNumber.isEmpty() || shrinkage.isEmpty()) {
            lastMessage = "Por favor complete todos los campos obligatorios";
            JOptionPane.showMessageDialog(costsView, "Por favor complete todos los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double fixedCostValue = Double.parseDouble(fixedCost);
            double variableCostValue = Double.parseDouble(variableCost);
            int plateNumberValue = Integer.parseInt(plateNumber);
            double shrinkageValue = Double.parseDouble(shrinkage);
            if (fixedCostValue < 0 || variableCostValue < 0 || plateNumberValue < 0 || shrinkageValue < 0) {
                lastMessage = "Los costos no pueden ser negativos.";
                JOptionPane.showMessageDialog(costsView, "Los costos no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (java.io.FileWriter writer = new java.io.FileWriter("src/models/data/costs.txt", false)) {
                pricingModel.updateCostsInFile(fixedCostValue, variableCostValue, plateNumberValue, shrinkageValue);
                costsView.dispose();
                view.AdminFeedView adminFeedView = new view.AdminFeedView();
                adminFeedView.updateUser(registeredUser.getFullName());
                new controllers.AdminFeedController(adminFeedView, registeredUser);
                adminFeedView.setVisible(true);
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
