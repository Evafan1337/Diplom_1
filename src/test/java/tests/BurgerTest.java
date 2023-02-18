package tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BurgerTest {

    Burger burger;
    Database database = new Database();
    List<Bun> buns = database.availableBuns();
    List<Ingredient> ingredients = database.availableIngredients();
    List<Ingredient> ingredientsUsed = new ArrayList<>();

    public float calculatePrice(){
        float price = buns.get(0).getPrice() * 2;

        for (Ingredient ingredient : ingredientsUsed) {
            price += ingredient.getPrice();
        }

        return price;
    }

    public String createReceipt(){
        StringBuilder expected = new StringBuilder(String.format("(==== %s ====)%n", buns.get(0).getName()));
        for (Ingredient ingredient : ingredientsUsed) {
            expected.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        expected.append(String.format("(==== %s ====)%n", buns.get(0).getName()));
        expected.append(String.format("%nPrice: %f%n", this.calculatePrice()));
        String expectedStr = expected.toString();

        return expectedStr;
    }

    public void moveIngredient(int index, int newIndex) {

        ingredientsUsed.add(newIndex, ingredientsUsed.remove(index));

    }

    @Before
    public void setUp(){

        this.burger = new Burger();

        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));

        this.ingredientsUsed.add(ingredients.get(1));
        this.ingredientsUsed.add(ingredients.get(4));
        this.ingredientsUsed.add(ingredients.get(3));
        this.ingredientsUsed.add(ingredients.get(5));

    }

    @Test
    public void checkGetReceiptCorrect(){

        String expected = this.createReceipt();
        String result = this.burger.getReceipt();

        assertEquals(expected, result);
    }

    //Can parametrize and create new class BurgerMovingIngridients?
    @Test
    public void checkMoveIngridientCorrect(){

        this.moveIngredient(2,3);
        String expected = this.createReceipt();

        this.burger.moveIngredient(2,3);
        String result = this.burger.getReceipt();

        assertEquals(expected, result);
    }

    @Test
    public void checkGetPriceCorrect(){

        float expected = this.calculatePrice();
        float result = this.burger.getPrice();

        assertEquals(expected, result,0);

    }
}
