package controllers;

import view.MenuManagementView;
import view.LogInView;

import models.RegisteredUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuManagementController implements ActionListener {
    private MenuManagementView menuView;
    private RegisteredUser registeredUser;
    public String lastMessage;

    @Override
    public void actionPerformed(ActionEvent e) {
        // No default action, this controller uses specific listeners for each button.
    }

    public MenuManagementController(MenuManagementView menuView, RegisteredUser registeredUser) {
        this.menuView = menuView;
        this.registeredUser = registeredUser;
        this.menuView.homeButton.addActionListener(e -> {
            menuView.dispose();
            view.AdminFeedView adminView = new view.AdminFeedView();
            adminView.updateUser(registeredUser.getFullName());
            new controllers.AdminFeedController(adminView, registeredUser);
            adminView.setVisible(true);
        });
        this.menuView.logOutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuView.dispose();
                    LogInView logInView = new LogInView();
                    models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                    new controllers.LogInController(logInView, validUsersModel);
                    logInView.setVisible(true);
                }
        });
        this.menuView.cancelButton.addActionListener(e -> {
            menuView.dispose();
            view.AdminFeedView adminView = new view.AdminFeedView();
            adminView.updateUser(registeredUser.getFullName());
            new controllers.AdminFeedController(adminView, registeredUser);
            adminView.setVisible(true);
        });
        this.menuView.saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveMenu();
        }
        });
        this.menuView.soupField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuView.dryField.requestFocus();
            }
        });
        this.menuView.dryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuView.juiceField.requestFocus();
            }
        });
        this.menuView.juiceField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuView.dessertField.requestFocus();
            }
        });
        this.menuView.dessertField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuView.saveButton.doClick();
            }
        });
    }

    private void saveMenu() {
        String soup = menuView.soupField.getText();
        String dry = menuView.dryField.getText();
        String juice = menuView.juiceField.getText();
        String dessert = menuView.dessertField.getText();

        if (soup.isEmpty() || dry.isEmpty() || juice.isEmpty() || dessert.isEmpty()) {
            JOptionPane.showMessageDialog(menuView, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            lastMessage = "Por favor, complete todos los campos.";
            return;
        } else {
            String turn = "";
            if (menuView.turn) {
                turn = "Desayuno (7:00 AM - 9:00 AM)";
            } else {
                turn = "Almuerzo (12:00 PM - 2:00 PM)";
            }
            String line = turn + "," + soup + "," + dry + "," + juice + "," + dessert + "\n";
            try (java.io.FileWriter fw = new java.io.FileWriter("src/models/menu.txt", true)) {
                fw.write(line);
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(menuView,
                    "Error al guardar el menu: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                lastMessage = "Error al guardar el menu";
                return;
            }
            JOptionPane.showMessageDialog(menuView,
                "Menú guardado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            lastMessage = "Menú guardado con éxito.";
            menuView.dispose();
            view.AdminFeedView adminView = new view.AdminFeedView();
            adminView.updateUser(registeredUser.getFullName());
            new controllers.AdminFeedController(adminView, registeredUser);
            adminView.setVisible(true);
        }
    }
}