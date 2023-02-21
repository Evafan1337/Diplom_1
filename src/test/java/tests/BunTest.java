package tests;

import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private Bun bun;
    private String name;
    private Double price;
    private boolean result;

    @Test
    public void getNameCorrect() {
        String expected = "test_name";
        Bun bun = new Bun(expected, 1000);
        String result = bun.getName();
        assertEquals(expected, result);
    }

    @Test
    public void getPriceCorrect() {
        Float expected = 1000.0F;
        Bun bun = new Bun("test_name", expected);
        Float result = bun.getPrice();
        assertEquals(expected, result);
    }
}
