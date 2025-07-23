package test.Controller;

import controllers.LogInController;
import models.ValidUsers;
import view.LogInView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LogInControllerTest {
    public LogInView mockView;
    public ValidUsers mockUsuarioModel;
    public LogInController controller;

    @BeforeEach
    public void setUp() {
        mockView = new LogInView();
        mockUsuarioModel = new ValidUsers("src/models/validUsers.txt");
        controller = new LogInController(mockView, mockUsuarioModel);
    }

    @Test
    public void testIniciarSesionCamposVacios() {
        mockView.userField.setText("");
        mockView.passwordField.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.logInButton, 0, ""));

        assertEquals("Usuario y contraseña son obligatorios", controller.lastMessage);
    }

    @Test
    public void testIniciarSesionUsuarioVacio() {
        mockView.userField.setText("");
        mockView.passwordField.setText("Cl@ve123");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.logInButton, 0, ""));

        assertEquals("Usuario y contraseña son obligatorios", controller.lastMessage);
    }
@Test
    public void testIniciarSesionContrasenaVacia() {
        mockView.userField.setText("usuario");
        mockView.passwordField.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.logInButton, 0, ""));

        assertEquals("Usuario y contraseña son obligatorios", controller.lastMessage);
    }
    

    @Test
    public void testIniciarSesionCorrecto() {
        mockView.userField.setText("usuario");
        mockView.passwordField.setText("Cl@ve123");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.logInButton, 0, ""));

        assertEquals("Acceso exitoso", controller.lastMessage);
    }

    @Test
    public void testIniciarSesionIncorrecto() {
        mockView.userField.setText("usuario");
        mockView.passwordField.setText("Cl@ve123");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.logInButton, 0, ""));

        assertEquals("Usuario o contraseña incorrectos", controller.lastMessage);
    }
}
