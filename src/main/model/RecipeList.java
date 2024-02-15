package model;

import java.util.*;

public class RecipeList {

    private final List<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }

    public void addRecipes(Recipe r) {
        recipes.add(r);
    }

    public void removeRecipeByName(String r) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(r)) {
                recipes.remove(i);
                break;
            }
        }
    }

    public List<Recipe> allRecipes() {
        return recipes;
    }

//    public void modifyRecipe(Recipe recipe1, Recipe newRecipe) {
//        if (recipes.contains(recipe1)) {
//            recipes.set(recipes.indexOf(recipe1), newRecipe);
//        } else {
//            addRecipes(newRecipe);
//        }
//    }
//    //Change in ingredients

    public void modifyRecipe(String name, Recipe realState) {
        int index = -1;
        for (int i = 0; i <= recipes.size(); i++) {
            if (recipes.get(i).getName().equals(name)) {
                index = i;
            }
        }
        if (index == -1) {
            addRecipes(realState);
        } else {
            recipes.set(index, realState);
        }
    }

    public List<Recipe> searchByCookingTime(int time) {
        List<Recipe> filteredTimeRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if (time <= r.getCookingTime()) {
                filteredTimeRecipes.add(r);
            }
        }
        return filteredTimeRecipes;
    }

    public List<Recipe> searchByIngredients(List<String> i) {
         List<Recipe> filteredIngredientsRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getIngredients().containsAll(i)) {
                filteredIngredientsRecipes.add(r);
            }
        }
        return filteredIngredientsRecipes;
    }

    public int getSize() {
        return recipes.size();
    }

    public Recipe getRecipe(int r) {
        return recipes.get(r);
    }
}
