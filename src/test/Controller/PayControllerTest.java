import controllers.PayController;
import models.Pricing;
import models.RegisteredUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import view.PayView;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PayControllerTest {

    @Mock private PayView mockPayView;
    @Mock private RegisteredUser mockRegisteredUser;
    @Mock private Pricing mockPricingModel;

    private PayController spyPayController; // Para espiar y mockear compareUserFace

    @BeforeEach
    void setUp() {
        PayController payController = new PayController(mockPayView, mockRegisteredUser);

        // Inyecta el mockPricingModel
        try {
            java.lang.reflect.Field pricingModelField = PayController.class.getDeclaredField("pricingModel");
            pricingModelField.setAccessible(true);
            pricingModelField.set(payController, mockPricingModel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Falló la inyección del mockPricingModel: " + e.getMessage());
        }

        spyPayController = spy(payController); // Creamos un espía para el controlador

        // Mock de JOptionPane (método estático)
        mockStatic(JOptionPane.class);

        // Mock de System.loadLibrary para OpenCV
        mockStatic(System.class);
        when(System.loadLibrary(anyString())).thenAnswer(invocation -> null); // No hacer nada al cargar la librería
    }

    @Test
    void testPayButtonInsufficientBalance() {
        when(mockPayView.priceValueLabel).thenReturn(new JLabel("Bs. 100.0"));
        when(mockRegisteredUser.getBalance()).thenReturn(50.0); // Saldo insuficiente

        spyPayController.payButton.doClick();

        verify(JOptionPane.class).showMessageDialog(null, "Saldo insuficiente para realizar el pago.", "Error", JOptionPane.WARNING_MESSAGE);
        verify(mockRegisteredUser, never()).addBalance(anyDouble());
        verify(mockPayView, never()).dispose();
    }

    @Test
    void testPayButtonNoImageProvided() {
        when(mockPayView.priceValueLabel).thenReturn(new JLabel("Bs. 10.0"));
        when(mockRegisteredUser.getBalance()).thenReturn(20.0);
        when(mockPayView.imagePathField).thenReturn(new JTextField("")); // Ruta vacía

        spyPayController.payButton.doClick();

        verify(JOptionPane.class).showMessageDialog(null, "Debe subir una imagen para el reconocimiento facial.", "Error", JOptionPane.WARNING_MESSAGE);
        verify(mockRegisteredUser, never()).addBalance(anyDouble());
    }

    @Test
    void testPayButtonStoredImageNotFound() throws Exception {
        when(mockPayView.priceValueLabel).thenReturn(new JLabel("Bs. 10.0"));
        when(mockRegisteredUser.getBalance()).thenReturn(20.0);
        when(mockPayView.imagePathField).thenReturn(new JTextField("/ruta/a/imagen.png"));
        when(mockRegisteredUser.getUser()).thenReturn("usuario_sin_imagen_almacenada");

        doReturn(-1).when(spyPayController).compareUserFace(anyString(), anyString()); // Simula imagen no encontrada

        spyPayController.payButton.doClick();

        verify(JOptionPane.class).showMessageDialog(null, "No se encontró la imagen almacenada del usuario.", "Error", JOptionPane.WARNING_MESSAGE);
    }

    @Test
    void testPayButtonFacialRecognitionFailed() throws Exception {
        when(mockPayView.priceValueLabel).thenReturn(new JLabel("Bs. 10.0"));
        when(mockRegisteredUser.getBalance()).thenReturn(20.0);
        when(mockPayView.imagePathField).thenReturn(new JTextField("/ruta/a/imagen.png"));
        when(mockRegisteredUser.getUser()).thenReturn("usuario_con_imagen");

        doReturn(0).when(spyPayController).compareUserFace(anyString(), anyString()); // Simula reconocimiento fallido

        spyPayController.payButton.doClick();

        verify(JOptionPane.class).showMessageDialog(null, "Reconocimiento facial fallido.", "Error", JOptionPane.WARNING_MESSAGE);
    }

    @Test
    void testPayButtonSuccessfulPayment() throws Exception {
        when(mockPayView.priceValueLabel).thenReturn(new JLabel("Bs. 10.0"));
        when(mockRegisteredUser.getBalance()).thenReturn(20.0);
        when(mockPayView.imagePathField).thenReturn(new JTextField("/ruta/a/imagen.png"));
        when(mockRegisteredUser.getUser()).thenReturn("usuario_con_imagen");
        when(mockRegisteredUser.getFullName()).thenReturn("Usuario de Prueba");

        doReturn(1).when(spyPayController).compareUserFace(anyString(), anyString()); // Simula reconocimiento exitoso

        spyPayController.payButton.doClick();

        verify(mockRegisteredUser).addBalance(-10.0);
        verify(mockPayView).updateBalance(mockRegisteredUser);
        verify(JOptionPane.class).showMessageDialog(null, "Pago realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        verify(mockPayView).dispose();
    }

    @Test
    void testCancelButtonDisposesView() {
        spyPayController.cancelButton.doClick();
        verify(mockPayView).dispose();
    }
}