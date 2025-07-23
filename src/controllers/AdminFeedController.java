package controllers;

import view.CostsView;

import view.AdminFeedView;
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
        this.adminFeedView.menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFeedView.sidePanel.setVisible(!adminFeedView.sidePanel.isVisible());
                adminFeedView.revalidate();
                adminFeedView.repaint();
            }
        });
        this.adminFeedView.calculateCCBButton.addActionListener(e -> {
            adminFeedView.dispose();
            CostsView costsView = new CostsView();
            new controllers.CostsController(costsView, registeredUser);
            costsView.setVisible(true);
            costsView.setBounds(0, 0, 500, 400);
            costsView.setVisible(true);
            costsView.setResizable(false);
            costsView.setLocationRelativeTo(null);
        });
    }
}
