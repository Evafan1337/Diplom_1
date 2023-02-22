package tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger;

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMockSauce;
    @Mock
    Ingredient ingredientMockFill;

    @Spy
    Burger burgerSpy;

    List<Ingredient> ingredientsUsed = new ArrayList<>();

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

//        burger.setBuns(buns.get(0));
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMockSauce);
        burger.addIngredient(ingredientMockFill);


        Mockito.when(bunMock.getPrice()).thenReturn(100F);
        Mockito.when(bunMock.getName()).thenReturn("bun_value");

        Mockito.when(ingredientMockSauce.getPrice()).thenReturn(120F);
        Mockito.when(ingredientMockFill.getPrice()).thenReturn(130F);

        Mockito.when(ingredientMockSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientMockFill.getType()).thenReturn(IngredientType.FILLING);

        Mockito.when(ingredientMockSauce.getName()).thenReturn("sauce_value");
        Mockito.when(ingredientMockFill.getName()).thenReturn("fill_value");

        this.ingredientsUsed.add(ingredientMockSauce);
        this.ingredientsUsed.add(ingredientMockFill);


    }


    @Test
    public void checkGetReceiptCorrect() {

        String expected = this.createReceipt();

        Burger burgerTest = new Burger();
        Burger b1 = Mockito.spy(burgerTest);
        Bun bunTest = new Bun("bun_value",100F);
        Ingredient sauceTest = new Ingredient(IngredientType.SAUCE,"sauce_value",100F);
        Ingredient fillTest = new Ingredient(IngredientType.FILLING, "fill_value", 100F);

        b1.setBuns(bunTest);
        b1.addIngredient(sauceTest);
        b1.addIngredient(fillTest);

        Mockito.when(b1.getPrice()).thenReturn(450F);

        String result = b1.getReceipt();

        assertEquals(expected, result);
    }

    //Can parametrize and create new class BurgerMovingIngridients?
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
