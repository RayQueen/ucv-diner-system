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
        controller = new RegistroController(mockView, "12345678");
    }
    
    @Test
    public void testRegistroCamposVacios() {
        mockView.nombre.setText("");
        mockView.apellido.setText("");
        mockView.correo.setText("");
        mockView.usuario.setText("");
        mockView.contrasena.setText("");
        mockView.rol.setSelectedItem("estudiante");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));
        
        assertEquals("Por favor complete todos los campos obligatorios", controller.ultimoMensaje);
    }


    @Test
    public void testRegistroNombreApellidoInvalidos() {
        mockView.nombre.setText("123");
        mockView.apellido.setText("456");
        mockView.correo.setText("correo@gmail.com.com");
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("profesor");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El nombre y el apellido deben contener solo letras", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroCorreoInvalido() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("correo_invalido");
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("empleado");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El correo electrónico no es válido", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroCorreoDireccionInvalida() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("correo@invalido.com");
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("empleado");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El correo debe terminar en @gmail.com, @ucv.ve o @ucv.<facultad>.ve", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroTelefonoInvalido() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("correo@gmail.com");
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("admin");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El teléfono debe contener solo números", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroExitoso() {
        mockView.nombre.setText("Nombre");
        mockView.apellido.setText("Apellido");
        mockView.correo.setText("NombreApellido123@gmail.com");
        mockView.usuario.setText("NA123");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("estudiante");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("Registro completado exitosamente", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroUsuarioExistente() {
        mockView.nombre.setText("Jhon");
        mockView.apellido.setText("Doe");
        mockView.correo.setText("JhonDoe@gmail.com");
        mockView.usuario.setText("usuario");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("admin");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El nombre de usuario ya está registrado", controller.ultimoMensaje);
    }

    @Test
    public void testRegistroCorreoExistente() {
        mockView.nombre.setText("Jane");
        mockView.apellido.setText("Doe");
        mockView.correo.setText("correo@gmail.com");
        mockView.usuario.setText("JNDoe");
        mockView.contrasena.setText("clave");
        mockView.rol.setSelectedItem("admin");

        controller.actionPerformed(new java.awt.event.ActionEvent(mockView.registrarse, 0, ""));

        assertEquals("El correo electrónico ya está registrado", controller.ultimoMensaje);
    }
}
