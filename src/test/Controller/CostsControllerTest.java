package test.Controller;

import controllers.CostsController;
import models.RegisteredUser;
import view.CostsView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CostsControllerTest {
    private CostsView mockView;
    private CostsController controller;
    private RegisteredUser mockUsuario;

    @BeforeEach
    public void setUp() {
        mockView = new CostsView();
        mockUsuario = new RegisteredUser("admin", "admin123", "Nombre Apellido", 0.0, 3, "correo@valido.com");
        controller = new CostsController(mockView, mockUsuario);
    }

    @Test
    public void testGuardarCostosCamposVacios() {
        mockView.fixedCostField.setText("");
        mockView.variableCostField.setText("");

        mockView.saveButton.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosFijoVacio() {
        mockView.fixedCostField.setText("");
        mockView.variableCostField.setText("12345");

        mockView.saveButton.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosVariableVacio() {
        mockView.fixedCostField.setText("12345");
        mockView.variableCostField.setText("");

        mockView.saveButton.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosValoresInvalidos() {
        mockView.fixedCostField.setText("abc");
        mockView.variableCostField.setText("xyz");

        mockView.saveButton.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosValoresNegativos() {
        mockView.fixedCostField.setText("-100");
        mockView.variableCostField.setText("-200");

        mockView.saveButton.doClick();

        assertEquals("Los costos no pueden ser negativos.", controller.lastMessage);
    }
}
