package test.Models;

import models.ValidRegisters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ValidRegistersTest {
    private ValidRegisters ci;

    @BeforeEach
    public void setUp() {
        ci = new ValidRegisters();
    }

    @Test
    public void testValidarCIVacio() {
        assertFalse(ci.isIDValid(""));
    }

    @Test
    public void testValidarCIConLetras() {
        assertFalse(ci.isIDValid("Nombre"));
    }

    @Test
    public void testValidarCIConEspacios() {
        assertFalse(ci.isIDValid("123 45678"));
    }

    @Test
    public void testValidarCIConNumeros() {
        assertTrue(ci.isIDValid("12345678"));
    }
}

