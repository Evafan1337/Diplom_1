package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private IngredientType type;
    private String name;
    private float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price){
        this.type = type;
        this.name = name;
        this.price = price;

        this.ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {IngredientType.FILLING, "Начинка_1", 100.0F},
                {IngredientType.SAUCE, "Соус_1", 200.0F}
        };
    }

    @Test
    public void getNameCorrect(){
        String expected = this.name;
        String result = this.ingredient.getName();
        assertEquals(expected,result);
    }

    @Test
    public void getPriceCorrect(){
        Float expected = this.price;
        Float result = this.ingredient.getPrice();
        assertEquals(expected,result);
    }

    @Test
    public void getTypeCorrect(){
        IngredientType expected = this.type;
        IngredientType result = this.ingredient.getType();
        assertEquals(expected,result);
    }


}
