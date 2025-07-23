package test.Controller;

import controllers.IDValidationController;
import view.IDValidationView;
import models.ValidRegisters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class IDValidationControllerTest {
    private IDValidationView mockView;
    private ValidRegisters mockModel;
    private IDValidationController controller;


    @BeforeEach
    void setUp() {
        mockView = new IDValidationView();
        mockModel = new ValidRegisters("src/models/validRegisters.txt");
        controller = new IDValidationController(mockView, mockModel);
    }

    @Test
    void testValidarCICampoVacio() {
        mockView.IDField.setText("");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));
        assertEquals("Campo de cédula vacío", controller.lastMessage);
    }

    @Test
    void testValidarCILetras() {
        mockView.IDField.setText("abcd");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));
        assertEquals("Campo de cédula vacío", controller.lastMessage);
    }

    @Test
    void testValidarCICorrecto() {
        mockView.IDField.setText("12345678");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));
        assertEquals("CI válida. Proceda al registro.", controller.lastMessage);
        assertFalse(mockView.isVisible());
    }

    @Test
    void testValidarCIIncorrecto() {
        mockView.IDField.setText("123456789");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.sendButton, 0, ""));
        assertEquals("No estás autorizado para registrarte. Contácta a Secretaría", controller.lastMessage);
    }
}