package test.Models;

import models.Menu;
import models.Turn;
import java.nio.file.StandardCopyOption;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.io.TempDir;

public class MenuTest {

    @TempDir
    Path tempDir;
    private Path testFilePath;
    private final String menuDataPath = "src/models/data/menu.txt";

    @BeforeEach
    void setUp() throws IOException {
        testFilePath = tempDir.resolve("test_menu.txt");
        // Copia el archivo temporal al archivo real antes de cada test
        Files.createDirectories(Path.of("src/models/data"));
        Files.writeString(Path.of(menuDataPath), ""); // Limpia el archivo antes de cada test
    }

    private void writeMenuFile(String content) throws IOException {
        Files.writeString(testFilePath, content);
        Files.copy(testFilePath, Path.of(menuDataPath), StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void testMenuLoadsSuccessfully() throws IOException {
        writeMenuFile("Desayuno,Arepa,Huevo revuelto,Cafe con leche,Fruta\n" +
                      "Almuerzo,Sopa de pollo,Pabellon,Jugo de lechoza,Dulce de lechoza\n");
        Menu menu = new Menu();
        assertNotNull(menu.menus);
        assertEquals(2, menu.menus.length);
    }

    @Test
    void testGetBreakfastDetails() throws IOException {
        writeMenuFile("Desayuno,Arepa,Huevo revuelto,Cafe con leche,Fruta\n");
        Menu menu = new Menu();
        Turn breakfast = menu.getBreakfast();
        assertEquals("Desayuno", breakfast.getTurn());
        assertEquals("Arepa", breakfast.getSoup());
    }

    @Test
    void testMalformedLineSkipsEntry() throws IOException {
        writeMenuFile("Desayuno,Arepa,Huevo revuelto\n" +
                      "Almuerzo,Sopa de pollo,Pabellon,Jugo de lechoza,Dulce de lechoza\n");
        Menu menu = new Menu();
        assertNotNull(menu.menus);
        assertEquals(2, menu.menus.length);
        assertNull(menu.menus[0].getDessert(), "La primera entrada debe ser null por línea malformada");
        assertNotNull(menu.menus[1], "La segunda entrada debe ser válida");
        assertEquals("Almuerzo", menu.menus[1].getTurn());
    }

    @Test
    void testEmptyFileResultsInNullTurns() throws IOException {
        writeMenuFile("");
        Menu menu = new Menu();
        assertNull(menu.menus);
        assertNull(menu.getBreakfast());
    }

    @Test
    void testNonExistentFileResultsInNullTurns() throws IOException {
        Files.deleteIfExists(Path.of(menuDataPath));
        Menu menu = new Menu();
        assertNull(menu.menus);
        assertNull(menu.getBreakfast());
    }
}