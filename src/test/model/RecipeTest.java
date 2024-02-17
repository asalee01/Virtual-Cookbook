package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


//Tests all branches and edge cases for methods implemented in Recipe.
public class RecipeTest {
    private Recipe recipe;
    private List<String> instructions2;
    private List<String> ingredients2;
    private String description2;

    @BeforeEach
    public void setup() {
        instructions2 = new ArrayList<>();
        instructions2.add("Turn on stove");
        instructions2.add("Add ingredients");
        instructions2.add("Wait 10 minutes for it to cook");
        instructions2.add("Add spices to your preference.");
        ingredients2 = new ArrayList<>();
        ingredients2.add("Egg");
        ingredients2.add("Bread");
        ingredients2.add("Spices");
        ingredients2.add("Chicken");
        description2 = "Tasty Recipe";
        recipe = new Recipe("Recipe2", ingredients2, instructions2, 35, 40, 670,
                description2);
    }
    @Test
    public void testConstructor() {
        assertEquals("Recipe2", recipe.getName());
        assertEquals(40, recipe.getCookingTime());
        assertEquals(35, recipe.getPrepTime());
        assertEquals(670, recipe.getCalories());
        assertEquals("Add ingredients", recipe.getCookingInstructions().get(1));
        assertEquals("Chicken", recipe.getIngredients().get(3));
        assertEquals(description2, recipe.getDescription());
    }

    @Test
    public void testAddIngredientsOnce() {
        recipe.addIngredients("Milk");
        assertEquals("Milk", recipe.getIngredients().get(4));
    }

    @Test
    public void testAddIngredientsManyTimes() {
        recipe.addIngredients("Milk");
        recipe.addIngredients("Banana");
        recipe.addIngredients("Apple");
        assertEquals("Milk", recipe.getIngredients().get(4));
        assertEquals("Banana", recipe.getIngredients().get(5));
        assertEquals("Apple", recipe.getIngredients().get(6));
    }

    @Test
    public void testSetCalories() {
        recipe.setCalories(690);
        assertEquals(690, recipe.getCalories());
    }

    @Test
    public void testAddInstructionsOnce() {
        recipe.addCookingInstructions("Boil milk");
        assertEquals("Boil milk", recipe.getCookingInstructions().get(4));
    }


    @Test
    public void testSetIngredientsManyTimes() {
        recipe.addCookingInstructions("Cook");
        recipe.addCookingInstructions("Finish");
        assertEquals("Cook", recipe.getCookingInstructions().get(4));
        assertEquals("Finish", recipe.getCookingInstructions().get(5));
    }

    @Test
    public void testSetName() {
        recipe.setName("Test Recipe");
        assertEquals("Test Recipe", recipe.getName());
    }

    @Test
    public void testSetPrepTime() {
        recipe.setPrepTime(5000);
        assertEquals(5000, recipe.getPrepTime());
    }

    @Test
    public void testSetCookTime() {
        recipe.setCookingTime(5000);
        assertEquals(5000, recipe.getCookingTime());
    }

    @Test
    public void testSetDescription() {
        recipe.setDescription("The best recipe in the world!");
        assertEquals("The best recipe in the world!", recipe.getDescription());
    }
}
