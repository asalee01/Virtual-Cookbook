package model;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class RecipeListTest {

   private Recipe recipe;
    private String instructions1;
    private String instructions2;
    private String instructions3;
    private String instructions4;
    private ArrayList<String> listOfInstructions;
    private String ingredients1;
    private String ingredients2;
    private String ingredients3;
    private String ingredients4;
    @BeforeEach
    public void setup() {
        instructions1 = "Turn on stove";
        instructions2 = "Add ingredients";
        instructions3 = "Wait 10 minutes for it to cook";
        instructions4 = "Add spices to your preference.";
        ingredients1 = "Egg";
        ingredients2 = "Bread";
        ingredients3 = "Cinnamon Powder";
        ingredients4 = "Sugar";
        recipe = new Recipe();
        recipe.setName("Recipe1");
        recipe.setCalories(900);
        recipe.setCookingInstructions(instructions1);
        recipe.setCookingInstructions(instructions2);
        recipe.setCookingInstructions(instructions3);
        recipe.setCookingInstructions(instructions4);
        recipe.setCookingTime(30);
        recipe.setPrepTime(15);
        recipe.setIngredients(ingredients1);
        recipe.setIngredients(ingredients2);
        recipe.setIngredients(ingredients3);
        recipe.setIngredients(ingredients4);
    }
}