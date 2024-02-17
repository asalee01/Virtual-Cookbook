package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
//TODO: fix the issue with these tests.
public class RecipeListTest {

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private RecipeList recipes;

    @BeforeEach
    public void setup() {
        List<String> instructions = new ArrayList<>();
        instructions.add("Turn on stove");
        instructions.add("Add ingredients");
        instructions.add("Wait 10 minutes for it to cook");
        instructions.add("Add spices to your preference.");
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Egg");
        ingredients.add("Bread");
        ingredients.add("Cinnamon Powder");
        ingredients.add("Sugar");
        String description = "Tasty Recipe";
        recipe1 = new Recipe("Recipe1", ingredients, instructions, 15, 30, 900, description);

        List<String> instructions2 = new ArrayList<>();
        instructions2.add("Mix Ingredients");
        instructions2.add("Add them into pan");
        instructions2.add("Wait 5 minutes to cook");
        instructions2.add("Add spices to your preference.");
        List<String> ingredients2 = new ArrayList<>();
        ingredients2.add("Egg");
        ingredients2.add("Tomato");
        ingredients2.add("Onion");
        ingredients2.add("Pepper");
        ingredients2.add("Salt");
        String description2 = "Protein Dense Recipe";
        recipe2 = new Recipe("Recipe2", ingredients2, instructions2, 10, 25, 500,
                description2);

        List<String> instructions3 = new ArrayList<>();
        instructions3.add("Add water to flour and mix till it becomes a dough");
        instructions3.add("Heat pan");
        instructions3.add("Roll dough into small pieces");
        instructions3.add("Place dough on pan and let it cook for 3 minutes");
        List<String> ingredients3 = new ArrayList<>();
        ingredients3.add("Flour");
        ingredients3.add("Water");
        ingredients3.add("Salt");
        String description3 = "Carbohydrate Dense Recipe";
        recipe3 = new Recipe("Recipe3", ingredients3, instructions3, 5, 15, 80,
                description3);
        recipes = new RecipeList();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, recipes.getSize());
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        assertEquals(recipe1, recipes.getRecipe(0));
        assertEquals(recipe2, recipes.getRecipe(1));
        assertEquals(recipe3, recipes.getRecipe(2));
        assertEquals(3, recipes.getSize());
    }

    @Test
    public void testAddRecipe() {
        recipes.addRecipes(recipe1);
        assertEquals(recipe1, recipes.getRecipe(0));
        assertEquals(1, recipes.getSize());
    }

    @Test
    public void testAddManyRecipes() {
        recipes.addRecipes(recipe1);
        assertEquals(recipe1, recipes.getRecipe(0));
        assertEquals(1, recipes.getSize());

        recipes.addRecipes(recipe2);
        assertEquals(recipe2, recipes.getRecipe(1));
        assertEquals(2, recipes.getSize());
    }

    @Test
    public void testAddRepetitionRecipe() {
        recipes.addRecipes(recipe1);
        assertEquals(recipe1, recipes.getRecipe(0));
        assertEquals(1, recipes.getSize());

        recipes.addRecipes(recipe1);
        assertEquals(1, recipes.getSize());
    }

    @Test
    public void testRemoveRecipe() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        assertEquals(3, recipes.getSize());
        recipes.removeRecipeByName("Recipe3");
        assertEquals(2, recipes.getSize());
    }

    @Test
    public void testRemoveManyRecipes() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        assertEquals(3, recipes.getSize());
        recipes.removeRecipeByName("Recipe3");
        assertEquals(2, recipes.getSize());
        recipes.removeRecipeByName("Recipe1");
        assertEquals(1, recipes.getSize());
    }

    @Test
    public void testRemoveNoRecipes() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        assertEquals(3, recipes.getSize());
        recipes.removeRecipeByName("Amazing");
        assertEquals(3, recipes.getSize());
    }

    @Test
    public void testModifyRecipe() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.modifyRecipe("Recipe2", recipe3);
        assertEquals(recipe3, recipes.getRecipe(1));
    }

    @Test
    public void testModifyManyRecipe() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.modifyRecipe("Recipe2", recipe3);
        recipes.modifyRecipe("Recipe1", recipe2);
        assertEquals(recipe2, recipes.getRecipe(0));
        assertEquals(recipe3, recipes.getRecipe(1));
    }

    @Test
    public void testNoRecipeInListModifyRecipe() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.modifyRecipe("Recipe3", recipe3);
        assertEquals(3, recipes.getSize());
    }

    @Test
    public void testSearchByTimeNoRecipe() {
        List<Recipe> recipesTime = new ArrayList<>();
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        assertEquals(recipesTime, recipes.searchByCookingTime(10));
    }

    @Test
    public void testSearchByTimeOneRecipe() {
        List<Recipe> recipesTime = new ArrayList<>();
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        recipesTime.add(recipe3);
        assertEquals(recipesTime, recipes.searchByCookingTime(15));
    }

    @Test
    public void testSearchByTimeManyRecipes() {
        List<Recipe> recipesTime = new ArrayList<>();
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        recipesTime.add(recipe2);
        recipesTime.add(recipe3);
        assertEquals(recipesTime, recipes.searchByCookingTime(25));
    }

    @Test
    public void testSearchByIngredientsNoRecipe() {
        List<Recipe> recipesTime = new ArrayList<>();
        List<String> ingredientsFiltered = new ArrayList<>();
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        ingredientsFiltered.add("Chicken");
        ingredientsFiltered.add("Beef");
        assertEquals(recipesTime, recipes.searchByIngredients(ingredientsFiltered));
    }

    @Test
    public void testSearchByIngredientsOneRecipe() {
        List<Recipe> recipesTime = new ArrayList<>();
        List<String> ingredientsFiltered = new ArrayList<>();
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        ingredientsFiltered.add("Cinnamon Powder");
        recipesTime.add(recipe1);
        assertEquals(recipesTime, recipes.searchByIngredients(ingredientsFiltered));
    }

    @Test
    public void testSearchByIngredientsManyRecipes() {
        List<Recipe> recipesTime = new ArrayList<>();
        List<String> ingredientsFiltered = new ArrayList<>();
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        ingredientsFiltered.add("Egg");
        recipesTime.add(recipe1);
        recipesTime.add(recipe2);
        assertEquals(recipesTime, recipes.searchByIngredients(ingredientsFiltered));
    }

    @Test
    public void testGetAllRecipe() {
        recipes.addRecipes(recipe1);
        recipes.addRecipes(recipe2);
        recipes.addRecipes(recipe3);
        assertEquals(recipe1, recipes.getAllRecipes().get(0));
        assertEquals(recipe2, recipes.getAllRecipes().get(1));
        assertEquals(recipe3, recipes.getAllRecipes().get(2));
    }

}