package controllers;

import view.SetPricingView;
import view.LogInView;

import models.RegisteredUser;
import models.Pricing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetPricingController {
    private SetPricingView setPricingView;
    private RegisteredUser registeredUser;
    private Pricing pricingModel;
    public String lastMessage;

    public SetPricingController(SetPricingView setPricingView, RegisteredUser user) {
        this.setPricingView = setPricingView;
        this.registeredUser = user;
        this.pricingModel = new Pricing();
        this.setPricingView.homeButton.addActionListener(e -> {
            setPricingView.dispose();
            view.AdminFeedView adminView = new view.AdminFeedView();
            adminView.updateUser(user.getFullName());
            new controllers.AdminFeedController(adminView, user);
            adminView.setVisible(true);
        });
        this.setPricingView.logOutButton.addActionListener(e -> {
            setPricingView.dispose();
            LogInView logInView = new LogInView();
            models.ValidUsers validUsersModel = new models.ValidUsers();
            new controllers.LogInController(logInView, validUsersModel);
            logInView.setVisible(true);
        });
        this.setPricingView.rateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPricingView.saveButton.doClick();
            }
        });
        this.setPricingView.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePricing();
            }
        });
        this.setPricingView.cancelButton.addActionListener(e -> {
            setPricingView.dispose();
            view.AdminFeedView adminView = new view.AdminFeedView();
            adminView.updateUser(registeredUser.getFullName());
            new controllers.AdminFeedController(adminView, registeredUser);
            adminView.setVisible(true);
        });
    }

    public void savePricing() {
        int index = setPricingView.userType;
        String rateText = setPricingView.rateField.getText();
        if (rateText.isEmpty()) {
            JOptionPane.showMessageDialog(setPricingView, "Por favor, complete la tarifa.", "Error", JOptionPane.ERROR_MESSAGE);
            lastMessage = "Por favor, complete la tarifa.";
            return;
        }
        try {
            double rate = Double.parseDouble(rateText);
            if (index==0 && (rate < 20 || rate > 30)) {
                JOptionPane.showMessageDialog(setPricingView, "La tarifa de estudiantes debe estar entre un 20% y 30%", "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "La tarifa de estudiantes debe estar entre un 20% y 30%";
                return;
            }
            if (index==1 && (rate < 70 || rate > 90)) {
                JOptionPane.showMessageDialog(setPricingView, "La tarifa de profesores debe estar entre un 70% y 90%", "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "La tarifa de profesores debe estar entre un 70% y 90%";
                return;
            }
            if (index==2 && (rate < 90 || rate > 110)) {
                JOptionPane.showMessageDialog(setPricingView, "La tarifa de empleados debe estar entre un 90% y 110%", "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "La tarifa de empleados debe estar entre un 90% y 110%";
                return;
            }
            pricingModel.updateRatesInFile(index, rate);
            JOptionPane.showMessageDialog(setPricingView, "Tarifa guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            lastMessage = "Tarifa guardada exitosamente.";
            setPricingView.dispose();
            view.AdminFeedView adminView = new view.AdminFeedView();
            adminView.updateUser(registeredUser.getFullName());
            new controllers.AdminFeedController(adminView, registeredUser);
            adminView.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(setPricingView, "La tarifa debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }
}
