package test.Controller;

import controllers.IniciarSesionController;
import models.Usuario;
import view.IniciarSesionView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class IniciarSesionControllerTest {
    public IniciarSesionView mockView;
    public Usuario mockUsuarioModel;
    public IniciarSesionController controller;

    @BeforeEach
    public void setUp() {
        mockView = new IniciarSesionView();
        mockUsuarioModel = new Usuario("src/models/usuarios.txt");
        controller = new IniciarSesionController(mockView, mockUsuarioModel);
    }

    @Test
    public void testIniciarSesionCamposVacios() {
        mockView.usuario.setText("");
        mockView.contrasena.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.boton1, 0, ""));

        assertEquals("Usuario y contraseña son obligatorios", controller.ultimoMensaje);
    }

    @Test
    public void testIniciarSesionUsuarioVacio() {
        mockView.usuario.setText("");
        mockView.contrasena.setText("contraseña");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.boton1, 0, ""));

        assertEquals("Usuario y contraseña son obligatorios", controller.ultimoMensaje);
    }
@Test
    public void testIniciarSesionContrasenaVacia() {
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.boton1, 0, ""));

        assertEquals("Usuario y contraseña son obligatorios", controller.ultimoMensaje);
    }
    

    @Test
    public void testIniciarSesionCorrecto() {
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("contraseña");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.boton1, 0, ""));

        assertEquals("¡Bienvenido, usuario!", controller.ultimoMensaje);
    }

    @Test
    public void testIniciarSesionIncorrecto() {
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("contraseñaIncorrecta");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.boton1, 0, ""));

        assertEquals("Usuario o contraseña incorrectos", controller.ultimoMensaje);
    }


}
