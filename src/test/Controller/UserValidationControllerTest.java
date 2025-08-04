package test.Controller;

import controllers.UserValidationController;
import models.RegisteredUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.UserValidationView;


import static org.junit.jupiter.api.Assertions.*;

public class UserValidationControllerTest {
    private UserValidationView userValidationView;
    private RegisteredUser adminUser;
    private UserValidationController controller;

    @BeforeEach
    void setUp() {
        userValidationView = new UserValidationView();
        adminUser = new RegisteredUser("admin", "admin", "Administrador", 1000.0, 1, "admin@correo.com");
        controller = new UserValidationController(userValidationView, adminUser);
    }

    @Test
    void testEmptyUserFieldShowsError() {
        userValidationView.userField.setText("");
        userValidationView.sendButton.doClick();
        assertEquals("Campo de usuario vacío. Por favor, ingrese un usuario.", controller.lastMessage);
    }

    @Test
    void testValidUserShowsPayView() {
        userValidationView.userField.setText("Ray");
        userValidationView.sendButton.doClick();
        assertEquals("Usuario válido.", controller.lastMessage);
        assertFalse(userValidationView.isVisible());
    }

    @Test
    void testInvalidUserShowsError() {
        userValidationView.userField.setText("noexiste");
        userValidationView.sendButton.doClick();
        assertEquals("Usuario inválido. Por favor, intente nuevamente.", controller.lastMessage);
    }

    @Test
    void testCancelButtonDisposesView() {
        userValidationView.setVisible(true);
        userValidationView.cancelButton.doClick();
        assertFalse(userValidationView.isVisible());
    }
}
