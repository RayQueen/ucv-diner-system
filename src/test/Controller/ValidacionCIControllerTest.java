package test.Controller;

import controllers.ValidacionCIController;
import view.ValidacionCIView;
import models.CI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ValidacionCIControllerTest {
    private ValidacionCIView mockView;
    private CI mockModel = new CI("src/models/CI_validas.txt");
    private ValidacionCIController controller;


    @BeforeEach
    void setUp() {
        mockView = new ValidacionCIView();
        controller = new ValidacionCIController(mockView, mockModel);

    }

    @Test
    void testValidarCICampoVacio() {
        mockView.cedula.setText("");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));
        assertEquals("Campo de cédula vacío", controller.ultimoMensaje);
    }

    @Test
    void testValidarCICorrecto() {
        mockView.cedula.setText("12345678");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));
        assertEquals("CI válida. Proceda al registro.", controller.ultimoMensaje);
        assertFalse(mockView.isVisible());
    }

    @Test
    void testValidarCIIncorrecto() {
        mockView.cedula.setText("123456789");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.enviar, 0, ""));
        assertEquals("No estás autorizado para registrarte. Contácta a Secretaría", controller.ultimoMensaje);
    }
}