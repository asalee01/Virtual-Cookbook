package model;

import java.util.*;

public class RecipeList {

    private final List<Recipe> recipes;
    private List<Recipe> filteredIngredientsRecipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }

    public void addRecipes(Recipe r) {
        recipes.add(r);
    }

    public void removeRecipe(Recipe r) {
        recipes.remove(r);
    }

    public List<Recipe> allRecipes() {
        return recipes;
    }

    public void modifyRecipe(Recipe recipe1, Recipe newRecipe) {
        if (recipes.indexOf(recipe1) != -1) {
            recipes.set(recipes.indexOf(recipe1), newRecipe);
        } else {
            addRecipes(newRecipe);
        }
    }

    public List<Recipe> searchByCookingTime(int time) {
        List<Recipe> filteredTimeRecipes = new ArrayList<>();
        for (Recipe r: recipes) {
            if (time <= r.getCookingTime()) {
                filteredTimeRecipes.add(r);
            }
        }
        return filteredTimeRecipes;
    }

    public List<Recipe> searchByIngredients(List<String> i) {
        filteredIngredientsRecipes = new ArrayList<>();
        for (Recipe r: recipes) {
            if (r.getIngredients().containsAll(i)) {
                filteredIngredientsRecipes.add(r);
            }
        }
        return filteredIngredientsRecipes;
    }
}
