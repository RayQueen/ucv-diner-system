package test.Controller;

import controllers.CostosController;
import models.UsuarioRegistrado;
import view.CostosView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CostosControllerTest {
    private CostosView mockView;
    private CostosController controller;
    private UsuarioRegistrado mockUsuario;

    @BeforeEach
    public void setUp() {
        mockView = new CostosView();
        mockUsuario = new UsuarioRegistrado("admin", "admin123", "Nombre Apellido", 0.0, true, "correo@valido.com");
        controller = new CostosController(mockView, mockUsuario);
    }

    @Test
    public void testGuardarCostosCamposVacios() {
        mockView.campoCostoFijo.setText("");
        mockView.campoCostoVariable.setText("");

        mockView.botonGuardar.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.ultimoMensaje);
    }

    @Test
    public void testGuardarCostosFijoVacio() {
        mockView.campoCostoFijo.setText("");
        mockView.campoCostoVariable.setText("12345");

        mockView.botonGuardar.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.ultimoMensaje);
    }

    @Test
    public void testGuardarCostosVariableVacio() {
        mockView.campoCostoFijo.setText("12345");
        mockView.campoCostoVariable.setText("");

        mockView.botonGuardar.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.ultimoMensaje);
    }

    @Test
    public void testGuardarCostosValoresInvalidos() {
        mockView.campoCostoFijo.setText("abc");
        mockView.campoCostoVariable.setText("xyz");

        mockView.botonGuardar.doClick();

        assertEquals("Por favor complete todos los campos obligatorios", controller.ultimoMensaje);
    }

    @Test
    public void testGuardarCostosValoresNegativos() {
        mockView.campoCostoFijo.setText("-100");
        mockView.campoCostoVariable.setText("-200");

        mockView.botonGuardar.doClick();

        assertEquals("Los costos no pueden ser negativos.", controller.ultimoMensaje);
    }
}
