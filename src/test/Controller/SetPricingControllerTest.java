package test.Controller;

import controllers.SetPricingController;
import models.RegisteredUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.SetPricingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetPricingControllerTest {
    private SetPricingView setPricingView;
    private RegisteredUser registeredUser;
    private SetPricingController setPricingController;

    @BeforeEach
    void setUp() {
        setPricingView = new SetPricingView();
        registeredUser = new RegisteredUser("user", "user", "Usuario", 1000.0, 0, "correouser@gmail.com");
        setPricingController = new SetPricingController(setPricingView, registeredUser);
    }

    @Test
    void testHomeButtonDisposesView() {
        setPricingView.setVisible(true);
        for (ActionListener al : setPricingView.homeButton.getActionListeners()) {
            al.actionPerformed(new ActionEvent(setPricingView.homeButton, ActionEvent.ACTION_PERFORMED, null));
        }
        assertEquals(false, setPricingView.isVisible());
    }

    @Test
    void testSavePricingSuccessStudentRate() {
        setPricingView.userType = 0;
        setPricingView.rateField.setText("25.0");
        setPricingController.savePricing();
        assertEquals(false, setPricingView.isVisible());
    }

    @Test
    void testSavePricingEmptyRateShowsError() {
        setPricingView.rateField.setText("");
        setPricingController.savePricing();
    }

    @Test
    void testSavePricingStudentRateOutOfRangeShowsError() {
        setPricingView.userType = 0;
        setPricingView.rateField.setText("19.9"); // Demasiado bajo
        setPricingController.savePricing();
    }
}