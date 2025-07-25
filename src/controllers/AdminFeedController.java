package controllers;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.RegisteredUser;

public class AdminFeedController {
    private AdminFeedView adminFeedView;
    private RegisteredUser registeredUser;

    public AdminFeedController(AdminFeedView adminFeedView, RegisteredUser usuario) {
        this.adminFeedView = adminFeedView;
        this.registeredUser = usuario;
        this.adminFeedView.updateUser(usuario.getFullName());
        this.adminFeedView.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFeedView.dispose();
                view.AdminFeedView adminView = new view.AdminFeedView();
                adminView.updateUser(registeredUser.getFullName());
                new controllers.AdminFeedController(adminView, registeredUser);
                adminView.setVisible(true);
            }
        });
        this.adminFeedView.logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFeedView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
        });
        this.adminFeedView.calculateCCBButton.addActionListener(e -> {
            adminFeedView.dispose();
            CostsView costsView = new CostsView();
            new controllers.CostsController(costsView, registeredUser);
            costsView.setVisible(true);
        });
        this.adminFeedView.menuManagementButton.addActionListener(e -> {
            adminFeedView.dispose();
            MenuManagementView menusView = new MenuManagementView();
            new MenuManagementController(menusView, registeredUser);
            menusView.setVisible(true);
        });
    }
}
