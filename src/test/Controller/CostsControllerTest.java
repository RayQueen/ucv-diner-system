package test.Controller;

import controllers.CostsController;
import models.RegisteredUser;
import view.CostsView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CostsControllerTest {
    private CostsView view;
    private CostsController controller;
    private RegisteredUser usuario;

    @BeforeEach
    public void setUp() {
        view = new CostsView();
        usuario = new RegisteredUser("admin", "admin123", "Nombre Apellido", 0.0, 3, "correo@valido.com");
        controller = new CostsController(view, usuario);
    }

    @Test
    public void testGuardarCostosCamposVacios() {
        view.fixedCostField.setText("");
        view.variableCostField.setText("");
        view.saveButton.doClick();
        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosFijoVacio() {
        view.fixedCostField.setText("");
        view.variableCostField.setText("12345");
        view.saveButton.doClick();
        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosVariableVacio() {
        view.fixedCostField.setText("12345");
        view.variableCostField.setText("");
        view.saveButton.doClick();
        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosValoresInvalidos() {
        view.fixedCostField.setText("abc");
        view.variableCostField.setText("xyz");
        view.saveButton.doClick();
        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }

    @Test
    public void testGuardarCostosValoresNegativos() {
        view.fixedCostField.setText("-100");
        view.variableCostField.setText("-200");
        view.saveButton.doClick();
        assertEquals("Por favor complete todos los campos obligatorios", controller.lastMessage);
    }
}
