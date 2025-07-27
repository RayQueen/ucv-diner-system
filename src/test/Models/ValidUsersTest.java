package test.Models;

import models.ValidUsers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ValidUsersTest {
    private ValidUsers validUsers;

    @BeforeEach
    public void setUp() {
        validUsers = new ValidUsers();
    }

    @Test
    public void testValidarUsuarioIncorrecto() {
        assertFalse(validUsers.isUserValid(null, null));
    }

    @Test
    public void testValidarUsuarioCorrecto() {
        assertTrue(validUsers.isUserValid("user123", "Cl@ve12345"));
    }
}
