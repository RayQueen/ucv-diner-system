package test.Controller;

import controllers.CredentialRecoveryController;
import view.CredentialRecoveryView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialRecoveryControllerTest {
    private CredentialRecoveryView mockView;
    private CredentialRecoveryController controller;

    @BeforeEach
    public void setUp() {
        mockView = new CredentialRecoveryView();
        controller = new CredentialRecoveryController(mockView);
    }

    @Test
    public void testRecuperacionCredencialesCamposVacios() {
        mockView.emailField.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));

        assertEquals("Ingrese su correo", controller.lastMessage);
    }

    @Test
    public void testRecuperacionCredencialesCorreoInvalido() {
        mockView.emailField.setText("correo_invalido");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));

        assertEquals("El correo electrónico no es válido", controller.lastMessage);
    }

    @Test
    public void testRecuperacionCredencialesExito() {
        mockView.emailField.setText("reinamlaura@gmail.com");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));
    
        assertEquals("Se ha enviado un correo con sus credenciales", controller.lastMessage);
        assertFalse(mockView.isVisible());
    }

    @Test
    public void testRecuperacionCredencialesUsuarioNoEncontrado() {
        mockView.emailField.setText("usuario_inexistente@gmail.com");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));

        assertEquals("No existe ningún usuario asociado a ese correo electrónico", controller.lastMessage);
    }
}