package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BurgerSmokeTest {

    @Mock
    private Ingredient ingredientMock;

    @Mock
    private Bun bunMock;

    private Burger burger;

    @Before
    public void setUp() {
        this.burger = new Burger();

    }


    @Test
    public void setBunsCorrect() {
        this.burger.setBuns(bunMock);
        assertNotNull(this.burger.bun);
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
