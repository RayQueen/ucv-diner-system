package test.Models;

import models.Turn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {

    @Test
    void testConstructorAndGetters() {
        Turn turn = new Turn("Mañana", "Sopa", "Seco", "Jugo", "Postre");
        assertEquals("Mañana", turn.getTurn());
        assertEquals("Sopa", turn.getSoup());
        assertEquals("Seco", turn.getDry());
        assertEquals("Jugo", turn.getJuice());
        assertEquals("Postre", turn.getDessert());
    }

    @Test
    void testEmptyValues() {
        Turn turn = new Turn("", "", "", "", "");
        assertEquals("", turn.getTurn());
        assertEquals("", turn.getSoup());
        assertEquals("", turn.getDry());
        assertEquals("", turn.getJuice());
        assertEquals("", turn.getDessert());
    }
}