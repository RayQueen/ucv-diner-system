package controllers;

import view.FeedView;
import view.ConsultMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.RegisteredUser;

public class FeedController {
    private FeedView feedView;
    public FeedController(FeedView feedView, RegisteredUser registeredUser) {
        this.feedView = feedView;
        this.feedView.updateUser(registeredUser);
        this.feedView.menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.sidePanel.setVisible(!feedView.sidePanel.isVisible());
                feedView.revalidate();
                feedView.repaint();
            }
        });
        this.feedView.consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedView.dispose();
                ConsultMenuView consultMenuView = new ConsultMenuView();
                new ConsultMenuController(consultMenuView, registeredUser);
                consultMenuView.setVisible(true);
                consultMenuView.setBounds(0, 0, 500, 600);
                consultMenuView.setVisible(true);
                consultMenuView.setResizable(false);
                consultMenuView.setLocationRelativeTo(null);
            }
        });
    }
    
}
