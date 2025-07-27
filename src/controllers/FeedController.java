package controllers;

import view.FeedView;
import view.LogInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.RegisteredUser;

public class FeedController {
    private FeedView feedView;
    public FeedController(FeedView feedView, RegisteredUser registeredUser) {
        this.feedView = feedView;
        this.feedView.updateUser(registeredUser);
        models.Menu menuModel = new models.Menu("src/models/data/menu.txt");
        this.feedView.updateMenu(menuModel);
        this.feedView.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.dispose();
                FeedView userView = new FeedView();
                new FeedController(userView, registeredUser);
                userView.setVisible(true);
            }
        });
        this.feedView.logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.dispose();
                LogInView logInView = new LogInView();
                models.ValidUsers validUsersModel = new models.ValidUsers();
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
        });
        this.feedView.addBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.dispose();
                view.AddBalanceView addBalanceView = new view.AddBalanceView();
                new controllers.AddBalanceController(addBalanceView, registeredUser);
                addBalanceView.setVisible(true);
            }
        });
        this.feedView.payBreakfastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.dispose();
                view.PayView payView = new view.PayView();
                new controllers.PayController(payView, registeredUser);
                payView.setVisible(true);
            }
        });
        this.feedView.payLunchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.dispose();
                view.PayView payView = new view.PayView();
                new controllers.PayController(payView, registeredUser);
                payView.setVisible(true);
            }
        });
    }
    
}
