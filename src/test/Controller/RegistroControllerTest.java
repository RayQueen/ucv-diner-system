package test.Controller;

import controllers.RegistroController;
import view.RegistroView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RegistroControllerTest {
    private RegistroView mockView;
    private RegistroController controller;

    @BeforeEach
    public void setUp() {
        mockView = new RegistroView();
        controller = new RegistroController(mockView);
    }
    
    @Test
    public void testRegistroCamposVacios() {
        mockView.nombre.setText("");
        mockView.apellido.setText("");
        mockView.correo.setText("");
        mockView.telefono.setText("");
        mockView.ocupacion.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));
        
        assertEquals("Por favor complete todos los campos obligatorios", controller.ultimoMensaje);
    }


    @Test
    public void testRegistroNombreApellidoInvalidos() {
        mockView.nombre.setText("123");
        mockView.apellido.setText("456");
        mockView.correo.setText("correo@valido.com");
        mockView.telefono.setText("1234567890");
        mockView.ocupacion.setText("Ocupacion");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El nombre y el apellido deben contener solo letras", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroCorreoInvalido() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("correo_invalido");
        mockView.telefono.setText("1234567890");
        mockView.ocupacion.setText("Ocupacion");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El correo electrónico no es válido", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroTelefonoInvalido() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("correo@valido.com");
        mockView.telefono.setText("telefono_invalido");
        mockView.ocupacion.setText("Ocupacion");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El teléfono debe contener solo números", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroExitoso() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("correo@valido.com");
        mockView.telefono.setText("1234567890");
        mockView.ocupacion.setText("Ocupacion");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("Registro completado exitosamente", controller.ultimoMensaje);
    }
}
