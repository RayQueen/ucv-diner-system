package test.Controller;

import view.PayView;
import controllers.PayController;
import models.RegisteredUser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PayControllerTest {
    private PayView payView;
    private RegisteredUser registeredUser;
    private RegisteredUser registeredAdmin;
    private PayController controller;

    @BeforeEach
    void setUp() {
        payView = new PayView();
        registeredUser = new RegisteredUser("Ray", "user", "Usuario", 1000.0, 0, "correouser@gmail.com");
        registeredAdmin = new RegisteredUser("admin", "admin", "Administrador", 0.0, 3, "correoadmin@gmail.com");
        controller = new PayController(payView, registeredUser, registeredAdmin);
    }

    @Test
    void testPagarSaldoInsuficiente() {
        payView.priceValueLabel.setText("Bs. 2000.0");
        payView.imagePathField.setText("src/models/data/images/Ray.png");
        payView.payButton.doClick();
        assertEquals(1000.0, registeredUser.getBalance());
        assertEquals("Saldo insuficiente para realizar el pago.", controller.lastMessage);
    }

    @Test
    void testPayButtonNoImageProvided() {
        payView.priceValueLabel.setText("Bs. 10.0");
        payView.imagePathField.setText("");
        payView.payButton.doClick();
        assertEquals(1000.0, registeredUser.getBalance());
        assertEquals("Debe subir una imagen para el reconocimiento facial.", controller.lastMessage);
    }

    @Test
    void testPayButtonStoredImageNotFound() throws Exception {
        RegisteredUser user = new RegisteredUser("null", "null", "null", 1000.0, 0, "null");
        controller = new PayController(payView, user, registeredAdmin);
        payView.priceValueLabel.setText("Bs. 10.0");
        payView.imagePathField.setText("/ruta/a/imagen.png");
        payView.payButton.doClick();
        assertEquals(1000.0, registeredUser.getBalance());
        assertEquals("No se encontró la imagen almacenada del usuario.", controller.lastMessage);
    }

    @Test
    void testPayButtonFacialRecognitionFailed() throws Exception {
        payView.priceValueLabel.setText("Bs. 10.0");
        payView.imagePathField.setText("/ruta/a/imagen.png");
        payView.payButton.doClick();
        assertEquals(1000.0, registeredUser.getBalance());
        assertEquals("Reconocimiento facial fallido.", controller.lastMessage);
    }

    @Test
    void testPayButtonSuccessfulPayment() throws Exception {
        payView.priceValueLabel.setText("Bs. 10.0");
        payView.imagePathField.setText("src/models/data/images/Ray.png");
        payView.payButton.doClick();
        assertEquals(990.0, registeredUser.getBalance());
        assertEquals("Pago realizado con éxito.", controller.lastMessage);
    }

    @Test
    void testCancelButtonDisposesView() {
        payView.cancelButton.doClick();
        assertFalse(payView.isVisible());
    }
}