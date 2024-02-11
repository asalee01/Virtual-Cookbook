package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    private Recipe recipe;
    private String instructions1;
    private String instructions2;
    private String instructions3;
    private String instructions4;

    private String ingredients1;
    private String ingredients2;
    private String ingredients3;
    private String ingredients4;

    private String decsription;

    @BeforeEach
    public void setup() {
        instructions1 = "Turn on stove";
        instructions2 = "Add ingredients";
        instructions3 = "Wait 5 minutes for it to cook";
        instructions4 = "Flip and wait same amount of time.";
        ingredients1 = "Egg";
        ingredients2 = "Bread";
        ingredients3 = "Cinnamon Powder";
        ingredients4 = "Sugar";
        decsription = "Tasty French Toast with a special ingredient";
        recipe = new Recipe();
        recipe.setName("Recipe1");
        recipe.setCalories(900);
        recipe.setCookingInstructions(instructions1);
        recipe.setCookingInstructions(instructions2);
        recipe.setCookingInstructions(instructions3);
        recipe.setCookingInstructions(instructions4);
        recipe.setCookingTime(30);
        recipe.setPrepTime(15);
        recipe.setDescription(decsription);
        recipe.setIngredients(ingredients1);
        recipe.setIngredients(ingredients2);
        recipe.setIngredients(ingredients3);
        recipe.setIngredients(ingredients4);
    }
    @Test
    public void testConstructor() {
        assertEquals("Recipe1", recipe.getName());
        assertEquals(30, recipe.getCookingTime());
        assertEquals(15, recipe.getPrepTime());
        assertEquals(900, recipe.getCalories());
        assertEquals(instructions2, recipe.getCookingInstructions().get(1));
        assertEquals(ingredients4, recipe.getIngredients().get(3));
    }

}
