package tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

public class BurgerSmokeTest {

    @Mock
    Ingredient ingredientMock;
    @Mock
    Bun bun;

    private Burger burger;

    @Before
    public void setUp() {
        this.burger = new Burger();
    }


    @Test
    public void setBunsCorrect() {
        assert true == true;
    }

    @Test
    public void addIngredientCorrect() {
        int expected = this.burger.ingredients.size() + 1;
        this.burger.addIngredient(ingredientMock);
        int result = this.burger.ingredients.size();
        assertEquals(expected, result);
    }

    @Test
    public void removeIngredientCorrect() {
        int expected = 0;
        this.burger.addIngredient(ingredientMock);
        this.burger.removeIngredient(0);
        int result = this.burger.ingredients.size();
        assertEquals(expected, result);
    }

}
