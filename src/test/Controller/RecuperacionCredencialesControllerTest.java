package test.Controller;

import controllers.RecuperacionCredencialesController;
import view.RecuperacionCredencialesView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RecuperacionCredencialesControllerTest {
    private RecuperacionCredencialesView mockView;
    private RecuperacionCredencialesController controller;

    @BeforeEach
    public void setUp() {
        mockView = new RecuperacionCredencialesView();
        controller = new RecuperacionCredencialesController(mockView);
    }

    @Test
    public void testRecuperacionCredencialesCamposVacios() {
        mockView.correo.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));

        assertEquals("Ingrese su correo", controller.ultimoMensaje);
    }

    @Test
    public void testRecuperacionCredencialesCorreoInvalido() {
        mockView.correo.setText("correo_invalido");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));

        assertEquals("El correo electrónico no es válido", controller.ultimoMensaje);
    }

    @Test
    public void testRecuperacionCredencialesExito() {
        mockView.correo.setText("reinamlaura@gmail.com");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));
    
        assertEquals("Se ha enviado un correo para restablecer su contraseña", controller.ultimoMensaje);
        assertFalse(mockView.isVisible());
    }

    @Test
    public void testRecuperacionCredencialesUsuarioNoEncontrado() {
        mockView.correo.setText("usuario_inexistente@gmail.com");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));

        assertEquals("No existe ningún usuario asociado a ese correo electrónico", controller.ultimoMensaje);
    }
}