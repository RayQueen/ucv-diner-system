package test.Controller;

import controllers.RegisterController;
import view.RegisterView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest {
    private RegisterView mockView;
    private RegisterController controller;

    @BeforeEach
    public void setUp() {
        mockView = new RegisterView();
        controller = new RegisterController(mockView, "12345678");
    }
    
    @Test
    public void testRegistroCamposVacios() {
        mockView.firstNameField.setText("");
        mockView.lastNameField.setText("");
        mockView.emailField.setText("");
        mockView.usernameField.setText("");
        mockView.passwordField.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));
        
        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testRegistroNombreApellidoInvalidos() {
        mockView.firstNameField.setText("123");
        mockView.lastNameField.setText("456");
        mockView.emailField.setText("correo@gmail.com.com");
        mockView.usernameField.setText("usuario");
        mockView.passwordField.setText("Cl@ve12345");        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));

        assertEquals("El nombre y el apellido deben contener solo letras", controller.lastMessage);
    }

    @Test
    public void testRegistroCorreoInvalido() {
        mockView.firstNameField.setText("Nombre");
        mockView.lastNameField.setText("Apellido");
        mockView.emailField.setText("correo_invalido");
        mockView.usernameField.setText("usuario");
        mockView.passwordField.setText("Cl@ve12345");        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));

        assertEquals("El correo no es válido", controller.lastMessage);
    }

    @Test
    public void testRegistroCorreoDireccionInvalida() {
        mockView.firstNameField.setText("Nombre");
        mockView.lastNameField.setText("Apellido");
        mockView.emailField.setText("correo@invalido.com");
        mockView.usernameField.setText("user12345");
        mockView.passwordField.setText("Cl@ve12345");        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));

        assertEquals("El correo no es válido", controller.lastMessage);
    }

    @Test
    public void testRegistroUsuarioExistente() {
        mockView.firstNameField.setText("Jhon");
        mockView.lastNameField.setText("Doe");
        mockView.emailField.setText("JhonDoe@gmail.com");
        mockView.usernameField.setText("usuario");
        mockView.passwordField.setText("Cl@ve12345");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));

        assertEquals("El nombre de usuario ya está registrado", controller.lastMessage);
    }

    @Test
    public void testRegistroCorreoExistente() {
        mockView.firstNameField.setText("Jane");
        mockView.lastNameField.setText("Doe");
        mockView.emailField.setText("correo2@gmail.com");
        mockView.usernameField.setText("JNDoe");
        mockView.passwordField.setText("Cl@ve12345");     
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));

        assertEquals("El correo electrónico ya está registrado", controller.lastMessage);
    }
    
    @Test
    public void testRegistroExitoso() {
        mockView.firstNameField.setText("Nombre");
        mockView.lastNameField.setText("Apellido");
        mockView.emailField.setText("NombreApellido123@gmail.com");
        mockView.usernameField.setText("user123");
        mockView.passwordField.setText("Cl@ve12345");
        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registerButton, 0, ""));

        assertEquals("Registro completado exitosamente", controller.lastMessage);
    }
}
