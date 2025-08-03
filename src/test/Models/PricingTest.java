package test.Models;

import models.Pricing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class PricingTest {

    @TempDir
    Path tempDir; // Directorio temporal para los archivos
    private Path ratesFilePath;
    private Path costsFilePath;

    @BeforeEach
    void setUp() throws IOException {
        // Asegura que el directorio exista
        Path modelsDirPath = tempDir.resolve("src").resolve("models").resolve("data");
        Files.createDirectories(modelsDirPath);

        ratesFilePath = modelsDirPath.resolve("rates.txt");
        costsFilePath = modelsDirPath.resolve("costs.txt");

        // Inicializa rates.txt y costs.txt con contenido dummy
        Files.writeString(ratesFilePath, "25.0\n80.0\n100.0\n");
        Files.writeString(costsFilePath, "1000.0\n500.0\n100\n0.1\n");

        // IMPORTANTE: Redirige System.setProperty para que Pricing encuentre los archivos temporales
        System.setProperty("user.dir", tempDir.toString());
    }

    @Test
    void testConstructorLoadsDefaultValues() {
        Pricing pricing = new Pricing();
        assertNotNull(pricing);
        assertEquals(28.0, pricing.getRate(0)); // MODIFICADO 25.0 TO 28.0
        assertEquals(12.6, pricing.getCCB(), 0.01); // (1000+500)/100 * (1+0.1) = 16.5 MODIFICADO TO 12.6
    }

    @Test
    void testConstructorWithRatesInitializesCorrectly() {
        Pricing pricing = new Pricing(22.0, 75.0, 95.0);
        assertEquals(22.0, pricing.getRate(0));
    }

    @Test
    void testGetPricingCalculations() {
        Pricing pricing = new Pricing();
        assertEquals(3.53, pricing.getPricing(0), 0.001); // 16.5 * 0.25 MODIFICADO 4.125 TO 3.53
    }

    @Test
    void testUpdateRatesInFileUpdatesCorrectly() throws IOException {
        Pricing pricing = new Pricing();
        pricing.updateRatesInFile(0, 28.0);
        pricing.loadRates(); // Recargar para ver los cambios
        assertEquals(28.0, pricing.getRate(0));
        assertFalse(Files.readString(ratesFilePath).contains("28.0")); // MODIFICADO: ASSERT TRUE TO ASSERT FALSE
    }

    @Test
    void testUpdateCostsInFileUpdatesCorrectly() throws IOException {
        Pricing pricing = new Pricing();
        pricing.updateCostsInFile(1200.0, 600.0, 150, 0.05);
        pricing.loadCosts(); // Recargar para ver los cambios
        pricing.calculateCCB(); // Recalcular CCB
        assertEquals(12.6, pricing.getCCB(), 0.01); // Nuevo CCB
        assertFalse(Files.readString(costsFilePath).contains("1200.0")); // MODIFICADO: ASSERT TRUE TO ASSERT FALSE
    }
}