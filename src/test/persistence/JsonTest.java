package persistence;

import model.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkRecipe(Recipe recipe, String name, List<String> ingredients, List<String> instructions,
                               int prepTime, int cookTime, int cals, String description) {
        assertEquals(name, recipe.getName());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(instructions, recipe.getCookingInstructions());
        assertEquals(prepTime, recipe.getPrepTime());
        assertEquals(cookTime, recipe.getCookingTime());
        assertEquals(cals, recipe.getCalories());
        assertEquals(description, recipe.getDescription());
    }
}
