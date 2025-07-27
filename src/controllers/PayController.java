package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class PayController {
    private view.PayView payView;
    private models.RegisteredUser registeredUser;
    private models.Pricing pricingModel;
    public String lastMessage;

    public PayController(view.PayView payView, models.RegisteredUser registeredUser) {
        this.payView = payView;
        this.registeredUser = registeredUser;
        this.pricingModel = new models.Pricing();
        this.payView.updateBalance(registeredUser);
        this.payView.updatePrice(registeredUser, pricingModel);

        this.payView.payButton.addActionListener(e -> confirmPayment());
        this.payView.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payView.dispose();
                view.FeedView feedView = new view.FeedView();
                new controllers.FeedController(feedView, registeredUser);
                feedView.setVisible(true);
            }
        });
    }
    
    private void confirmPayment() {
        double totalAmount = Double.parseDouble(this.payView.priceValueLabel.getText().replace("Bs. ", ""));
        if (registeredUser.getBalance() < totalAmount) {
            JOptionPane.showMessageDialog(null,
                    "Saldo insuficiente para realizar el pago.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            lastMessage = "Saldo insuficiente para realizar el pago.";
            return;
        }
        registeredUser.addBalance(-totalAmount);
        payView.updateBalance(registeredUser);
        JOptionPane.showMessageDialog(null,
                "Pago realizado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        lastMessage = "Pago realizado con éxito.";
        payView.dispose();
        view.FeedView feedView = new view.FeedView();
        new controllers.FeedController(feedView, registeredUser);
        feedView.setVisible(true);
    }
}
