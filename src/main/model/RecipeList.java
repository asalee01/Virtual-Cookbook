package model;

import java.util.*;


//Recipe List is basically a list of recipes and has methods that can fulfill all the user stroies mentioned.
public class RecipeList {

    private final List<Recipe> recipes;

    public RecipeList() {
        recipes = new ArrayList<>();
    }


    //REQUIRES: A recipe
    //MODIFIES: this
    //EFFECTS: adds the recipe to the recipe list. If there are duplicate recipes, it will not be added.
    public void addRecipes(Recipe r) {
        if (!recipes.contains(r)) {
            recipes.add(r);
        }
    }


    //REQUIRES: A recipe name.
    //MODIFIES: this
    //EFFECTS: searches through the list of recipes for the recipe name given, then removes that recipe.
    public void removeRecipeByName(String r) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(r)) {
                recipes.remove(i);
                System.out.println("Recipe has been Removed");
                break;
            } else {
                System.out.println("No recipes found!!");
            }
        }
    }


    //REQUIRES: A recipe name and the modified recipe.
    //MODIFIES: this
    //EFFECTS: Finds the given name in the list and replaces it with the new Recipe. If not found, add it to the list.
    public void modifyRecipe(String name, Recipe newRecipe) {
        int index = -1;
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equals(name)) {
                index = i;
            }
        }
        if (index == -1) {
            addRecipes(newRecipe);
        } else {
            recipes.set(index, newRecipe);
        }
    }

    //REQUIRES: time >= 0.
    //MODIFIES:
    //EFFECTS: returns a list of recipes that have cooking time less than or equal to the time entered.
    public List<Recipe> searchByCookingTime(int time) {
        List<Recipe> filteredTimeRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if (time >= r.getCookingTime()) {
                filteredTimeRecipes.add(r);
            }
        }
        return filteredTimeRecipes;
    }


    //REQUIRES: List of ingredients
    //MODIFIES:
    //EFFECTS: returns a list of recipes that contain the list of ingredients entered in their ingredients list.
    public List<Recipe> searchByIngredients(List<String> i) {
        List<Recipe> filteredIngredientsRecipes = new ArrayList<>();
        for (String ing : i) {
            for (Recipe r : recipes) {
                if (r.getIngredients().contains(ing)) {
                    filteredIngredientsRecipes.add(r);
                }
            }
        }
        return filteredIngredientsRecipes;
    }

    //getters
    public int getSize() {
        return recipes.size();
    }

    public Recipe getRecipe(int r) {
        return recipes.get(r);
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }
}
