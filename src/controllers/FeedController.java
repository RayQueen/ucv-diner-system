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
                models.ValidUsers validUsersModel = new models.ValidUsers("src/models/validUsers.txt");
                new controllers.LogInController(logInView, validUsersModel);
                logInView.setVisible(true);
            }
        });
    }
    
}
