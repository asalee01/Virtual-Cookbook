package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
}
