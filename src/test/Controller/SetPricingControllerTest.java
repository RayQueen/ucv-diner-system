import controllers.SetPricingController;
import models.Pricing;
import models.RegisteredUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import view.SetPricingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SetPricingControllerTest {

    @Mock private SetPricingView mockSetPricingView;
    @Mock private RegisteredUser mockRegisteredUser;
    @Mock private Pricing mockPricingModel; // Mock del modelo Pricing

    private SetPricingController setPricingController;

    @BeforeEach
    void setUp() {
        setPricingController = new SetPricingController(mockSetPricingView, mockRegisteredUser);

        // Inyecta el mockPricingModel al controlador
        try {
            java.lang.reflect.Field pricingModelField = SetPricingController.class.getDeclaredField("pricingModel");
            pricingModelField.setAccessible(true);
            pricingModelField.set(setPricingController, mockPricingModel);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Falló la inyección del mockPricingModel: " + e.getMessage());
        }

        // Mock de los listeners de acción para poder verificarlos
        doNothing().when(mockSetPricingView.homeButton).addActionListener(any(ActionListener.class));
        doNothing().when(mockSetPricingView.logOutButton).addActionListener(any(ActionListener.class));
        doNothing().when(mockSetPricingView.saveButton).addActionListener(any(ActionListener.class));
        doNothing().when(mockSetPricingView.cancelButton).addActionListener(any(ActionListener.class));

        // Mock de JOptionPane (método estático)
        mockStatic(JOptionPane.class);
    }

    @Test
    void testHomeButtonDisposesView() {
        ArgumentCaptor<ActionListener> listenerCaptor = ArgumentCaptor.forClass(ActionListener.class);
        verify(mockSetPricingView.homeButton).addActionListener(listenerCaptor.capture());
        listenerCaptor.getValue().actionPerformed(mock(ActionEvent.class));
        verify(mockSetPricingView).dispose();
    }

    @Test
    void testSavePricingSuccessStudentRate() {
        when(mockSetPricingView.userType).thenReturn(0); // Estudiante
        when(mockSetPricingView.rateField.getText()).thenReturn("25.0");

        setPricingController.savePricing();

        verify(mockPricingModel).updateRatesInFile(0, 25.0);
        verify(JOptionPane.class).showMessageDialog(mockSetPricingView, "Tarifa guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        verify(mockSetPricingView).dispose();
    }

    @Test
    void testSavePricingEmptyRateShowsError() {
        when(mockSetPricingView.rateField.getText()).thenReturn("");
        setPricingController.savePricing();
        verify(JOptionPane.class).showMessageDialog(mockSetPricingView, "Por favor, complete la tarifa.", "Error", JOptionPane.ERROR_MESSAGE);
        verifyNoInteractions(mockPricingModel); // No se llama al modelo
    }

    @Test
    void testSavePricingStudentRateOutOfRangeShowsError() {
        when(mockSetPricingView.userType).thenReturn(0);
        when(mockSetPricingView.rateField.getText()).thenReturn("19.9"); // Demasiado bajo
        setPricingController.savePricing();
        verify(JOptionPane.class).showMessageDialog(mockSetPricingView, "La tarifa de estudiantes debe estar entre un 20% y 30%", "Error", JOptionPane.ERROR_MESSAGE);
        verifyNoInteractions(mockPricingModel);
    }
}