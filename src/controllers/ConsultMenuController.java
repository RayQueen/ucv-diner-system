package controllers;

import view.ConsultMenuView;
import view.FeedView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultMenuController {
    private ConsultMenuView consultMenuView;
    public ConsultMenuController(ConsultMenuView consultMenuView, models.RegisteredUser registeredUser) {
        this.consultMenuView = consultMenuView;
        this.consultMenuView.updateUser(registeredUser);
        this.consultMenuView.menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultMenuView.sideMenuPanel.setVisible(!consultMenuView.sideMenuPanel.isVisible());
                consultMenuView.revalidate();
                consultMenuView.repaint();
            }
        });
        this.consultMenuView.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultMenuView.dispose();
                FeedView feedView = new FeedView();
                new FeedController(feedView, registeredUser);
                feedView.setVisible(true);
                feedView.setBounds(0, 0, 800, 468);
                feedView.setResizable(false);
                feedView.setLocationRelativeTo(null); 
            }
        });
    }
}
