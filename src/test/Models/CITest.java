package test.Models;

import models.CI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CITest {
    private CI ci;

    @BeforeEach
    public void setUp() {
        ci = new CI("src/models/CI_Validas.txt");
    }

    @Test
    public void testValidarCIVacio() {
        assertFalse(ci.validarCI(""));
    }

    @Test
    public void testValidarCIConLetras() {
        assertFalse(ci.validarCI("Nombre"));
    }

    @Test
    public void testValidarCIConEspacios() {
        assertFalse(ci.validarCI("123 45678"));
    }

    @Test
    public void testValidarCIConNumeros() {
        assertTrue(ci.validarCI("12345678"));
    }
}

