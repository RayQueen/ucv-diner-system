package test.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.AddBalanceController;
import models.RegisteredUser;
import view.AddBalanceView;

public class AddBalanceControllerTest {
    private AddBalanceView mockView;
    private AddBalanceController controller;
    private RegisteredUser mockUsuario;

    @BeforeEach
    public void setUp() {
        mockView = new AddBalanceView();
        mockUsuario = new RegisteredUser("admin", "admin123", "Nombre Apellido", 0.0, 1, "correo@valido.com");
        controller = new AddBalanceController(mockView, mockUsuario);
    }

    @Test
    public void testGuardarCamposVacios() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("");
        mockView.dateField.setText("");
        mockView.digitsField.setText("");

        mockView.validateButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarTelefonoVacio() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("");
        mockView.dateField.setText("01/01/2025");
        mockView.digitsField.setText("1234");

        mockView.validateButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarFechaVacia() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("1234567");
        mockView.dateField.setText("");
        mockView.digitsField.setText("1234");

        mockView.validateButton.doClick();

        assertEquals("Fecha inválida. Formato debe ser DD/MM/AAAA.", controller.lastMessage);
    }

    
    @Test
    public void testGuardarDigitosVacios() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("1234567");
        mockView.dateField.setText("01/01/2025");
        mockView.digitsField.setText("");

        mockView.validateButton.doClick();

        assertEquals("Por favor, complete todos los campos.", controller.lastMessage);
    }

    @Test
    public void testGuardarTelefonoIncorrecto() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("123");
        mockView.dateField.setText("01/01/2025");
        mockView.digitsField.setText("1234");

        mockView.validateButton.doClick();

        assertEquals("Teléfono inválido. Debe tener 7 dígitos.", controller.lastMessage);
    }

    @Test
    public void testGuardarFechaIncorrecta() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("1234567");
        mockView.dateField.setText("32/13/9999");
        mockView.digitsField.setText("1234");

        mockView.validateButton.doClick();

        assertEquals("Fecha inválida. Formato debe ser DD/MM/AAAA.", controller.lastMessage);
    }

    @Test
    public void testGuardarDigitoIncorrecto() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("1234567");
        mockView.dateField.setText("01/01/2025");
        mockView.digitsField.setText("12345678");

        mockView.validateButton.doClick();

        assertEquals("Los últimos 4 dígitos inválidos.", controller.lastMessage);
    }

    @Test
    public void testGuardarNoAprobado() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("1234567");
        mockView.dateField.setText("01/01/2025");
        mockView.digitsField.setText("1234");

        mockView.validateButton.doClick();

        assertEquals("Por favor verifique los detalles del pago.", controller.lastMessage);
    }

    @Test
    public void testGuardarCorrecto() {
        mockView.bankCombo.setSelectedIndex(0);
        mockView.phoneCodeCombo.setSelectedIndex(0);
        mockView.phoneField.setText("1234567");
        mockView.dateField.setText("01/01/2025");
        mockView.digitsField.setText("1234");

        mockView.validateButton.doClick();

        assertEquals("Pago validado con éxito.", controller.lastMessage);
    }
}
