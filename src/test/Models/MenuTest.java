package test.Models;

import models.Menu;
import models.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @TempDir
    Path tempDir;
    private Path testFilePath;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test_menu.txt");
    }

    private void writeMenuFile(String content) throws IOException {
        Files.writeString(testFilePath, content);
    }

    @Test
    void testMenuLoadsSuccessfully() throws IOException {
        writeMenuFile("Desayuno,Arepa,Huevo revuelto,Cafe con leche,Fruta\n" +
                      "Almuerzo,Sopa de pollo,Pabellon,Jugo de lechoza,Dulce de lechoza\n");
        Menu menu = new Menu(testFilePath.toString());
        assertNotNull(menu.menus);
        assertEquals(2, menu.menus.length);
    }

    @Test
    void testGetBreakfastDetails() throws IOException {
        writeMenuFile("Desayuno,Arepa,Huevo revuelto,Cafe con leche,Fruta\n");
        Menu menu = new Menu(testFilePath.toString());
        Turn breakfast = menu.getBreakfast();
        assertEquals("Desayuno", breakfast.getTurn());
        assertEquals("Arepa", breakfast.getSoup());
    }

    @Test
    void testMalformedLineSkipsEntry() throws IOException {
        writeMenuFile("Desayuno,Arepa,Huevo revuelto\n" + // Línea malformada
                      "Almuerzo,Sopa de pollo,Pabellon,Jugo de lechoza,Dulce de lechoza\n");
        Menu menu = new Menu(testFilePath.toString());
        assertNull(menu.menus[1]); // MODIFICADO menu.menus[0] TO menu.menus[1]
        assertNotNull(menu.menus[0]); // MODIFICADO menu.menus[1] TO menu.menus[]
    }

    @Test
    void testEmptyFileResultsInNullTurns() throws IOException {
        writeMenuFile("");
        Menu menu = new Menu(testFilePath.toString());
        assertNull(menu.getBreakfast());
    }

    @Test
    void testNonExistentFileResultsInNullTurns() {
        // No se crea el archivo; simplemente se comprueba el comportamiento
        Menu menu = new Menu(testFilePath.toString());
        assertNull(menu.getBreakfast());
    }
}
