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
                    models.ValidUsers validUsersModel = new models.ValidUsers();
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
                menuView.drinkField.requestFocus();
            }
        });
        this.menuView.drinkField.addActionListener(new ActionListener() {
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
        String juice = menuView.drinkField.getText();
        String dessert = menuView.dessertField.getText();

        if (soup.isEmpty() || dry.isEmpty() || juice.isEmpty() || dessert.isEmpty()) {
            JOptionPane.showMessageDialog(menuView, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            lastMessage = "Por favor, complete todos los campos.";
            return;
        } else {
            String turn = "";
            int menuType = menuView.turn ? 0 : 1; // 0 for breakfast, 1 for lunch
            if (menuView.turn) {
                turn = "Desayuno (7:00 AM - 9:00 AM)";
            } else {
                turn = "Almuerzo (12:00 PM - 2:00 PM)";
            }
            String newLine = turn + "," + "Sopa: " + soup + "," + "Seco: " + dry + "," + "Bebida: " + juice + "," + "Postre: " + dessert;
            java.util.List<String> lines = new java.util.ArrayList<>();
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(
                        new java.io.FileInputStream("src/models/data/menu.txt"), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(menuView,
                    "Error al leer el archivo de menú: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            while (lines.size() <= menuType) lines.add("");
            lines.set(menuType, newLine);
            saveMenuUTF8(lines);
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

    private void saveMenuUTF8(java.util.List<String> lines) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(
                new java.io.OutputStreamWriter(
                        new java.io.FileOutputStream("src/models/data/menu.txt"), "UTF-8"))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(menuView,
                    "Error al guardar el menu: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            lastMessage = "Error al guardar el menu";
        }
    }
}