package controllers;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.RegisteredUser;
import models.Pricing;

public class AdminFeedController {
    private AdminFeedView adminFeedView;
    private RegisteredUser registeredUser;
    private Pricing pricingModel;

    public AdminFeedController(AdminFeedView adminFeedView, RegisteredUser user) {
        this.adminFeedView = adminFeedView;
        this.registeredUser = user;
        this.pricingModel = new Pricing();
        this.adminFeedView.updateUser(user.getFullName());
        this.adminFeedView.updateCosts(pricingModel);
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
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/data/validUsers.txt");
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
        this.adminFeedView.setPricingButton.addActionListener(e -> {
            adminFeedView.dispose();
            SetPricingView setPricingView = new SetPricingView();
            new SetPricingController(setPricingView, registeredUser);
            setPricingView.setVisible(true);
        });
        this.adminFeedView.menuManagementButton.addActionListener(e -> {
            adminFeedView.dispose();
            MenuManagementView menusView = new MenuManagementView();
            new MenuManagementController(menusView, registeredUser);
            menusView.setVisible(true);
        });
    }
}
