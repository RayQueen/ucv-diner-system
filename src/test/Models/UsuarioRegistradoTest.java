package test.Models;

import models.UsuarioRegistrado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRegistradoTest {
    private UsuarioRegistrado usuario;

    @BeforeEach
    public void setUp() {
        usuario = new UsuarioRegistrado("usuario1", "pass123", "Juan Pérez", 100.5, true, "juan@ucv.ve");
    }

    @Test
    public void testGetNombreCompleto() {
        assertEquals("Juan Pérez", usuario.getNombreCompleto());
    }

    @Test
    public void testGetSaldo() {
        assertEquals(100.5, usuario.getSaldo());
    }

    @Test
    public void testEsAdmin() {
        assertTrue(usuario.esAdmin());
    }

    @Test
    public void testGetUsuario() {
        assertEquals("usuario1", usuario.getUsuario());
    }

    @Test
    public void testGetContrasena() {
        assertEquals("pass123", usuario.getContrasena());
    }

    @Test
    public void testGetCorreo() {
        assertEquals("juan@ucv.ve", usuario.getCorreo());
    }
}
