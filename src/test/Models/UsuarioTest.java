package test.Models;

import models.Usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("src/models/usuarios.txt");
    }

    @Test
    public void testValidarUsuarioIncorrecto() {
        assertFalse(usuario.validarUsuario(null, null));
    }

    @Test
    public void testValidarUsuarioCorrecto() {
        assertTrue(usuario.validarUsuario("usuario", "contraseña"));
    }
}
