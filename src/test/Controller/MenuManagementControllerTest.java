package test.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.MenuManagementController;
import models.RegisteredUser;
import view.MenuManagementView;

public class MenuManagementControllerTest {
    public MenuManagementView mockView;
    private MenuManagementController controller;
    private RegisteredUser mockUsuario;

    @BeforeEach
    public void setUp() {
        mockView = new MenuManagementView();
        mockUsuario = new RegisteredUser("admin", "admin123", "Nombre Apellido", 0.0, 3, "correo@valido.com");
        controller = new MenuManagementController(mockView, mockUsuario);
    }

    @Test
    public void testGuardarMenuCamposVacios() {
        mockView.soupField.setText("");
        mockView.dryField.setText("");
        mockView.drinkField.setText("");
        mockView.dessertField.setText("");

        mockView.saveButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarMenuSopaVacio() {
        mockView.soupField.setText("");
        mockView.dryField.setText("Sandwich de jamón y queso");
        mockView.drinkField.setText("Toddy");
        mockView.dessertField.setText("Galleta María");

        mockView.saveButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarMenuSecoVacio() {
        mockView.soupField.setText(" - ");
        mockView.dryField.setText("");
        mockView.drinkField.setText("Toddy");
        mockView.dessertField.setText("Galleta María");

        mockView.saveButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarMenuBebidaVacio() {
        mockView.soupField.setText(" - ");
        mockView.dryField.setText("Sandwich de jamón y queso");
        mockView.drinkField.setText("");
        mockView.dessertField.setText("Galleta María");

        mockView.saveButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarMenuPostreVacio() {
        mockView.soupField.setText(" - ");
        mockView.dryField.setText("Sandwich de jamón y queso");
        mockView.drinkField.setText("Toddy");
        mockView.dessertField.setText("");

        mockView.saveButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarMenuCorrecto() {
        mockView.soupField.setText(" - ");
        mockView.dryField.setText("Sandwich de jamón y queso");
        mockView.drinkField.setText("Toddy");
        mockView.dessertField.setText("Galleta María");

        mockView.saveButton.doClick();

        assertEquals("Menú guardado con éxito.", controller.lastMessage);
    }
}
