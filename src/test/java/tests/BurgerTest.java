package tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMockSauce;
    @Mock
    private Ingredient ingredientMockFill;

    private List<Ingredient> ingredientsUsed = new ArrayList<>();

    public float calculatePrice() {
        float price = bunMock.getPrice() * 2;

        for (Ingredient ingredient : ingredientsUsed) {
            price += ingredient.getPrice();
        }

        return price;
    }

    public String createReceipt() {
        StringBuilder expected = new StringBuilder(String.format("(==== %s ====)%n", bunMock.getName()));
        for (Ingredient ingredient : ingredientsUsed) {
            expected.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        expected.append(String.format("(==== %s ====)%n", bunMock.getName()));
        expected.append(String.format("%nPrice: %f%n", this.calculatePrice()));
        String expectedStr = expected.toString();

        return expectedStr;
    }

    public void moveIngredient(int index, int newIndex) {

        ingredientsUsed.add(newIndex, ingredientsUsed.remove(index));

    }

    @Before
    public void setUp() {

        this.burger = new Burger();


        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMockSauce);
        burger.addIngredient(ingredientMockFill);


        when(bunMock.getPrice()).thenReturn(100F);
        when(bunMock.getName()).thenReturn("bun_value");

        when(ingredientMockSauce.getPrice()).thenReturn(120F);
        when(ingredientMockFill.getPrice()).thenReturn(130F);

        when(ingredientMockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMockFill.getType()).thenReturn(IngredientType.FILLING);

        when(ingredientMockSauce.getName()).thenReturn("sauce_value");
        when(ingredientMockFill.getName()).thenReturn("fill_value");

        this.ingredientsUsed.add(ingredientMockSauce);
        this.ingredientsUsed.add(ingredientMockFill);

    }


    @Test
    public void checkGetReceiptCorrect() {
        String expected = this.createReceipt();

        Burger burSpy = spy(this.burger);
        Mockito.doReturn(450F).when(burSpy).getPrice();

        String result = burSpy.getReceipt();
        assertEquals(expected, result);
    }

    @Test
    public void checkMoveIngredientCorrect() {
        this.moveIngredient(0, 1);
        String expected = this.createReceipt();
        this.burger.moveIngredient(0, 1);
        String result = this.burger.getReceipt();
        assertEquals(expected, result);
    }

    @Test
    public void checkGetPriceCorrect() {

        float expected = this.calculatePrice();
        float result = this.burger.getPrice();

        assertEquals(expected, result, 0);

    }
}
