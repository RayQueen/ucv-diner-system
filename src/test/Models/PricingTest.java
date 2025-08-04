package test.Models;

import models.Pricing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class PricingTest {

    private final String ratesFilePath = "src/models/data/rates.txt";
    private final String costsFilePath = "src/models/data/costs.txt";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Path.of("src/models/data"));
        Files.writeString(Path.of(ratesFilePath), "25.0\n80.0\n100.0\n");
        Files.writeString(Path.of(costsFilePath), "1000.0\n500.0\n100\n0.1\n");
    }

    @Test
    void testConstructorLoadsDefaultValues() {
        Pricing pricing = new Pricing();
        assertNotNull(pricing);
        assertEquals(25.0, pricing.getRate(0));
        assertEquals(16.5, pricing.getCCB(), 0.01); // (1000+500)/100 * (1+0.1) = 16.5
    }

    @Test
    void testConstructorWithRatesInitializesCorrectly() {
        Pricing pricing = new Pricing(22.0, 75.0, 95.0);
        assertEquals(22.0, pricing.getRate(0));
    }

    @Test
    void testGetPricingCalculations() {
        Pricing pricing = new Pricing();
        assertEquals(4.13, pricing.getPricing(0), 0.001); // 16.5 * 0.25
    }

    @Test
    void testUpdateRatesInFileUpdatesCorrectly() throws IOException {
        Pricing pricing = new Pricing();
        pricing.updateRatesInFile(0, 28.0);
        pricing.loadRates(); // Recargar para ver los cambios
        assertEquals(28.0, pricing.getRate(0));
        assertTrue(Files.readString(Path.of(ratesFilePath)).contains("28.0"));
    }

    @Test
    void testUpdateCostsInFileUpdatesCorrectly() throws IOException {
        Pricing pricing = new Pricing();
        pricing.updateCostsInFile(1200.0, 600.0, 150, 0.05);
        pricing.loadCosts(); // Recargar para ver los cambios
        pricing.calculateCCB(); // Recalcular CCB
        assertEquals(12.6, pricing.getCCB(), 0.01); // Nuevo CCB
        assertTrue(Files.readString(Path.of(costsFilePath)).contains("1200.0"));
    }
}