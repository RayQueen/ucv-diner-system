package test.Models;

import models.RegisteredUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisteredUserTest {
    private RegisteredUser usuario;

    @BeforeEach
    public void setUp() {
        usuario = new RegisteredUser("usuario1", "pass123", "Juan Pérez", 100.5, true, "juan@ucv.ve");
    }

    @Test
    public void testGetNombreCompleto() {
        assertEquals("Juan Pérez", usuario.getFullName());
    }

    @Test
    public void testGetSaldo() {
        assertEquals(100.5, usuario.getBalance());
    }

    @Test
    public void testEsAdmin() {
        assertTrue(usuario.isAdmin());
    }

    @Test
    public void testGetUsuario() {
        assertEquals("usuario1", usuario.getUser());
    }

    @Test
    public void testGetContrasena() {
        assertEquals("pass123", usuario.getPassword());
    }

    @Test
    public void testGetCorreo() {
        assertEquals("juan@ucv.ve", usuario.getEmail());
    }
}
